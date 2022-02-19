package com.javisel.aeonspast.common.spell.spells.basicspell;

import com.javisel.aeonspast.common.effects.AttackSpeedBuff;
import com.javisel.aeonspast.common.effects.ComplexEffectInstance;
import com.javisel.aeonspast.common.effects.StatusEffect;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.registration.EffectRegistration;
import com.javisel.aeonspast.common.registration.SoundEventRegistration;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class SwiftStrikes extends Spell {

    private static final UUID SWIFT_STRIKES_BUFF = UUID.fromString("07242767-2dc9-465d-a527-fc76a65f1914");
    public SwiftStrikes( ) {
        super(15*20,  50  , SpellRank.SKILL_BASIC);
    }



    @Override
    public void cast(LivingEntity entity, SpellStack stack) {



        entity.level.playSound(null,entity, SoundEventRegistration.SWIFT_STRIKES_BUFF.get(), entity instanceof Player ? SoundSource.PLAYERS : SoundSource.NEUTRAL,1,1);

        float base = 0.30f;

        float powerScaling = (float) (entity.getAttributeValue(AttributeRegistration.MAGICAL_POWER.get()) * 0.015f);

        float total = base + powerScaling;

       StatusEffect effect = (StatusEffect) EffectRegistration.ATTACK_SPEED_BUFF.get();

        ComplexEffectInstance instance = new ComplexEffectInstance(SWIFT_STRIKES_BUFF, entity.getUUID(),total,140 );
       effect.addnewComplexInstance(instance,entity);



    }
}
