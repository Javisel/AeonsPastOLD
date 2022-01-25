package com.javisel.aeonspast.common.items.weapons;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import static com.javisel.aeonspast.AeonsPast.MODID;

public class WeaponHandler {



    public void initWeapon(ItemStack stack) {

        CompoundTag tag = stack.hasTag() ? stack.getTag() : new CompoundTag();

        CompoundTag AeonsPastData = tag.contains(MODID) ? tag.getCompound(MODID) : new CompoundTag();

        CompoundTag weaponData = new CompoundTag();
















    }







}
