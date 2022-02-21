package com.javisel.aeonspast.common.capabiltiies.projectile;

import com.javisel.aeonspast.common.capabiltiies.entity.EntityCapability;
import com.javisel.aeonspast.common.capabiltiies.projectile.IProjectileData;
import com.javisel.aeonspast.common.capabiltiies.projectile.ProjectileData;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProjectileDataProvider implements ICapabilitySerializable<CompoundTag> {


    private final ProjectileData apProjectileData = new ProjectileData();
    private final LazyOptional<IProjectileData> apOptional = LazyOptional.of(() -> apProjectileData);


    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {


        if (cap == ProjectileCapability.PLAYER_DATA_CAPABILITY) {
            return apOptional.cast();
        } else {
            return LazyOptional.empty();
        }
    }


    public void invalidate() {
        apOptional.invalidate();
    }

    @Override
    public CompoundTag serializeNBT() {

        if (EntityCapability.ENTITY_DATA_CAP == null) {
            return new CompoundTag();


        } else {

            return apProjectileData.writeNBT();
        }


    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {


        if (EntityCapability.ENTITY_DATA_CAP != null) {
            apProjectileData.readNBT(nbt);


        }


    }


}