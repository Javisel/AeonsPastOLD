package com.javisel.aeonspast.common.combat;

import net.minecraft.world.damagesource.DamageSource;

public class APDamageSource extends net.minecraft.world.damagesource.DamageSource {


    private DamageInstance instance;
    public APDamageSource(String p_19333_, DamageInstance damageInstance) {
        super(p_19333_);
        this.instance=damageInstance;
    }


    public DamageInstance getInstance( ) {

        return  instance;
    }
}
