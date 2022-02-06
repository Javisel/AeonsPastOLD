package com.javisel.aeonspast.common;

import com.javisel.aeonspast.common.combat.damagesource.APEntityDamageSource;
import com.javisel.aeonspast.common.combat.DamageInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import org.jetbrains.annotations.Nullable;

public class APEntityProjecitleDamage extends APEntityDamageSource {

    public AbstractHurtingProjectile projectile;

    public APEntityProjecitleDamage(String id, DamageInstance instance, AbstractHurtingProjectile projectile) {
        super(id, instance, projectile.getOwner());
        setProjectile();
        this.projectile=projectile;

    }


    @Override
    public @Nullable Entity getDirectEntity() {
        return super.getEntity();
    }



}
