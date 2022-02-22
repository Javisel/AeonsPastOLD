package com.javisel.aeonspast.common.capabiltiies.projectile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public interface IProjectileData {


       void readNBT(CompoundTag tag);
      CompoundTag writeNBT();

    ItemStack getShooterItem();

    void setShooterItem(ItemStack shooterItem);
}
