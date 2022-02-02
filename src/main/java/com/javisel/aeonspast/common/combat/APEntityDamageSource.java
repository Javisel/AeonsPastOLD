package com.javisel.aeonspast.common.combat;

import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;

public class APEntityDamageSource extends EntityDamageSource implements APDamageSource {


    private DamageInstance instance;





    public APEntityDamageSource(String id, DamageInstance instance, Entity source) {
        super(id, source);
        this.instance=instance;
    }


    public DamageInstance getInstance( ) {

        return  instance;
    }
}
