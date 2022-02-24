package com.javisel.aeonspast.common.items.properties.weapon;

import com.javisel.aeonspast.common.combat.damage.instances.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.combat.damage.sources.APEntityDamageSource;
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


                DamageInstance proc = DamageInstance.getGenericProcInstance(DamageTypeEnum.RADIANT, ((damageInstance.getBaseAmount()) * 0.10) );

                proc.setDamageDevice(damageInstance.getDamageDevice());

                APEntityDamageSource source = new APEntityDamageSource(attacker instanceof Player ? "player" : "mob", proc, attacker);

                victim.hurt(source, (float) proc.getPreMitigatedValue());

            }


            return true;

        }
        return false;
    }
}
