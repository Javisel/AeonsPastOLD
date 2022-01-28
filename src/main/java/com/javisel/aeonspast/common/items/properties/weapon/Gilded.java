package com.javisel.aeonspast.common.items.properties.weapon;

import com.javisel.aeonspast.common.combat.APDamageSource;
import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.damagetypes.APDamageSubType;
import com.javisel.aeonspast.common.items.properties.WeaponProperty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;

public class Gilded extends WeaponProperty {


    @Override
    public void onHitEntity(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack) {
        super.onHitEntity(attacker, victim, damageInstance, stack);


        if (victim.getMobType() == MobType.UNDEAD) {




            DamageInstance proc = DamageInstance.genericProcDamage(APDamageSubType.MAGIC,damageInstance.getAmount() * 0.10f);

            proc.isCritical=damageInstance.isCritical;


            DamageSource source = new APDamageSource("generic.onhit.magic.Gilded",proc);

            victim.hurt(source, proc.getAmount());


        }


    }
}
