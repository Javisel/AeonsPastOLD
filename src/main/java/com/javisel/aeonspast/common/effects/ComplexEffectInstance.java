package com.javisel.aeonspast.common.effects;

import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class ComplexEffectInstance {



    public final UUID instanceID;
    public final UUID source;
   public float power;
    public  float duration;
    public  float initialDuration;
    public float tickRate = 20;
    public boolean remove = false;

    ArrayList<StatusFlags> statusFlags = new ArrayList<>();

    public ComplexEffectInstance(UUID instanceID, UUID source, float power, float duration ) {
        this.instanceID = instanceID;
        this.source = source;
        this.power = power;
        this.duration = duration;
        this.initialDuration = duration;
    }
    public ComplexEffectInstance(UUID instanceID, UUID source, float power, float duration ,  StatusFlags... statusFlags) {
        this.instanceID = instanceID;
        this.source = source;
        this.power = power;
        this.duration = duration;
        this.initialDuration = duration;
        this.statusFlags= new ArrayList<StatusFlags>(Arrays.asList(statusFlags));
    }
    private ComplexEffectInstance(UUID instanceID, UUID source, float power, float duration, float initialDuration ) {
        this.instanceID = instanceID;
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


    public static final String STATUS_FLAGS = "status_flags";
    public CompoundTag toNBT() {


        CompoundTag tag = new CompoundTag();

        tag.putUUID("source",source);
        tag.putFloat("power",power);
        tag.putFloat("duration",duration);
        tag.putFloat("initialduration",initialDuration);

        CompoundTag statusFlagTag = new CompoundTag();

        int i = 0;
         for (StatusFlags flags : statusFlags) {

             statusFlagTag.putInt("flag_" + i,flags.id);

             i++;

         }

        tag.put(STATUS_FLAGS,statusFlagTag);


        return  tag;
    }

     public static ComplexEffectInstance fromNBT(CompoundTag tag) {




      ComplexEffectInstance instance = new ComplexEffectInstance(tag.getUUID("instanceid"), tag.getUUID("source"),tag.getFloat("power"),tag.getFloat("duration"),tag.getFloat("initialduration"));

       CompoundTag flags =  tag.getCompound(STATUS_FLAGS);

       for (String key : flags.getAllKeys())  {


           int id = flags.getInt(key);

           StatusFlags flag = StatusFlags.STATUS_FLAGS[id];

           instance.statusFlags.add(flag);



       }


        return  instance;


    }





}
