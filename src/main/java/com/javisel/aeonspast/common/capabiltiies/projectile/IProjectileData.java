package com.javisel.aeonspast.common.capabiltiies.projectile;

import net.minecraft.nbt.CompoundTag;

import java.util.UUID;

public interface IProjectileData {


    UUID shooterItemID();
      void readNBT(CompoundTag tag);
      CompoundTag writeNBT();

}
