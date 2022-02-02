package com.javisel.aeonspast.common.combat;

import net.minecraft.world.damagesource.DamageSource;

public class EnvironmentalDamageSource extends DamageSource implements APDamageSource {

    DamageInstance instance;
    public EnvironmentalDamageSource(String p_19333_, DamageInstance instance) {
        super(p_19333_);
        this.instance=instance;
    }

    @Override
    public DamageInstance getInstance() {
        return instance;
    }
}
