package com.javisel.aeonspast.common.items.properties.weapon;

import com.javisel.aeonspast.common.combat.APDamageSource;
import com.javisel.aeonspast.common.combat.APEntityDamageSource;
import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.damagetypes.APDamageSubType;
import com.javisel.aeonspast.common.items.properties.WeaponProperty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class Gilded extends WeaponProperty {


    @Override
    public void onHitEntity(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack) {
        super.onHitEntity(attacker, victim, damageInstance, stack);


        if (victim.getMobType() == MobType.UNDEAD) {



            float strength = damageInstance.isCritical ? 0.05f : 0.1f;


            DamageInstance proc = DamageInstance.genericProcDamage(APDamageSubType.MAGIC,(damageInstance.getAmount() * strength) * damageInstance.procPower);



            System.out.println("On-hit, baby!");

            DamageSource source = new APEntityDamageSource(attacker instanceof Player ? "player" : "mob",proc,attacker);

            victim.hurt(source, (float) proc.getAmount());


        }


    }
}
