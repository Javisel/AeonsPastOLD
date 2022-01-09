package com.javisel.aeonspast.common.capabiltiies;

import net.minecraft.nbt.CompoundTag;

public interface IEntityData {

    void setMana(float mana);

    float getMana();
    CompoundTag storedData();
    CompoundTag writeNBT();
    void readNBT(CompoundTag tag);


}
