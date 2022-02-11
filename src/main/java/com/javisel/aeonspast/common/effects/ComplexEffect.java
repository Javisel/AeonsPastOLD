package com.javisel.aeonspast.common.effects;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.spell.Spell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class ComplexEffect extends ForgeRegistryEntry<ComplexEffect> {


    private RegistryObject<MobEffect> displayEffect;



    public boolean attemptApply(LivingEntity target, ComplexEffectInstance instance) {


        apply(target,instance);

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
}
