package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.items.basic.BloodChalice;
import com.javisel.aeonspast.common.items.emblem.WarriorEmblem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistration {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AeonsPast.MODID);
    //  public static final RegistryObject<Item> ENDER_KATANA = ITEMS.register("ender_katana", () -> new EnderKatana());

    public static final RegistryObject<Item> BLOOD_CHALICE = ITEMS.register("blood_chalice", () -> new BloodChalice());
    // public static final RegistryObject<Item> HEART_OF_GOLD = ITEMS.register("heart_of_gold", () ->new TrinketItem(new Item.Properties().stacksTo(1), new APAttributeContainer(Attributes.MAX_HEALTH,new AttributeModifier("2cfeb25f-2af5-455d-aa35-8685a290e783",5, AttributeModifier.Operation.ADDITION)),new APAttributeContainer(AttributeRegistration.HEALTH_REGENERATION, new AttributeModifier("c8709100-27dd-44e6-b7cf-e92c35243271",2, AttributeModifier.Operation.ADDITION))));
    public static final RegistryObject<Item> WARRIOR_EMBLEM = ITEMS.register("warrior_emblem", () -> new WarriorEmblem());
}