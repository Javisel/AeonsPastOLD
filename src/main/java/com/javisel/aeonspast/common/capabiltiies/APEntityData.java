package com.javisel.aeonspast.common.capabiltiies;

import net.minecraft.nbt.CompoundTag;

public class APEntityData implements IEntityData {

    CompoundTag storedData = new CompoundTag();
    float mana = 0;

    @Override
    public void setMana(float mana) {

        this.mana=mana;
    }

    @Override
    public float getMana() {
        return mana;
    }

    @Override
    public CompoundTag storedData() {
        return storedData;
    }

    @Override
    public CompoundTag writeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.put("storedData",storedData);
        tag.putFloat("mana",mana);


        return tag;
    }

    @Override
    public void readNBT(CompoundTag tag) {

        storedData = tag.getCompound("storedData");
        mana=tag.getFloat("mana");

    }
}
