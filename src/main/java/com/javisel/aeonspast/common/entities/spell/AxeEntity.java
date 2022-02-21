package com.javisel.aeonspast.common.entities.spell;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.level.Level;

public class AxeEntity extends WeaponEntity {
    public AxeEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }
}
