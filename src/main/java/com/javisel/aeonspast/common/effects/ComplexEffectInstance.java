package com.javisel.aeonspast.common.effects;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class ComplexEffectInstance {

    private final ComplexEffect effect;

    private UUID source;
     private boolean isVisible = true;
    private final float power;
    private final float duration;
    public boolean removal=false;
    public ComplexEffectInstance(UUID source, ComplexEffect effect, boolean isVisible, float power, float duration) {
        this.source = source;
        this.effect = effect;
        this.isVisible = isVisible;
        this.power = power;
        this.duration = duration;
    }

    public ComplexEffectInstance(ComplexEffect effect, float power, float duration, UUID source) {

        this.effect=effect;
        this.power=power;
        this.duration=duration;
        this.source =  source;



    }

    public void setInvisible(){

        isVisible=false;
    }

    public ComplexEffectInstance(ComplexEffect effect, float power, float duration ) {

        this.effect=effect;
        this.power=power;
        this.duration=duration;



    }
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("effect",effect.getRegistryName().toString());

        if (source!=null){

            tag.putString("source",source.toString());
        }

        tag.putFloat("power",power);
        tag.putFloat("duration",duration);
        return  tag;
    }

    public static ComplexEffectInstance fromNBT(CompoundTag tag) {


        ResourceLocation location = new ResourceLocation(tag.getString("effect"));
        ComplexEffectInstance effectInstance = new ComplexEffectInstance(ComplexEffect.getEffectByLocation(location),tag.getFloat("power"),tag.getFloat("duration"));


        if (tag.contains("source")) {

            effectInstance.source = UUID.fromString(tag.getString("source"));


        }


        return  effectInstance;
    }



    public boolean attemptApply(LivingEntity target) {


        return effect.attemptApply(target,this);
    }

    public void apply(LivingEntity target) {

        effect.apply(target,this);

    }


    public void remove(LivingEntity target) {


        effect.remove(target,this);

    }


    public void tick(LivingEntity target) {

        effect.tick(target,this);


    }



    public ComplexEffect getEffect() {
        return effect;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public float getPower() {
        return power;
    }

    public float getDuration() {
        return duration;
    }

    public ArrayList<Entity> getallSourceEntities(Level level) {


        return  new ArrayList<>();

    }

    @Nullable
    public MobEffect getDisplayEffect() {




        return isVisible ? effect.getDisplayEffect() : null;
    }





}
