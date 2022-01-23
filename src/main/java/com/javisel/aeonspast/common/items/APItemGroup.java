package com.javisel.aeonspast.common.items;

import com.javisel.aeonspast.common.registration.ItemRegistration;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static com.javisel.aeonspast.AeonsPast.MODID;

public class APItemGroup extends CreativeModeTab {

    public APItemGroup() {
        super(MODID);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemRegistration.WARRIOR_EMBLEM.get());
    }
}
