package com.javisel.aeonspast.common.spell;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.registration.ResourceRegistration;
import com.javisel.aeonspast.common.registration.SpellRegistration;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
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
    private RegistryObject<Resource> spellResource = ResourceRegistration.MANA;



    /* Use this constructor for spells with charges.
     * @Param defaultMaxCharges - The maximum amount of charges this spell can have
     * @Param defaultChargeTime - the amount of time it takes to generate one charge
     * @Param defaultCooldown - the time you must wait between spell casts
     * @param defaultCost the resource cost you must spend to cast this
     * @param SpellRank - The rank of this spell.

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
     * @Param defaultCost - the cost of this spell.
     *  @Param spellRank - the rank of this spell.
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


    public static boolean isSpellDefault(Spell spell) {


        return spell == null || spell == getDefaultSpell();
    }

    public void onSpellEquipped(Player entity, SpellStack stack) {

        IPlayerData data = Utilities.getPlayerData(entity);


        SpellStack spellStack =  Utilities.getOrCreateSpellstack(entity,this);
 
        
        if (spellStack.getCharges() < getMaxCharges(entity, stack)) {

            spellStack.chargeTime = getChargeTime(entity, stack);

        }

        //TODO more efficient sync


        IPlayerData entityData = Utilities.getPlayerData(entity);

        entityData.addActiveSpell(this);


        if (entity instanceof ServerPlayer) {
            Utilities.syncTotalPlayerData((Player) entity);

        }


    }

    public boolean canunEquipSpell(Player entity, SpellStack stack) {


        return getSpellStack(entity, stack).getCharges() >= 1;
    }

    public void onSpellUnEquipped(Player entity, SpellStack stack) {


        IPlayerData entityData = Utilities.getPlayerData(entity);

        entityData.removeActiveSpell(this);


        if (entity instanceof ServerPlayer) {
            Utilities.syncTotalPlayerData((Player) entity);

        }


    }

    public void commitCosts(Player caster, SpellStack stack) {


        if (spellResource != null) {

            spellResource.get().addResource(caster, -1 * getCost(caster, stack), true);

        }


        SpellStack spellStack = Utilities.getOrCreateSpellstack(caster,this);

        spellStack.charges--;


        spellStack.cooldown = getCurrentMaxCooldown(caster, stack);
        if (spellStack.charges == 0) {


            spellStack.chargeTime = getChargeTime(caster, spellStack);


        }

        if (caster instanceof Player && !caster.level.isClientSide) {

            Utilities.syncTotalPlayerData((Player) caster);

        }


    }

    public boolean attemptCast(Player caster, SpellStack stack) {


        if (stack == null) {
            Utilities.getOrCreateSpellstack(caster,this);
        }
        if (canCast(caster, stack)) {

            commitCosts(caster, stack);
            cast(caster, stack);
            endCast(caster, stack);
            return true;

        }


        return false;
    }

    public void endCast(Player caster, SpellStack stack) {

        if (caster instanceof Player) {

            Player player = (Player) caster;


        }


    }

    public boolean canCast(LivingEntity caster, SpellStack stack) {


        if (caster instanceof Player) {

            IPlayerData data = Utilities.getPlayerData((Player) caster);


            if (stack == null) {

                stack = createNewSpellStack(caster);
            }

            if (stack.isCoolingDown()) {

                return false;
            }

            if (stack.getCharges() < 1) {

                return false;
            }


            if (getCostResource((Player) caster, stack) != null) {
                if (data.getOrCreateResource(getCostResource((Player) caster, stack)) < getCost((Player) caster, stack)) {


                    return false;
                }

            }

            if (this instanceof ICustomUseRequirement) {

                ICustomUseRequirement checkRequirementItem = (ICustomUseRequirement) this;

                if (!checkRequirementItem.canMeetRequirement(caster, stack)) {

                    return false;
                }

            }


            return true;

        }

        return false;
    }

    public int getCurrentMaxCooldown(Player entity, SpellStack stack) {


        int result = defaultCooldown;


        result = (int) (result * Utilities.getCooldownCoefficient(entity));

        return (int) result;
    }

    public abstract void cast(LivingEntity entity, SpellStack stack);

    public float getCost(Player caster, SpellStack stack) {


        return defaultCost;
    }

    public void tick(Player caster, SpellStack stack) {


        SpellStack spellStack = Utilities.getOrCreateSpellstack(caster,this);


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

                onFinishCharge(caster, stack);

            }


        }


    }

    public int getMaxCharges(Player caster, SpellStack stack) {


        return defaultMaxCharges;
    }

    public int getChargeTime(Player caster, SpellStack stack) {

        int result = defaultChargetime;


        result = (int) (result * Utilities.getCooldownCoefficient(caster));

        return result;


    }

    public boolean isRecharging(Player entity, SpellStack stack) {


        return getSpellStack(entity, stack).isRecharging();


    }

    public SpellStack getSpellStack(Player entity, SpellStack stack) {


        return Utilities.getOrCreateSpellstack(entity,this);


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


    public void onFinishCooldown(Player entity, SpellStack stack) {


        if (stack.charges < getMaxCharges(entity, stack)) {

            stack.chargeTime = getChargeTime(entity, stack);


        }

    }

    @OnlyIn(Dist.CLIENT)

    public ResourceLocation[] getSpellRenderLocations() {

        ResourceLocation[] locations = new ResourceLocation[]{super.getRegistryName()};

        return locations;
    }


    public Resource getCostResource(Player entity, SpellStack stack) {


        if (spellResource != null) {
            return spellResource.get();
        }

        return null;

    }


    protected Spell setSpellResource(RegistryObject<Resource> resource) {


        spellResource = resource;
        return this;
    }


    public void onFinishCharge(Player entity, SpellStack stack) {


        stack.charges++;

        if (stack.charges < getMaxCharges(entity, stack)) {

            stack.chargeTime = getChargeTime(entity, stack);

        }


    }


    public void addToCooldown(int amount, SpellStack stack, Player entity) {


        int cooldown = stack.cooldown;


        if (cooldown + amount < 0) {

            amount = cooldown;
        }


        cooldown += amount;


        if (cooldown == 0) {

            onFinishCooldown(entity, stack);
        }


    }

    public void addToCharge(int amount, Player entity, SpellStack stack) {


        int charge = stack.chargeTime;


        if (stack.charges == getMaxCharges(entity, stack)) {

            return;
        }


        if (charge + amount < 0) {

            amount = charge;
        }

        charge += amount;

        if (charge == 0) {


            onFinishCharge(entity, stack);
        }


    }

    public String getGenericDescription() {


        return getRegistryName().toString() + ".desc.generic";
    }


    public void equipWeaponSpell(Player player) {

        IPlayerData playerData = Utilities.getPlayerData(player);
        IPlayerData entityData = Utilities.getPlayerData(player);


        playerData.setActiveWeaponSpell(this);
          playerData.setSpellstack(this,Utilities.getOrCreateSpellstack(player,this));

    }


    public SpellStack createNewSpellStack(LivingEntity creator) {


        SpellStack stack = new SpellStack(this);


        return stack;
    }


}
