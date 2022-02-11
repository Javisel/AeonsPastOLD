package com.javisel.aeonspast.common.effects;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class ComplexEffect extends ForgeRegistryEntry<ComplexEffect> {


    private RegistryObject<MobEffect> displayEffect;
    private StackPolicy stackPolicy;


    public ComplexEffect(RegistryObject<MobEffect> displayEffect, StackPolicy stackPolicy) {
        this.displayEffect = displayEffect;
        this.stackPolicy = stackPolicy;
    }

    public boolean attemptApply(LivingEntity target, ComplexEffectInstance instance) {


        IEntityData entityData = Utilities.getEntityData(target);
        ArrayList<ComplexEffectInstance> instances = entityData.getInstances();

        ArrayList<ComplexEffectInstance> sameType = new ArrayList<>();


                for (ComplexEffectInstance i : instances) {

                    if (i.getEffect()==this) {

                        sameType.add(i);
                    }

                }




        switch (stackPolicy) {
            case OLDEST_PRIORITY -> {


               return  sameType.isEmpty();



            }
            case NEWEST_PRIORITY -> {

                if (!sameType.isEmpty()) {

                    for (ComplexEffectInstance s : sameType) {

                        instances.remove(s);

                    }

                }
                apply(target,instance);



            }
            case REFRESH_OLDEST -> {
            }
            case REFRESH_NEWEST -> {
            }
            case STACK_DURATION -> {
            }
            case STACK_POWER -> {
            }
            case STACK_DURATION_AND_POWER -> {
            }
        }











        return true;


    }
    public void apply(LivingEntity target, ComplexEffectInstance instance) {





    }

    public void remove(LivingEntity target, ComplexEffectInstance instance) {



    }

    public void tick(LivingEntity target, ComplexEffectInstance instance) {



    }

    public MobEffect getDisplayEffect() {


        return displayEffect != null ? displayEffect.get() : null;
    }


    public String getSimpleDescription() {


        return getRegistryName().toString() + ".simpledesc";
    }
    public static ComplexEffect getEffectByLocation(ResourceLocation resourceLocation) {


        return resourceLocation == null ?  null: (ComplexEffect) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.COMPLEX_EFFECT_REGISTRY_NAME).getValue(resourceLocation);
    }



    public enum  StackPolicy {

        OLDEST_PRIORITY,
        NEWEST_PRIORITY,
        REFRESH_OLDEST,
        REFRESH_NEWEST,
        STACK_DURATION,
        STACK_POWER,
        STACK_DURATION_AND_POWER;





    }




}
