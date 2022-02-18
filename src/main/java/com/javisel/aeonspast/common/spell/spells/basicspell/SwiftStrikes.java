package com.javisel.aeonspast.common.spell.spells.basicspell;

import com.javisel.aeonspast.common.effects.AttackSpeedBuff;
import com.javisel.aeonspast.common.effects.ComplexEffectInstance;
import com.javisel.aeonspast.common.effects.StatusEffect;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.registration.EffectRegistration;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.entity.LivingEntity;

import java.util.UUID;

public class SwiftStrikes extends Spell {
    public SwiftStrikes( ) {
        super(15,  50  , SpellRank.SKILL_BASIC);
    }



    @Override
    public void cast(LivingEntity entity, SpellStack stack) {



        float base = 0.30f;

        float powerScaling = (float) (entity.getAttributeValue(AttributeRegistration.MAGICAL_POWER.get()) * 0.015f);

        float total = base + powerScaling;

       StatusEffect effect = (StatusEffect) EffectRegistration.ATTACK_SPEED_BUFF.get();

        ComplexEffectInstance instance = new ComplexEffectInstance(UUID.fromString("07242767-2dc9-465d-a527-fc76a65f1914"), entity.getUUID(),total,100 );
       effect.addnewComplexInstance(instance,entity);



    }
}
