package com.javisel.aeonspast.common.spell;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import com.javisel.aeonspast.common.networking.PacketHandler;
import com.javisel.aeonspast.common.networking.PlayerCapabiltiiesMessage;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.utilities.APUtilities;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.registries.RegistryManager;

public abstract class Spell extends net.minecraftforge.registries.ForgeRegistryEntry<Spell>{



    public static final Spell EMPTY = null;
    protected  int defaultMaxCharges;
    protected int defaultCooldown =0;
    protected float defaultCost;
    protected int defaultChargetime;
    private SpellRank spellRank;




    /* Use this constructor fpr spells with charges.
    * @Param defaultMaxCharges - The maximum amount of charges this spell can have
    * @Param defaultChargeTime - the amount of time it takes to generate one charges
    * @Param defaultCooldown - the time you must wait between spell casts
    * @param defaultCost the (typically) mana cost you must spend to cast this
    * @param SpellRank - The rank of this spell. Skills use Skill_Basic or Skill_Ultimate

     */

    public Spell(int defaultMaxCharges, int defaultChargeTime, int defaultCooldown, float defaultCost, SpellRank spellRank) {
        this.defaultMaxCharges = defaultMaxCharges;
        this.defaultChargetime=defaultChargeTime;
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
        defaultChargetime=defaultChargeTime;



    }




    public void onSpellEquipped(LivingEntity entity, SpellStack stack) {

        IEntityData data = APUtilities.getEntityData(entity);


      SpellStack spellStack =  data.getOrCreateSpellStack(this);
      spellStack.currentMaxCooldownOrCharge=this.getChargeTime(entity,stack);








        //TODO more efficient sync

        if (entity instanceof  Player) {

            Player player = (Player) entity;



            APUtilities.syncTotalPlayerData(player);


        }






    }




    public boolean canunEquipSpell(LivingEntity entity, SpellStack stack) {





        return getSpellStack(entity,stack).getCharges()>=1;
    }




    public void onSpellUnEquipped(LivingEntity entity, SpellStack stack) {


        if (entity instanceof  Player) {

            Player player = (Player) entity;



            APUtilities.syncTotalPlayerData(player);


        }


    }




    public void commitCosts(LivingEntity caster, SpellStack stack) {


        if (!caster.level.isClientSide) {
            APUtilities.addManaToUnit(caster, -1 * getCost(caster,stack));


            SpellStack spellStack = APUtilities.getEntityData(caster).getOrCreateSpellStack(this);

            spellStack.charges--;

            if (spellStack.charges ==0) {

                spellStack.cooldownOrChargeRemaining = getChargeTime(caster,stack);

            }
            else {

                spellStack.cooldownOrChargeRemaining = getMaxCooldown(caster,stack);
            }

                spellStack.currentMaxCooldownOrCharge=spellStack.cooldownOrChargeRemaining;








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


        if (stack.isCoolingdown) {
            return false;
        }

        if (stack.getCharges()<1) {
            return false;
        }

        if (data.getMana() < getCost(caster, stack)) {

            return false;
        }

        if (this instanceof ICustomUseRequirement) {

            ICustomUseRequirement checkRequirementItem = (ICustomUseRequirement) this;

            if (!checkRequirementItem.canMeetRequirement(caster,stack)) {

                return false;
            }

        }



        return true;

    }



 public int getMaxCooldown(LivingEntity entity, SpellStack stack) {


     int result = defaultCooldown;

     int cdr = (int) entity.getAttributeValue(AttributeRegistration.COOLDOWN_REDUCTION.get());


        result = result *  (100/100+cdr);

        return (int) result;
 }







    public abstract void cast(LivingEntity entity, SpellStack stack);


    public float getCost(LivingEntity caster, SpellStack stack) {


        return defaultCost;
    }



    public void tick(LivingEntity caster, SpellStack stack) {


        SpellStack spellStack = APUtilities.getEntityData(caster).getOrCreateSpellStack(this);



        if (spellStack.cooldownOrChargeRemaining > 0) {

            spellStack.cooldownOrChargeRemaining--;

            if (spellStack.charges==0 && spellStack.cooldownOrChargeRemaining ==0) {

                spellStack.charges=1;

            }
            return;
        }



        if (spellStack.charges < getMaxCharges(caster,stack)) {
            if (spellStack.cooldownOrChargeRemaining > 0) {
                spellStack.cooldownOrChargeRemaining--;

                if (spellStack.cooldownOrChargeRemaining == 0) {

                    spellStack.charges++;

                    if (spellStack.charges<getMaxCharges(caster,stack)) {
                        spellStack.cooldownOrChargeRemaining = this.getChargeTime(caster,stack);
                        spellStack.currentMaxCooldownOrCharge=spellStack.cooldownOrChargeRemaining;


                    }

                }


            }
            }
 







    }



    public int getMaxCharges(LivingEntity caster, SpellStack stack) {


        return defaultMaxCharges;
    }


    public int getChargeTime(LivingEntity caster, SpellStack stack) {

        int result = defaultChargetime;

        int cdr = (int) caster.getAttributeValue(AttributeRegistration.COOLDOWN_REDUCTION.get());


        result = result *  (100/(100+cdr));

        return (int) result;


    }


    public boolean isRecharging(LivingEntity entity, SpellStack stack) {


        return  getSpellStack(entity,stack).charges < getMaxCharges(entity,stack) && getSpellStack(entity,stack).cooldownOrChargeRemaining!=0;




    }

    public SpellStack getSpellStack(LivingEntity entity, SpellStack stack) {





        return  APUtilities.getEntityData(entity).getOrCreateSpellStack(this);


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




    public static int getId(Spell spell) {



        return spell == null ? 0 :RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.SPELL_REGISTRY_NAME).getID(spell.getRegistryName());
    }


    public static Spell getSpellByResourceLocation(ResourceLocation resourceLocation) {





        return resourceLocation==null ? EMPTY : (Spell) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.SPELL_REGISTRY_NAME).getValue(resourceLocation);
    }



    public boolean isEmpty(Spell spell) {




        return spell == EMPTY;
    }




    @OnlyIn(Dist.CLIENT)

    public ResourceLocation[] getSpellResources() {

        ResourceLocation[] locations = new ResourceLocation[]{super.getRegistryName()};

        return locations;
    }


}
