package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundEventRegistration {


    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, AeonsPast.MODID);


    public static final RegistryObject<SoundEvent> SWIFT_STRIKES_CAST = SOUND_EVENTS.register("swift_strikes_cast", () -> register("spell.swift_strikes_cast"));


    private static SoundEvent register(String soundLocation) {
        return new SoundEvent(new ResourceLocation(AeonsPast.MODID, soundLocation));
    }

}


