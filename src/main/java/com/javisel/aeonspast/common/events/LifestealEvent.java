package com.javisel.aeonspast.common.events;

import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;

public class LifestealEvent extends LivingEvent {


    float amount;


    private LivingEntity victim;

    public LifestealEvent(LivingEntity healer, LivingEntity victim, float amount) {
        super(healer);
        this.amount = amount;
        this.victim = victim;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LivingEntity getVictim() {
        return victim;
    }

    public void setVictim(LivingEntity victim) {
        this.victim = victim;
    }
}
