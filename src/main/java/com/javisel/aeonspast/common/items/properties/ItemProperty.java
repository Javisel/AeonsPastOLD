package com.javisel.aeonspast.common.items.properties;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.items.ItemEngine;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryManager;

public class ItemProperty extends net.minecraftforge.registries.ForgeRegistryEntry<ItemProperty> {


    private boolean isDisplayed = false;
    public ItemProperty() {


    }


    public void setDisplayed(){


        isDisplayed=true;
    }

    public boolean isDisplayed(){

        return isDisplayed;
    }


    public static ItemProperty getPropertyByLocation(ResourceLocation resourceLocation) {


        return resourceLocation == null ? null : (ItemProperty) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.ITEM_PROPERTY_REGISTRY_NAME).getValue(resourceLocation);
    }

    public void applyToItem(ItemStack stack) {


        ItemEngine.getPropertyTag(stack).putString(this.getRegistryName().toString(), "");


    }

    public boolean onPreHitEntity(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance ) {
        return  true;

    }

    //When the wielder is hit, before the hit is processed
    public boolean onOwnerPreHit(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance ) {

        return  true;

    }

    //when the hit applies
    public boolean onOwnerHit(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance ) {

        return  true;

    }

    //After the hit applies.
    public boolean onOwnerPostHit(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance ) {

        return  true;

    }


    public boolean onPreHitEntityInHand(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack ) {

        return  true;

    }


    public boolean onHitEntity(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance) {

     return  true;

    }


    public boolean onHitEntityInHand(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack) {

        return  true;
    }
    public boolean postHitEntity(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance ) {


        return true;

    }
    public boolean postHitEntityInHand(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack) {

        return  true;




    }

    public boolean onSwingItem(LivingEntity swinger, ItemStack stack) {


        System.out.println("ON SWING!");

        return true;
    }


}
