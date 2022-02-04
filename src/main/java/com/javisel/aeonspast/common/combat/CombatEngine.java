package com.javisel.aeonspast.common.combat;

import com.javisel.aeonspast.common.events.EventFactory;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.items.properties.WeaponProperty;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class CombatEngine {


    public static boolean attemptCriticalHit(LivingEntity critter) {

        Random random = critter.getRandom();


        float chance = random.nextFloat(101);


        return chance <= critter.getAttributeValue(AttributeRegistration.CRITICAL_CHANCE.get());


    }

    public static void onCrit(LivingEntity attacker, LivingEntity victim, DamageInstance criticalInstance) {


        criticalInstance.preMitigationsAmount = DamageEngine.applyCriticalInstance(attacker, criticalInstance);
        criticalInstance.isCritical = true;


    }

    public static void applyWeaponLifesteal(LivingEntity attacker, LivingEntity victim, DamageInstance instance) {

        double amount = instance.getPreMitigationsAmount() * (attacker.getAttributeValue(AttributeRegistration.WEAPON_LIFESTEAL.get()) / 100);
        amount *= attacker.getAttributeValue(AttributeRegistration.HEALING_INTAKE.get());
        amount = EventFactory.onLifesteal(attacker, victim, (float) amount);

        if (instance.isArea) {

            amount *= 0.33;


        }


        if (amount > 0) {


            attacker.heal((float) amount);
        }


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

    public boolean onDamageSet(APDamageSource apDamagesource, LivingEntity victim) {


        return false;
    }

    public void doDamageCalculations(APDamageSource damageSource, LivingEntity victim) {


    }

    public void onDamagePostApplication(APDamageSource damageSource, LivingEntity livingEntity) {


    }

    public void fireWeapon(LivingEntity attacker, ItemStack stack) {


    }

    public void onProjectileHit(LivingEntity attacker, LivingEntity victim, Projectile projectile) {


    }

    public void attackEntity(LivingEntity attacker, LivingEntity victim, ItemStack weapon) {


    }


}
