package com.javisel.aeonspast.common.items.properties.armor;

import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.items.properties.ArmorProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;

public class Gilded extends ArmorProperty {
    public Gilded() {
        setDisplayed();
    }

    @Override
    public boolean onOwnerPreHit(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance) {


        if (super.onOwnerPreHit(attacker, victim, damageInstance)) {

            if (attacker.getMobType() == MobType.UNDEAD) {

                damageInstance.preMitigationsAmount *= 0.975f;


            }


            return true;
        } else {

            return false;
        }
    }
}
