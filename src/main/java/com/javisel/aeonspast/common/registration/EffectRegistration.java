package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import net.minecraft.world.effect.MobEffect;
 import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectRegistration {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AeonsPast.MODID);


}
