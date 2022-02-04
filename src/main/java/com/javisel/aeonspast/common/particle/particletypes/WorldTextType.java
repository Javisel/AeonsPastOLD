package com.javisel.aeonspast.common.particle.particletypes;

import com.javisel.aeonspast.common.particle.particleoptions.WorldTextOptions;
import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.MutableComponent;

public class WorldTextType extends ParticleType implements WorldTextOptions {


    private MutableComponent component;


    public WorldTextType(boolean overrideLimiter, ParticleOptions.Deserializer deserializer) {
        super(overrideLimiter, deserializer);

    }


    @Override
    public Codec codec() {
        return ParticleTypes.CODEC;
    }

    public MutableComponent getComponent() {
        return component;
    }

    @Override
    public ParticleType<?> getType() {
        return this;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf friendlyByteBuf) {

    }

    @Override
    public String writeToString() {
        return component.toString();
    }
}
