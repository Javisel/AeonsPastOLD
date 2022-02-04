package com.javisel.aeonspast.common.capabiltiies.mob;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MobDataProvider implements ICapabilitySerializable<CompoundTag> {


    private final MobData entityData = new MobData();
    private final LazyOptional<IMobData> apOptional = LazyOptional.of(() -> entityData);


    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {


        if (cap == MobDataCapability.MOB_DATA_CAP) {
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

        if (MobDataCapability.MOB_DATA_CAP == null) {
            return new CompoundTag();


        } else {

            return entityData.toNBT();
        }


    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {


        if (MobDataCapability.MOB_DATA_CAP != null) {
            entityData.fromNBT(nbt);


        }


    }
}
