package com.javisel.aeonspast.common.combat;

import com.javisel.aeonspast.common.combat.damage.instances.DamageInstance;
import com.javisel.aeonspast.common.combat.damage.instances.DamageModifier;
import com.javisel.aeonspast.common.combat.damage.sources.APDamageSource;
import com.javisel.aeonspast.common.config.weapon.WeaponData;
import com.javisel.aeonspast.common.effects.ComplexEffect;
import com.javisel.aeonspast.common.effects.IDamageStatus;
import com.javisel.aeonspast.common.events.EventFactory;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.items.properties.WeaponProperty;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.server.ServerHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;

public class CombatEngine {


    public static void attemptCriticalHit(LivingEntity attacker, LivingEntity victim, DamageInstance instance) {


        double chance = attacker.getAttributeValue(AttributeRegistration.CRITICAL_CHANCE.get());
        if (rollCriticalNumber(attacker,chance, 101)) {

            applyCriticalHits(attacker, victim, instance);


            double reRolls = chance-100;
            while (reRolls > 0) {


                if (rollCriticalNumber(attacker,reRolls,reRolls+101)) {

                     applyCriticalHits(attacker,victim,instance);
                 }





                reRolls-=100;


            }






        }


    }

    public static boolean rollCriticalNumber(LivingEntity roller, double input, double bind) {

        Random random = roller.getRandom();


        double chance = random.nextDouble(bind);


        return chance <= input;


    }

    public static void applyCriticalHits(LivingEntity attacker, LivingEntity victim, DamageInstance instance) {


        double critDamage =  instance.getBaseAmount() * ( attacker.getAttributeValue(AttributeRegistration.CRITICAL_DAMAGE.get()));





        DamageModifier crit = new DamageModifier(UUID.fromString("a66f3903-d7db-4787-9a34-b7eded3435ab"),"aeonspast:critical_strike", DamageModifier.Operation.ADDITON,critDamage);

        instance.addCritical(crit);


    }


    public static void attemptStatusHit(LivingEntity attacker, LivingEntity victim, DamageInstance instance) {


        if (instance.getDamageType().getStatusEffect() == null) {
            return;
        }
        if (rollStatusHit(attacker, instance.doesDeviceMatch(attacker.getMainHandItem()))) {

            applyStatusHit(attacker, victim, instance);
        }


    }


    public static boolean rollStatusHit(LivingEntity applier, boolean excludeWeapon) {

        Random random = applier.getRandom();


        float attempt = random.nextFloat(101);

        double chance = applier.getAttributeValue(AttributeRegistration.STATUS_CHANCE.get());

        if (excludeWeapon) {


            if (applier.getAttribute(AttributeRegistration.STATUS_CHANCE.get()).getModifier(UUID.fromString(WeaponData.WEAPON_MOD_ID)) != null) {
                chance -= applier.getAttribute(AttributeRegistration.STATUS_CHANCE.get()).getModifier(UUID.fromString(WeaponData.WEAPON_MOD_ID)).getAmount();

            }

        }


        return attempt <= chance;
    }

    public static void applyStatusHit(LivingEntity attacker, LivingEntity victim, DamageInstance instance) {


        ComplexEffect complexEffect = (ComplexEffect) instance.getDamageType().getStatusEffect();

        IDamageStatus damageStatus = (IDamageStatus) complexEffect;


        complexEffect.addnewComplexInstance(damageStatus.getDefaultDamageInstance(attacker, victim, instance), victim);

        instance.setStatus();


    }


    public static void applyWeaponLifesteal(LivingEntity attacker, LivingEntity victim, DamageInstance instance) {

        double amount = instance.getMitigatedAmount() * (attacker.getAttributeValue(AttributeRegistration.WEAPON_LIFESTEAL.get()) / 100);
        amount *= attacker.getAttributeValue(AttributeRegistration.HEALING_INTAKE.get());
        amount = EventFactory.onLifesteal(attacker, victim, (float) amount);


        if (amount > 0) {


            attacker.heal((float) amount);
        }


    }

