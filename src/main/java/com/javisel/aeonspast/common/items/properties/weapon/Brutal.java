package com.javisel.aeonspast.common.items.properties.weapon;

import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.items.properties.WeaponProperty;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class Brutal extends WeaponProperty {


    @Override
    public boolean onPreHitEntity(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack) {
        if (super.onPreHitEntity(attacker, victim, damageInstance, stack)){



            boolean doesproc =   attacker.fallDistance > 0.0F && !attacker.isOnGround() && !attacker.onClimbable() && !attacker.isInWater() && !attacker.hasEffect(MobEffects.BLINDNESS) && !attacker.isPassenger() && victim instanceof LivingEntity;


            if (doesproc) {

                System.out.println("Does Proc Boost!!");
                damageInstance.amount*=1.15;

            }



            return true;
        }


        return false;

    }
}
