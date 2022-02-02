package com.javisel.aeonspast.common.items.properties;

import com.javisel.aeonspast.common.combat.DamageInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class WeaponProperty extends ItemProperty {





    public boolean onPreHitEntity(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack) {

        return true;
    }


    public void onHitEntity(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack) {


    }







    public boolean onSwingItem(LivingEntity swinger, ItemStack stack) {


        System.out.println("ON SWING!");

        return true;
    }


}
