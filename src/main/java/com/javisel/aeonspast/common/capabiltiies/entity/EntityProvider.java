package com.javisel.aeonspast.common.capabiltiies.entity;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityProvider implements ICapabilitySerializable<CompoundTag> {


    private final EntityData entityData = new EntityData();
    private final LazyOptional<IEntityData> apOptional = LazyOptional.of(() -> entityData);


    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {


        if (cap == EntityCapability.ENTITY_DATA_CAP) {
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

            return entityData.writeNBT();
        }


    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {


        if (EntityCapability.ENTITY_DATA_CAP != null) {
            entityData.readNBT(nbt);


        }


    }
}
