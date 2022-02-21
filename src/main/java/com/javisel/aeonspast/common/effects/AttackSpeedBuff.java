package com.javisel.aeonspast.common.effects;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class AttackSpeedBuff extends ComplexStatChangeEffect {


    public AttackSpeedBuff() {

        super(Attributes.ATTACK_SPEED, UUID.fromString("267fde62-70a5-4df4-b074-c298cbe8eab2"), MobEffectCategory.BENEFICIAL, 0, AttributeModifier.Operation.MULTIPLY_BASE);
    }


}
