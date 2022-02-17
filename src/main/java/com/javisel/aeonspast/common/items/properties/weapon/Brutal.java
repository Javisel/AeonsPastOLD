package com.javisel.aeonspast.common.items.properties.weapon;

import com.javisel.aeonspast.common.combat.CombatEngine;
import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.items.properties.WeaponProperty;
import com.javisel.aeonspast.common.registration.EffectRegistration;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class Brutal extends WeaponProperty {


    @Override
    public boolean onPreHitEntityInHand(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack) {
        if (super.onPreHitEntityInHand(attacker, victim, damageInstance, stack)) {


            boolean doesproc = !attacker.hasEffect(EffectRegistration.BRUTAL_COOLDOWN.get()) && attacker.fallDistance > 0.0F && !attacker.isOnGround() && !attacker.onClimbable() && !attacker.isInWater() && !attacker.hasEffect(MobEffects.BLINDNESS) && !attacker.isPassenger() && victim instanceof LivingEntity;


            if (doesproc) {

                damageInstance.preMitigationsAmount*=1.25;
               // CombatEngine.applyCrits(attacker,victim,damageInstance);
                //applyBrutalCooldown(attacker);
            }




            return true;
        }


        return false;

    }


    public void applyBrutalCooldown(LivingEntity attacker) {

        double baseDuration = 140;
        double attackSpeed = attacker.getAttributeValue(Attributes.ATTACK_SPEED);

        baseDuration -= (attackSpeed * 40);


        if (baseDuration < 1) {
            baseDuration=1;
        }
        attacker.addEffect(new MobEffectInstance(EffectRegistration.BRUTAL_COOLDOWN.get(),(int)baseDuration,0,false,false,true));

    }


}
