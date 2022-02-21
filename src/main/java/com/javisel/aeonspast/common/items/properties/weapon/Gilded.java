package com.javisel.aeonspast.common.items.properties.weapon;

import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.combat.damagesource.APEntityDamageSource;
import com.javisel.aeonspast.common.items.properties.WeaponProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class Gilded extends WeaponProperty {


    public Gilded() {
        setDisplayed();
    }

    @Override
    public boolean onHitEntityInHand(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack) {
        if (super.onHitEntityInHand(attacker, victim, damageInstance, stack)) {


            if (victim.getMobType() == MobType.UNDEAD) {


                DamageInstance proc =DamageInstance.getGenericProcDamage(DamageTypeEnum.RADIANT, ((damageInstance.getPreMitigationsAmount()) * 0.10) * damageInstance.procPower);

                proc.damageDevice=damageInstance.damageDevice;

                APEntityDamageSource source = new APEntityDamageSource(attacker instanceof Player ? "player" : "mob", proc, attacker);

                victim.hurt(source, (float) proc.getPreMitigationsAmount());

            }


            return true;

        }
        return false;
    }
}
