package com.javisel.aeonspast.common.playerclasses;

import net.minecraft.nbt.CompoundTag;

import static com.javisel.aeonspast.utilities.StringKeys.EXPERIENCE;
import static com.javisel.aeonspast.utilities.StringKeys.LEVEL;

public final class ClassInstance {


    public static final ClassInstance DEFAULT = new ClassInstance();

    private int level = 1;
    private float experience = 0;


    public void fromNBT(CompoundTag tag) {

        level = tag.getInt(LEVEL);
        experience = tag.getFloat(EXPERIENCE);


    }


    public CompoundTag toNBT() {

        CompoundTag tag = new CompoundTag();

        tag.putInt(LEVEL, level);
        tag.putFloat(EXPERIENCE, experience);
        return tag;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public float getExperience() {
        return experience;
    }

    public void setExperience(float experience) {
        this.experience = experience;
    }

    public void addExperience(float amount) {


        experience += amount;


    }


}
