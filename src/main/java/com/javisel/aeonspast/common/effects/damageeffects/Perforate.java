package com.javisel.aeonspast.common.effects.damageeffects;

import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import com.javisel.aeonspast.common.combat.damagesource.APEntityDamageSource;
import com.javisel.aeonspast.common.effects.ComplexEffectInstance;
import com.javisel.aeonspast.common.effects.DamageStatusDebuff;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

public class Perforate extends StatChangeDamageDebuff {

    private static final UUID stat_id = UUID.fromString("fff311d2-c709-422a-b7a6-7c1262b55575");
    public Perforate(  ) {
        super(AttributeRegistration.DAMAGE_INTAKE.get(), stat_id,0, AttributeModifier.Operation.ADDITION,DamageTypeEnum.PUNCTURE);
    }


    @Override
    public void applyFromDamage(LivingEntity source, LivingEntity target,  DamageInstance instance) {


        UUID sourceid = source !=null ? source.getUUID() : null;
        ComplexEffectInstance effectInstance = new ComplexEffectInstance(UUID.randomUUID(),sourceid,5,100,100);



        addnewComplexInstance(effectInstance,target);

    }


}
