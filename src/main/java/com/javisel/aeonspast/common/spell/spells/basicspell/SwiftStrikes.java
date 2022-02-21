package com.javisel.aeonspast.common.spell.spells.basicspell;

import com.javisel.aeonspast.common.effects.ComplexEffectInstance;
import com.javisel.aeonspast.common.effects.StatusEffect;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.registration.EffectRegistration;
import com.javisel.aeonspast.common.registration.SoundEventRegistration;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class SwiftStrikes extends Spell {

    private static final UUID SWIFT_STRIKES_BUFF = UUID.fromString("07242767-2dc9-465d-a527-fc76a65f1914");

    public SwiftStrikes() {
        super(15 * 20, 50, SpellRank.SKILL_BASIC);
    }


    @Override
    public void cast(LivingEntity entity, SpellStack stack) {


        Level level = entity.getLevel();

        entity.level.playSound(null, entity, SoundEventRegistration.SWIFT_STRIKES_CAST.get(), entity instanceof Player ? SoundSource.PLAYERS : SoundSource.NEUTRAL, 1, 1);

        float base = 0.30f;

        float powerScaling = (float) (entity.getAttributeValue(AttributeRegistration.MAGICAL_POWER.get()) * 0.015f);

        float total = base + powerScaling;

        StatusEffect effect = (StatusEffect) EffectRegistration.ATTACK_SPEED_BUFF.get();

        ComplexEffectInstance instance = new ComplexEffectInstance(SWIFT_STRIKES_BUFF, entity.getUUID(), total, 140);
        effect.addnewComplexInstance(instance, entity);



        if (!level.isClientSide) {

            ServerLevel serverLevel = (ServerLevel) level;

            double x =  entity.getX();
            double y = (entity.getY() + entity.getBbHeight() /2)  ;
            double z = entity.getZ();

             for (int i = 0; i < 360; i++) {



                double angle = Math.toRadians(i);
                double radius = 1.0d;
                double vx = radius * Math.cos(angle);
                double vz = radius * Math.sin(angle);


                serverLevel.sendParticles(ParticleTypes.CLOUD,x+vx,y,z+vz,0,0,0,0,1);



            }
        }
    }
}
