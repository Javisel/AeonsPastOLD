package com.javisel.aeonspast.common.items.properties;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.capabiltiies.itemstack.IItemStackData;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryManager;

public class ItemProperty extends net.minecraftforge.registries.ForgeRegistryEntry<ItemProperty> {


    public ItemProperty() {


    }





    public void applyToItem(LivingEntity entity, ItemStack stack) {

        IItemStackData data = Utilities.getItemStackData(stack);

        data.getItemProperties().add(this);


    }

    public static ItemProperty getPropertyByLocation(ResourceLocation resourceLocation) {


        return resourceLocation == null ? null: (ItemProperty) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.ITEM_PROPERTY_NAME).getValue(resourceLocation);
    }


}