    public static void applySpellLifestal(LivingEntity attacker, LivingEntity victim, DamageInstance instance) {

        double amount = instance.getMitigatedAmount() * (attacker.getAttributeValue(AttributeRegistration.SPELL_LIFESTEAL.get()) / 100);
        amount *= attacker.getAttributeValue(AttributeRegistration.HEALING_INTAKE.get());
        amount = EventFactory.onLifesteal(attacker, victim, (float) amount);


        if (amount > 0) {


            attacker.heal((float) amount);
        }


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

        double total = (baseDamage + physicalPower) * (1 + (rangeBonus / 100));

        total *= attacker.getAttributeValue(AttributeRegistration.DAMAGE_OUTPUT.get());


        WeaponData weaponData = ServerHandler.WEAPON_STATISTICS_LOADER.getWeaponData(weapon);


        DamageInstance instance = DamageInstance.genericWeaponDamage(weaponData.getWeapon_type().getDamageType(), total, weapon, !weaponData.isRanged());

        return instance;


    }


    /*
        Ranged Damage baed on

     */
    public static DamageInstance calculateFiredRangedDamage(LivingEntity attacker, Projectile projectile, ItemStack weapon, float rangedPower) {

        double baseDamage = attacker.getAttributeValue(AttributeRegistration.WEAPON_POWER.get());
        double physicalPower = attacker.getAttributeValue(AttributeRegistration.PHYSICAL_POWER.get());
        double rangeBonus = attacker.getAttributeValue(AttributeRegistration.RANGED_POWER.get());
        if (rangedPower < 0) {
            rangedPower = 0;
        }

        double total = (baseDamage + physicalPower) * (1 + (rangeBonus / 100));

        total *= rangedPower;

        total *= attacker.getAttributeValue(AttributeRegistration.DAMAGE_OUTPUT.get());

        WeaponData weaponData = ServerHandler.WEAPON_STATISTICS_LOADER.getWeaponData(weapon);

        DamageInstance instance = DamageInstance.genericWeaponDamage(weaponData.getWeapon_type().getDamageType(), total, weapon, !weaponData.isRanged());
        ;

        return instance;


    }


    public static double getMitigatedDamage(LivingEntity victim, DamageInstance instance) {


        double baseamount = instance.getPreMitigatedValue();


        double armor = 0;
        double damageMod = 1;
        damageMod += victim.getAttributeValue(AttributeRegistration.DAMAGE_INTAKE.get()) / 100;

        if (instance.getDamageType().isAbsolute()) {

            return baseamount;
        } else {


            if (!instance.isMagical()) {

                armor = victim.getAttribute(AttributeRegistration.ARMOR.get()).getValue();
                armor *= 1 + victim.getAttribute(AttributeRegistration.ARMOR_TOUGHNESS.get()).getValue();

                damageMod -= victim.getAttributeValue(AttributeRegistration.PHYSICAL_MITIGATIONS.get()) / 100;

            } else {


                armor += victim.getAttribute(AttributeRegistration.MAGIC_RESISTANCE.get()).getValue();
                damageMod = 1 - victim.getAttributeValue(AttributeRegistration.MAGICAL_MITIGATIONS.get()) / 100;
            }


        }


        baseamount = getDamagePostMitigations(armor, baseamount);

        baseamount *= damageMod;


        return baseamount;


    }

    public static boolean cycleAllPreHitEffects(LivingEntity attacker, LivingEntity victim, APDamageSource damageSource) {


        boolean result = true;
        if (EventFactory.onDamageHit(attacker, victim, damageSource)) {

            return false;

        }

        if (damageSource.getInstance().canCritical()) {


            CombatEngine.attemptCriticalHit(attacker, victim, damageSource.instance);
        }


        DamageInstance instance = damageSource.instance;


        Object device = instance.getDamageDevice();


        if (attacker != null) {

            if (device != null) {

                if (device instanceof SpellStack) {


                    //TODO Spellhit effects

                }
                if (device instanceof ItemStack weapon) {


                    for (ItemProperty property : ItemEngine.getItemProperties(weapon)) {


                        result = property.onPreHitEntityInHand(attacker, victim, instance, weapon);


                    }
                }

            }


            ArrayList<ItemStack> attackerItems = ItemEngine.getAllAppicableItems(attacker);
            for (ItemStack attackerStack : attackerItems) {


                if (ItemEngine.isItemInitialized(attackerStack)) {


                    for (ItemProperty property : ItemEngine.getItemProperties(attackerStack)) {


                        result = property.onPreHitEntity(attacker, victim, instance);


                    }

                }


            }

            Collection<MobEffectInstance> effects = attacker.getActiveEffects();


            for (MobEffectInstance mobEffectInstance : effects) {

                if (mobEffectInstance.getEffect() instanceof ComplexEffect effect) {

                    result = effect.onpreHitEffect(attacker, victim, damageSource);

                }


            }


        }

        ArrayList<ItemStack> victimItems = ItemEngine.getAllAppicableItems(victim);


        for (ItemStack victimStack : victimItems) {


            if (ItemEngine.isItemInitialized(victimStack)) {


                for (ItemProperty property : ItemEngine.getItemProperties(victimStack)) {


                    result = property.onOwnerPreHit(attacker, victim, instance);


                }

            }


        }


        Collection<MobEffectInstance> effects = victim.getActiveEffects();


        for (MobEffectInstance mobEffectInstance : effects) {

            if (mobEffectInstance.getEffect() instanceof ComplexEffect effect) {

                result = effect.onOwnerpreHitEffect(attacker, victim, damageSource);

            }


        }


        return result;
    }

