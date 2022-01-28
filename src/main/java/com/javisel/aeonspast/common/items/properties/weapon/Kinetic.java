package com.javisel.aeonspast.common.items.properties.weapon;

import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.items.properties.WeaponProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class Kinetic  extends WeaponProperty {


    @Override
    public void onAttackEntity(LivingEntity attacker, LivingEntity livingEntity, DamageInstance damageInstance, ItemStack stack) {
        super.onAttackEntity(attacker, livingEntity, damageInstance, stack);
    }
}
