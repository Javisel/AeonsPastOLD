package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.particles.WorldTextOptions;
import com.javisel.aeonspast.common.particles.WorldTextType;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ParticleTypeRegistration {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, AeonsPast.MODID);


    public static final RegistryObject<ParticleType<WorldTextOptions>> WORLD_TEXT_TYPE = PARTICLE_TYPES.register("world_text_type", () -> new WorldTextType());
}
