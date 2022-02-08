package com.javisel.aeonspast.common.events;

import com.javisel.aeonspast.common.combat.DamageInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;

public class OnDamageCalculationEvent extends LivingEvent {

    DamageInstance instance;
    public OnDamageCalculationEvent(DamageInstance instance,LivingEntity entity) {
        super(entity);
        this.instance=instance;
    }






}
