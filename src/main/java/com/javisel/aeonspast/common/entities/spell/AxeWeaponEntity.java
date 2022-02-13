package com.javisel.aeonspast.common.entities.spell;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class AxeWeaponEntity extends WeaponEntity{


    public AxeWeaponEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }


    @Override
    protected void onHit(HitResult p_37260_) {
        super.onHit(p_37260_);




    }
}
