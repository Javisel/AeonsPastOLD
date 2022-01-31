package com.javisel.aeonspast.common.combat;

import com.javisel.aeonspast.common.items.ItemEngine;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class DamageEngine {


    public static float getDamagePostMitigations(double armor, float damage) {

        float mitigations = 1;
        if (armor < 0) {


            mitigations = (float) (2 - (armor / (100 + armor)));
        } else if (armor == 0) {
            return damage;
        } else if (armor > 0) {


            mitigations = (float) (armor / (100 + armor));
        }


        return mitigations * damage;


    }



   public DamageInstance calculateWeaponDamage(LivingEntity attacker, ItemStack weapon) {


        ItemEngine.initializeItem(attacker,weapon);




return null;





   }























}
