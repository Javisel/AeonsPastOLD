package com.javisel.aeonspast.common.capabiltiies.itemstack;

import com.javisel.aeonspast.common.capabiltiies.entity.EntityCapability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemStackDataProvider implements ICapabilitySerializable<CompoundTag> {


    private final ItemStackData ItemStackData = new ItemStackData();
    private final LazyOptional<IItemStackData> apOptional = LazyOptional.of(() -> ItemStackData);


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

            return ItemStackData.toNBT();
        }


    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {


        if (EntityCapability.ENTITY_DATA_CAP != null) {
            ItemStackData.readNBT(nbt);


        }


    }
}
