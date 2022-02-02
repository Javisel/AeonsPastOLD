package com.javisel.aeonspast.common.items.properties;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.combat.DamageInstance;
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


        return resourceLocation == null ? null: (ItemProperty) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.ITEM_PROPERTY_REGISTRY_NAME).getValue(resourceLocation);
    }




    public boolean onPreHitEntity(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack) {

        return true;
    }


    public void onHitEntity(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack) {


    }







    public boolean onSwingItem(LivingEntity swinger, ItemStack stack) {


        System.out.println("ON SWING!");

        return true;
    }



}
