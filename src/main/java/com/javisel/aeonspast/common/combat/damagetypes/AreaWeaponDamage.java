package com.javisel.aeonspast.common.combat.damagetypes;

import com.javisel.aeonspast.common.combat.APDamageInstance;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

public class AreaWeaponDamage extends APDamageInstance {


    ItemStack weapon;

    public AreaWeaponDamage(APDamageSubType subType, float amount, @Nullable ItemStack weapon) {
            super(subType,amount);

            this.weapon=weapon;
            this.doesProcWeaponHitEffects=true;
            this.isArea=true;
            this.procPower=0.33f;



    }



}
