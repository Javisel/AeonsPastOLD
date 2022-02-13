package com.javisel.aeonspast.common.effects;

import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypes;
import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.registries.RegistryObject;

public class Bleed extends ComplexEffect{

    public Bleed( ) {
        super(MobEffectCategory.HARMFUL, 1);
    }


    @Override
    public void applyEffectTick(LivingEntity entity, int durationIn) {
        super.applyEffectTick(entity, durationIn);



        if (durationIn % 20 ==0) {


        }
    }



}
