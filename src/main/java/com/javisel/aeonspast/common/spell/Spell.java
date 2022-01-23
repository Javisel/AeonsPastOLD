package com.javisel.aeonspast.common.spell;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.IPlayerData;
import com.javisel.aeonspast.common.registration.SpellRegistration;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.utilities.APUtilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;

public abstract class Spell extends net.minecraftforge.registries.ForgeRegistryEntry<Spell> {


    protected int defaultMaxCharges;
    protected int defaultCooldown = 0;
    protected float defaultCost;
    protected int defaultChargetime;
    private SpellRank spellRank;
    private RegistryObject<Resource> spellResource = null;



    /* Use this constructor fpr spells with charges.
     * @Param defaultMaxCharges - The maximum amount of charges this spell can have
     * @Param defaultChargeTime - the amount of time it takes to generate one charges
     * @Param defaultCooldown - the time you must wait between spell casts
     * @param defaultCost the (typically) mana cost you must spend to cast this
     * @param SpellRank - The rank of this spell. Skills use Skill_Basic or Skill_Ultimate

     */

    public Spell(int defaultMaxCharges, int defaultChargeTime, int defaultCooldown, float defaultCost, SpellRank spellRank) {
        this.defaultMaxCharges = defaultMaxCharges;
        this.defaultChargetime = defaultChargeTime;
        this.defaultCooldown = defaultCooldown;
        this.defaultCost = defaultCost;
        this.spellRank = spellRank;
    }


    /* Use this constructor if your spell does not use multiple charges.
     * defaultChargeTime  - how long it takes for the spell to recharge.
     * @Param defaultCost - the (typically) mana cost of this spell.
     *  @Param spellRank - the SpellRank. Skills use Skill_Basic or Skill_Ultimate.
     */
    public Spell(int defaultChargeTime, float defaultCost, SpellRank spellRank) {
        this.defaultMaxCharges = 1;
        this.defaultChargetime = defaultChargeTime;
        this.defaultCost = defaultCost;
        this.spellRank = spellRank;
        defaultChargetime = defaultChargeTime;
        this.defaultCooldown = 10;

    }

