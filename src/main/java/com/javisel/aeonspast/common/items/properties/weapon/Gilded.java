package com.javisel.aeonspast.common.items.properties.weapon;

import com.javisel.aeonspast.common.combat.APDirectEntityDamageSource;
import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.damagetypes.APDamageSubType;
import com.javisel.aeonspast.common.items.properties.WeaponProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class Gilded extends WeaponProperty {


    @Override
    public boolean onHitEntityInHand(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack) {
        if (super.onHitEntityInHand(attacker, victim, damageInstance, stack )) {

            System.out.println("Super lets attack!");

            if (victim.getMobType() == MobType.UNDEAD) {


                DamageInstance proc = new DamageInstance(APDamageSubType.MAGIC, (damageInstance.getPreMitigationsAmount()) * damageInstance.procPower);


                System.out.println("Proc: " + proc.getPreMitigationsAmount());


                APDirectEntityDamageSource source = new APDirectEntityDamageSource(attacker instanceof Player ? "player" : "mob", proc, attacker);

                victim.hurt(source, (float) proc.getPreMitigationsAmount());

             }



            return true;

        }
        System.out.println("Super denied!");
        return  false;
     }
}