    public static void cycleAllHitEffects(LivingEntity attacker, LivingEntity victim, APDamageSource damageSource) {
        DamageInstance instance = damageSource.instance;


        Object device = instance.getDamageDevice();

        if (EventFactory.onDamageHit(attacker, victim, damageSource)) {

            return;

        }


        victim.hurt(damageSource, (float) damageSource.instance.getPreMitigatedValue());


        if (attacker != null) {

            if (device != null) {

                if (device instanceof Spell) {


                }
                if (device instanceof ItemStack weapon) {


                    if (instance.isMelee()) {

                        weapon.hurt(1, attacker.getRandom(), attacker instanceof ServerPlayer ? (ServerPlayer) attacker : null);

                    }


                    for (ItemProperty property : ItemEngine.getItemProperties(weapon)) {


                        property.onHitEntityInHand(attacker, victim, instance, weapon);


                    }
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
            Collection<MobEffectInstance> attackerActiveEffects = attacker.getActiveEffects();

            if (!attackerActiveEffects.isEmpty()) {

                for (MobEffectInstance mobEffectInstance : attacker.getActiveEffects()) {

                    if (mobEffectInstance.getEffect() instanceof ComplexEffect effect) {

                        effect.onHitEffect(attacker, victim, damageSource);

                    }


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


        if (!effects.isEmpty()) {

            for (MobEffectInstance mobEffectInstance : effects) {

                if (mobEffectInstance.getEffect() instanceof ComplexEffect effect) {

                    effect.onOwnerHitEffect(attacker, victim, damageSource);

                }


            }


        }
    }

    public static void cycleAllPostHitEffects(LivingEntity attacker, LivingEntity victim, APDamageSource damageSource) {

        DamageInstance instance = damageSource.instance;


        Object device = instance.getDamageDevice();


        if (attacker != null) {

            if (device != null) {

                if (device instanceof Spell) {


                    CombatEngine.applySpellLifestal(attacker, victim, instance);


                }
                if (device instanceof ItemStack weapon) {


                    CombatEngine.applyWeaponLifesteal(attacker, victim, instance);


                    for (ItemProperty property : ItemEngine.getItemProperties(weapon)) {


                        property.postHitEntityInHand(attacker, victim, instance, weapon);


                    }
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

                if (mobEffectInstance.getEffect() instanceof ComplexEffect effect) {

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


        Collection<MobEffectInstance> victimActiveEffects = victim.getActiveEffects();


        for (MobEffectInstance mobEffectInstance : victimActiveEffects) {

            if (mobEffectInstance.getEffect() instanceof ComplexEffect effect) {

                effect.onOwnerpostHitEffect(attacker, victim, damageSource);

            }


        }


    }

    public void swingWeapon(LivingEntity attacker, ItemStack stack) {


        ArrayList<ItemProperty> properties = ItemEngine.getItemProperties(stack);

        if (EventFactory.onSwingItem(attacker, stack)) {


            if (!properties.isEmpty()) {


                for (ItemProperty property : properties) {


                    if (property instanceof WeaponProperty weaponProperty) {


                        weaponProperty.onSwingItem(attacker, stack);


                    }


                }


            }


        }


    }


}
