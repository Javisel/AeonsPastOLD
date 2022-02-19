package com.javisel.aeonspast.common.capabiltiies.player;

import com.javisel.aeonspast.common.capabiltiies.entity.EntityCapability;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class APPlayerProvider implements ICapabilitySerializable<CompoundTag> {


    private final PlayerData apPlayerData = new PlayerData();
    private final LazyOptional<IPlayerData> apOptional = LazyOptional.of(() -> apPlayerData);


    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {


        if (cap == PlayerCapability.PLAYER_DATA_CAPABILITY) {
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

            return apPlayerData.writeNBT();
        }


    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {


        if (EntityCapability.ENTITY_DATA_CAP != null) {
            apPlayerData.readNBT(nbt);


        }


    }


}