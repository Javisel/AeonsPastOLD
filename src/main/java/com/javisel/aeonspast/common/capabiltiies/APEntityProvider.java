package com.javisel.aeonspast.common.capabiltiies;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class APEntityProvider implements ICapabilitySerializable<CompoundTag> {


    private final APEntityData apEntityData = new APEntityData();
    private final LazyOptional<IEntityData> apOptional = LazyOptional.of(() -> apEntityData);


    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {


        if (cap == APEntityCapability.ENTITY_DATA_CAP) {
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

        if (APEntityCapability.ENTITY_DATA_CAP == null) {
            return new CompoundTag();


        } else {

            return apEntityData.writeNBT();
        }


    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {


        if (APEntityCapability.ENTITY_DATA_CAP != null) {
            apEntityData.readNBT(nbt);


        }


    }
}
