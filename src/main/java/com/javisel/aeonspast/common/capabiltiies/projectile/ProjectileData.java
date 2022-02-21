package com.javisel.aeonspast.common.capabiltiies.projectile;

import net.minecraft.nbt.CompoundTag;

import java.util.UUID;

public class ProjectileData implements IProjectileData {



    @Override
    public UUID shooterItemID() {
        return null;
    }

    @Override
    public void readNBT(CompoundTag tag) {

    }

    @Override
    public CompoundTag writeNBT() {
        return null;
    }
}
