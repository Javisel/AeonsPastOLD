package com.javisel.aeonspast.common.items.properties.weapon;

import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.items.properties.WeaponProperty;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class Kinetic  extends WeaponProperty {

    @Override
    public void onHitEntity(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack) {
        super.onHitEntity(attacker, victim, damageInstance, stack);


      double   i = attacker.getAttributeValue(Attributes.ATTACK_KNOCKBACK);

        if (victim instanceof LivingEntity) {
            ((LivingEntity)victim).knockback((double)((float)i * 0.5F), (double) Mth.sin(attacker.getYRot() * ((float)Math.PI / 180F)), (double)(-Mth.cos(attacker.getYRot() * ((float)Math.PI / 180F))));
        } else {
            victim.push((double)(-Mth.sin(attacker.getYRot() * ((float)Math.PI / 180F)) * (float)i * 0.5F), 0.1D, (double)(Mth.cos(attacker.getYRot() * ((float)Math.PI / 180F)) * (float)i * 0.5F));
        }

        System.out.println("BACK OFF!");
        attacker.setDeltaMovement(attacker.getDeltaMovement().multiply(0.6D, 1.0D, 0.6D));
        attacker.setSprinting(false);
    }
}
