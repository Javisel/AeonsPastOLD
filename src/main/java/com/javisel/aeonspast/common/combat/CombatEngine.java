package com.javisel.aeonspast.common.combat;

import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import com.javisel.aeonspast.common.config.WeaponData;
import com.javisel.aeonspast.common.effects.ComplexEffect;
import com.javisel.aeonspast.common.events.EventFactory;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.items.properties.WeaponProperty;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.server.ServerHandler;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class CombatEngine {


    public static boolean attemptCriticalHit(LivingEntity critter) {

        Random random = critter.getRandom();


        float chance = random.nextFloat(101);


        return chance <= critter.getAttributeValue(AttributeRegistration.CRITICAL_CHANCE.get());


    }

    public static void applyCrits(LivingEntity attacker, LivingEntity victim, DamageInstance instance) {


        if (instance.isCritical) {
            return;
        }
        instance.preMitigationsAmount = applyCriticalInstance(attacker, instance);
        instance.isCritical = true;


    }

    public static void applyWeaponLifesteal(LivingEntity attacker, LivingEntity victim, DamageInstance instance) {

        double amount = instance.postMitigationsAmount * (attacker.getAttributeValue(AttributeRegistration.WEAPON_LIFESTEAL.get()) / 100);
        amount *= attacker.getAttributeValue(AttributeRegistration.HEALING_INTAKE.get());
        amount = EventFactory.onLifesteal(attacker, victim, (float) amount);

        if (instance.isArea) {

            amount *= 0.33;


        }


        if (amount > 0) {


            attacker.heal((float) amount);
        }


    }
    public static void applySpellLifestal(LivingEntity attacker, LivingEntity victim, DamageInstance instance) {

        double amount = instance.postMitigationsAmount * (attacker.getAttributeValue(AttributeRegistration.SPELL_LIFESTEAL.get()) / 100);
        amount *= attacker.getAttributeValue(AttributeRegistration.HEALING_INTAKE.get());
        amount = EventFactory.onLifesteal(attacker, victim, (float) amount);

        if (instance.isArea) {

            amount *= 0.33;


        }


        if (amount > 0) {


            attacker.heal((float) amount);
        }


    }

    private static double applyCriticalInstance(LivingEntity entity, DamageInstance instance) {

        double critDamage = entity.getAttributeValue(AttributeRegistration.CRITICAL_DAMAGE.get());

        double base = instance.getPreMitigationsAmount();

        base *= critDamage;


        base *= instance.critPower;


        return base;
    }

    private static double getDamagePostMitigations(double armor, double damage) {

        double mitigations = 1;
        if (armor < 0) {


            mitigations = (2 - (100 / (100 + armor)));
        } else if (armor == 0) {
            return damage;
        } else if (armor > 0) {


            mitigations = (100 / (100 + armor));
        }


        return mitigations * damage;


    }

    public static DamageInstance calculateMeleeDamage(LivingEntity attacker, ItemStack weapon) {


        double baseDamage = attacker.getAttributeValue(AttributeRegistration.WEAPON_POWER.get());
         double physicalPower = attacker.getAttributeValue(AttributeRegistration.PHYSICAL_POWER.get());
        double rangeBonus = attacker.getAttributeValue(AttributeRegistration.MELEE_POWER.get());

        double total = (baseDamage + physicalPower) * (1 + (rangeBonus/100));

        total *= attacker.getAttributeValue(AttributeRegistration.DAMAGE_OUTPUT.get());


        WeaponData weaponData = ServerHandler.WEAPON_STATISTICS_LOADER.getWeaponData(weapon);


        DamageInstance instance = new DamageInstance(weapon, weaponData.getWeapon_type().getDamageType(), total, false, false, true);;

         return instance;


    }


    //TODO Ranged rework based on projectiles, and projectile damage types and mods
    public static DamageInstance calculateRangedDamage(LivingEntity attacker, ItemStack weapon, float rangedPower ) {

        double baseDamage = attacker.getAttributeValue(AttributeRegistration.WEAPON_POWER.get());
        double physicalPower = attacker.getAttributeValue(AttributeRegistration.PHYSICAL_POWER.get());
        double rangeBonus = attacker.getAttributeValue(AttributeRegistration.RANGED_POWER.get());

        double total = (baseDamage + physicalPower) * (1 + (rangeBonus/100));


        if (rangedPower <0) {
            rangedPower = 0;
        }

        total *= rangedPower;
        total *= attacker.getAttributeValue(AttributeRegistration.DAMAGE_OUTPUT.get());



        DamageInstance instance = new DamageInstance(weapon, DamageTypeEnum.PUNCTURE, total, false, false, false);;

        return instance;




    }








    public static double getMitigatedDamage(LivingEntity victim, DamageInstance instance) {


        double baseamount = instance.getPreMitigationsAmount();


        double armor = 0;
        double damageMod = 1;
        damageMod += victim.getAttributeValue(AttributeRegistration.DAMAGE_INTAKE.get()) / 100;

        if (instance.getDamage_type().isAbsolute()) {

            return baseamount;
        }
         else {


             if (!instance.isMagic) {

                 armor = victim.getAttribute(AttributeRegistration.ARMOR.get()).getValue();
                 armor *= 1 + victim.getAttribute(AttributeRegistration.ARMOR_TOUGHNESS.get()).getValue();

                 damageMod -= victim.getAttributeValue(AttributeRegistration.PHYSICAL_MITIGATIONS.get()) / 100;

             }
             if (instance.isMagic) {



                armor+=victim.getAttribute(AttributeRegistration.MAGIC_RESISTANCE.get()).getValue();
                damageMod=1 -victim.getAttributeValue(AttributeRegistration.MAGICAL_MITIGATIONS.get()) / 100;
            }


        }



        baseamount = getDamagePostMitigations(armor, baseamount);

        baseamount *= damageMod;


         return baseamount;


    }

    public void swingWeapon(LivingEntity attacker, ItemStack stack) {


        ArrayList<ItemProperty> properties = ItemEngine.getItemProperties(stack);

        if (EventFactory.onSwingItem(attacker, stack)) {


            if (!properties.isEmpty()) {


                for (ItemProperty property : properties) {


                    if (property instanceof WeaponProperty) {

                        WeaponProperty weaponProperty = (WeaponProperty) property;


                        weaponProperty.onSwingItem(attacker, stack);


                    }


                }


            }


        }


    }


    public void doDamageCalculations(APDamageSource damageSource, LivingEntity victim) {


    }


    
    public static boolean   cycleAllPreHitEffects(LivingEntity attacker, LivingEntity victim, APDamageSource damageSource) {


        boolean result = true;


        DamageInstance instance = damageSource.instance;


        Object device = instance.damageDevice;


        if (device != null) {

            if (attacker != null) {

                if (device instanceof Spell) {




                }
                if (device instanceof ItemStack) {



                    ItemStack weapon = (ItemStack) device;


                    for (ItemProperty property : ItemEngine.getItemProperties(weapon)) {


                        result =    property.onPreHitEntityInHand(attacker, victim, instance, weapon);


                    }
                }

                ArrayList<ItemStack> attackerItems = ItemEngine.getAllAppicableItems(attacker);
                for (ItemStack attackerStack : attackerItems) {


                    if (ItemEngine.isItemInitialized(attackerStack)) {


                        for (ItemProperty property : ItemEngine.getItemProperties(attackerStack)) {


                            result =             property.onPreHitEntity(attacker, victim, instance);


                        }

                    }


                }

                Collection<MobEffectInstance> effects = attacker.getActiveEffects();


                for (MobEffectInstance mobEffectInstance : effects) {

                    if (mobEffectInstance.getEffect() instanceof ComplexEffect) {

                        ComplexEffect effect = (ComplexEffect) mobEffectInstance.getEffect();

                        result =       effect.onpreHitEffect(attacker, victim, damageSource);

                    }


                }


            }


            ArrayList<ItemStack> victimItems = ItemEngine.getAllAppicableItems(victim);


            for (ItemStack victimStack : victimItems) {


                if (ItemEngine.isItemInitialized(victimStack)) {


                    for (ItemProperty property : ItemEngine.getItemProperties(victimStack)) {


                        result =       property.onOwnerPreHit(attacker, victim, instance);


                    }

                }


            }


            Collection<MobEffectInstance> effects = victim.getActiveEffects();


            for (MobEffectInstance mobEffectInstance : effects) {

                if (mobEffectInstance.getEffect() instanceof ComplexEffect) {

                    ComplexEffect effect = (ComplexEffect) mobEffectInstance.getEffect();

                    result =        effect.onOwnerpreHitEffect(attacker, victim, damageSource);

                }


            }

        }



return  result;
    }



    public static void cycleAllHitEffects(LivingEntity attacker, LivingEntity victim, APDamageSource damageSource) {



        DamageInstance instance = damageSource.instance;


        Object device = instance.damageDevice;


        if (device != null) {

            if (attacker != null) {

                if (device instanceof Spell) {




                }
                if (device instanceof ItemStack) {



                    ItemStack weapon = (ItemStack) device;


                    for (ItemProperty property : ItemEngine.getItemProperties(weapon)) {


                        property.onHitEntityInHand(attacker, victim, instance, weapon);


                    }
                }

                ArrayList<ItemStack> attackerItems = ItemEngine.getAllAppicableItems(attacker);
                for (ItemStack attackerStack : attackerItems) {


                    if (ItemEngine.isItemInitialized(attackerStack)) {


                        for (ItemProperty property : ItemEngine.getItemProperties(attackerStack)) {


                            property.onHitEntity(attacker, victim, instance);


                        }

                    }


                }

                Collection<MobEffectInstance> effects = attacker.getActiveEffects();


                for (MobEffectInstance mobEffectInstance : effects) {

                    if (mobEffectInstance.getEffect() instanceof ComplexEffect) {

                        ComplexEffect effect = (ComplexEffect) mobEffectInstance.getEffect();

                        effect.onHitEffect(attacker, victim, damageSource);

                    }


                }


            }


            ArrayList<ItemStack> victimItems = ItemEngine.getAllAppicableItems(victim);


            for (ItemStack victimStack : victimItems) {


                if (ItemEngine.isItemInitialized(victimStack)) {


                    for (ItemProperty property : ItemEngine.getItemProperties(victimStack)) {


                        property.onOwnerHit(attacker, victim, instance);


                    }

                }


            }


            Collection<MobEffectInstance> effects = victim.getActiveEffects();


            for (MobEffectInstance mobEffectInstance : effects) {

                if (mobEffectInstance.getEffect() instanceof ComplexEffect) {

                    ComplexEffect effect = (ComplexEffect) mobEffectInstance.getEffect();

                    effect.onOwnerHitEffect(attacker, victim, damageSource);

                }


            }

        }




    }







    public static void cycleAllPostHitEffects(LivingEntity attacker, LivingEntity victim, APDamageSource damageSource) {

        DamageInstance instance = damageSource.instance;


        Object device = instance.damageDevice;


        if (device != null) {

            if (attacker != null) {

                if (device instanceof Spell) {


                    CombatEngine.applySpellLifestal(attacker, victim, instance);


                }
                if (device instanceof ItemStack) {


                    CombatEngine.applyWeaponLifesteal(attacker, victim, instance);

                    ItemStack weapon = (ItemStack) device;


                    for (ItemProperty property : ItemEngine.getItemProperties(weapon)) {


                        property.postHitEntityInHand(attacker, victim, instance, weapon);


                    }
                }

                ArrayList<ItemStack> attackerItems = ItemEngine.getAllAppicableItems(attacker);
                for (ItemStack attackerStack : attackerItems) {


                    if (ItemEngine.isItemInitialized(attackerStack)) {


                        for (ItemProperty property : ItemEngine.getItemProperties(attackerStack)) {


                            property.postHitEntity(attacker, victim, instance);


                        }

                    }


                }

                Collection<MobEffectInstance> effects = attacker.getActiveEffects();


                for (MobEffectInstance mobEffectInstance : effects) {

                    if (mobEffectInstance.getEffect() instanceof ComplexEffect) {

                        ComplexEffect effect = (ComplexEffect) mobEffectInstance.getEffect();

                        effect.onpostHitEffect(attacker, victim, damageSource);

                    }


                }


            }


            ArrayList<ItemStack> victimItems = ItemEngine.getAllAppicableItems(victim);


            for (ItemStack victimStack : victimItems) {


                if (ItemEngine.isItemInitialized(victimStack)) {


                    for (ItemProperty property : ItemEngine.getItemProperties(victimStack)) {


                        property.onOwnerPostHit(attacker, victim, instance);


                    }

                }


            }


            Collection<MobEffectInstance> effects = victim.getActiveEffects();


            for (MobEffectInstance mobEffectInstance : effects) {

                if (mobEffectInstance.getEffect() instanceof ComplexEffect) {

                    ComplexEffect effect = (ComplexEffect) mobEffectInstance.getEffect();

                    effect.onOwnerpostHitEffect(attacker, victim, damageSource);

                }


            }

        }





    }








}
