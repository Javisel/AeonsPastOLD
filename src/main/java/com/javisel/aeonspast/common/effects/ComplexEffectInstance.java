package com.javisel.aeonspast.common.effects;

import net.minecraft.nbt.CompoundTag;

import java.util.UUID;

public class ComplexEffectInstance {


    protected final UUID source;
   protected float power;
    protected  float duration;
    protected  float initialDuration;
    public boolean remove = false;
    public ComplexEffectInstance(UUID source, float power, float duration, float initialDuration) {
        this.source = source;
        this.power = power;
        this.duration = duration;
        this.initialDuration = initialDuration;
    }


    public UUID getSource() {
        return source;
    }

    public float getPower() {
        return power;
    }

    public float getDuration() {
        return duration;
    }

    public float getInitialDuration() {
        return initialDuration;
    }


    public CompoundTag toNBT() {


        CompoundTag tag = new CompoundTag();

        tag.putUUID("source",source);
        tag.putFloat("power",power);
        tag.putFloat("duration",duration);
        tag.putFloat("initialduration",initialDuration);
        return  tag;
    }

    public static ComplexEffectInstance fromNBT(CompoundTag tag) {







        return  new ComplexEffectInstance(tag.getUUID("source"),tag.getFloat("power"),tag.getFloat("duration"),tag.getFloat("initialduration"));
    }





}