    public static int getId(Spell spell) {


        return spell == null ? 0 : RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.SPELL_REGISTRY_NAME).getID(spell.getRegistryName());
    }

    public static Spell getSpellByResourceLocation(ResourceLocation resourceLocation) {


        return resourceLocation == null ? getDefaultSpell() : (Spell) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.SPELL_REGISTRY_NAME).getValue(resourceLocation);
    }

    public static Spell getDefaultSpell() {


        return SpellRegistration.NONE.get();
    }

    public void onSpellEquipped(LivingEntity entity, SpellStack stack) {

        IEntityData data = APUtilities.getEntityData(entity);


        SpellStack spellStack = data.getOrCreateSpellStack(this);


        //TODO more efficient sync

        if (entity instanceof Player) {

            Player player = (Player) entity;

            IPlayerData playerData = APUtilities.getPlayerData(player);

            playerData.addActiveSpell(this);


            APUtilities.syncTotalPlayerData(player);


        }


    }

    public boolean canunEquipSpell(LivingEntity entity, SpellStack stack) {


        return getSpellStack(entity, stack).getCharges() >= 1;
    }

    public void onSpellUnEquipped(LivingEntity entity, SpellStack stack) {


        if (entity instanceof Player) {

            Player player = (Player) entity;

            IPlayerData playerData = APUtilities.getPlayerData(player);

            playerData.removeActiveSpell(this);


            APUtilities.syncTotalPlayerData(player);


        }

    }

    public void commitCosts(LivingEntity caster, SpellStack stack) {


            APUtilities.addManaToUnit(caster, -1 * getCost(caster, stack));


            SpellStack spellStack = APUtilities.getEntityData(caster).getOrCreateSpellStack(this);

            spellStack.charges--;


            spellStack.cooldown = getCurrentMaxCooldown(caster, stack);
            if (spellStack.charges==0) {


                spellStack.chargeTime=getChargeTime(caster,spellStack);


            }

            if (caster instanceof  Player && !caster.level.isClientSide) {

                APUtilities.syncTotalPlayerData((Player) caster);

            }




    }

    public boolean attemptCast(LivingEntity caster, SpellStack stack) {

        if (canCast(caster, stack)) {

            commitCosts(caster, stack);
            cast(caster, stack);
            endCast(caster, stack);
            return true;

        }


        return false;
    }

    public void endCast(LivingEntity caster, SpellStack stack) {

        if (caster instanceof Player) {

            Player player = (Player) caster;


        }


    }

    public boolean canCast(LivingEntity caster, SpellStack stack) {


        IEntityData data = APUtilities.getEntityData(caster);


        if (stack.isCoolingDown()) {

            System.out.println("Cooling Down!");
            return false;
        }

        if (stack.getCharges() < 1) {
            System.out.println("No charges !");

            return false;
        }


        if (getCostResource(caster,stack) !=null) {
            if (data.getResourceAmount(getCostResource(caster,stack)) < getCost(caster, stack)) {

                System.out.println("No Mana !");

                return false;
            }

        }

        if (this instanceof ICustomUseRequirement) {
            System.out.println("Can't pass custom use requirement!!");

            ICustomUseRequirement checkRequirementItem = (ICustomUseRequirement) this;

            if (!checkRequirementItem.canMeetRequirement(caster, stack)) {

                return false;
            }

        }


        return true;

    }

    public int getCurrentMaxCooldown(LivingEntity entity, SpellStack stack) {


        int result = defaultCooldown;


        result = (int) (result * APUtilities.getCooldownCoefficient(entity));

        return (int) result;
    }

    public abstract void cast(LivingEntity entity, SpellStack stack);

    public float getCost(LivingEntity caster, SpellStack stack) {


        return defaultCost;
    }

    public void tick(LivingEntity caster, SpellStack stack) {


        SpellStack spellStack = APUtilities.getEntityData(caster).getOrCreateSpellStack(this);


        if (spellStack.cooldown > 0) {


            spellStack.cooldown--;

            if (spellStack.cooldown == 0) {


                onFinishCooldown(caster, stack);
                return;
            }


        }


        if (spellStack.chargeTime > 0) {

            spellStack.chargeTime--;

            if (spellStack.chargeTime == 0) {

                spellStack.charges++;

                if (spellStack.charges < getMaxCharges(caster, stack)) {


                    spellStack.chargeTime = getChargeTime(caster, stack);


                }


            }


        }


    }

    public int getMaxCharges(LivingEntity caster, SpellStack stack) {


        return defaultMaxCharges;
    }

    public int getChargeTime(LivingEntity caster, SpellStack stack) {

        int result = defaultChargetime;


        result = (int) (result * APUtilities.getCooldownCoefficient(caster));

        return result;


    }

    public boolean isRecharging(LivingEntity entity, SpellStack stack) {


        return getSpellStack(entity, stack).isRecharging();


    }

    public SpellStack getSpellStack(LivingEntity entity, SpellStack stack) {


        return APUtilities.getEntityData(entity).getOrCreateSpellStack(this);


    }

    public int getDefaultMaxCharges() {
        return defaultMaxCharges;
    }

    public int getDefaultCooldown() {
        return defaultCooldown;
    }

    public float getDefaultCost() {
        return defaultCost;
    }

    public int getDefaultChargetime() {
        return defaultChargetime;
    }

    public SpellRank getSpellRank() {
        return spellRank;
    }

    public boolean isEmpty(Spell spell) {


        return spell == getDefaultSpell();
    }

    public void onFinishCooldown(LivingEntity entity, SpellStack stack) {


        if (stack.charges<getMaxCharges(entity, stack)){

            stack.chargeTime=getChargeTime(entity,stack);


        }

    }

    @OnlyIn(Dist.CLIENT)

    public ResourceLocation[] getSpellRenderLocations() {

        ResourceLocation[] locations = new ResourceLocation[]{super.getRegistryName()};

        return locations;
    }



    public Resource getCostResource(LivingEntity entity, SpellStack stack) {




        return spellResource.get();

    }


    protected Spell setSpellResource(RegistryObject<Resource> resource) {


        spellResource=resource;
        return this;
    }






}
