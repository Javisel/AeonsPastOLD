package com.javisel.aeonspast.common.effects.damageeffects;

import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import com.javisel.aeonspast.common.effects.ComplexStatChangeEffect;
import com.javisel.aeonspast.common.effects.DamageStatusDebuff;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

public class StatChangeDamageDebuff extends ComplexStatChangeEffect implements DamageStatusDebuff {


    private final DamageTypeEnum damage_type;

    public StatChangeDamageDebuff(Attribute attribute, UUID effectId,   int colorCode, AttributeModifier.Operation operation, DamageTypeEnum damage_type) {
        super(attribute, effectId, MobEffectCategory.HARMFUL, colorCode, operation);
        this.damage_type = damage_type;
    }


    @Override
    public DamageTypeEnum getDamageType() {
        return damage_type;
    }

    @Override
    public void applyFromDamage(LivingEntity source, LivingEntity target, DamageInstance damageInstance) {

    }


}
