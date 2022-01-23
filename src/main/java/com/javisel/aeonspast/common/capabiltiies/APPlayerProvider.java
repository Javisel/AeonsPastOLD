package com.javisel.aeonspast.common.capabiltiies;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class APPlayerProvider implements ICapabilitySerializable<CompoundTag> {


    private final APPlayerData apPlayerData = new APPlayerData();
    private final LazyOptional<IPlayerData> apOptional = LazyOptional.of(() -> apPlayerData);


    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {


        if (cap == APPlayerCapability.PLAYER_DATA_CAPABILITY) {
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

            return apPlayerData.writeNBT();
        }


    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {


        if (APEntityCapability.ENTITY_DATA_CAP != null) {
            apPlayerData.readNBT(nbt);


        }


    }


}