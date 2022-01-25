package com.javisel.aeonspast.common.events;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class APDamageEvent extends LivingDamageEvent {
    public APDamageEvent(LivingEntity entity, DamageSource source, float amount) {
        super(entity, source, amount);
    }
}
