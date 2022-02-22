package com.javisel.aeonspast.common.entities.spell;

import com.javisel.aeonspast.common.registration.EntityTypeRegistration;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AxeEntity extends WeaponEntity {




    public AxeEntity(LivingEntity owner, Level level, ItemStack stack) {
        super(owner, stack,EntityTypeRegistration.THROWN_AXE.get(), level);


    }

    public AxeEntity(EntityType<? extends AxeEntity> entityEntityType, Level level) {
        super( EntityTypeRegistration.THROWN_AXE.get(),level);


    }
}
