package com.javisel.aeonspast.common.items.properties;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.items.ItemEngine;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryManager;

public class ItemProperty extends net.minecraftforge.registries.ForgeRegistryEntry<ItemProperty> {


    public ItemProperty() {


    }





    public void applyToItem( ItemStack stack) {


        ItemEngine.getPropertyTag(stack).putString(this.getRegistryName().toString(),"");





    }


    public static ItemProperty getPropertyByLocation(ResourceLocation resourceLocation) {


        return resourceLocation == null ? null: (ItemProperty) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.ITEM_PROPERTY_NAME).getValue(resourceLocation);
    }







}
