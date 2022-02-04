package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.items.properties.ItemRarity;
import com.javisel.aeonspast.common.items.properties.generic.Versatile;
import com.javisel.aeonspast.common.items.properties.itementities.FireProof;
import com.javisel.aeonspast.common.items.properties.weapon.Brutal;
import com.javisel.aeonspast.common.items.properties.weapon.Kinetic;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ItemPropertyRegistration {


    public static final DeferredRegister<ItemProperty> ITEM_PROPERTIES = DeferredRegister.create(ItemProperty.class, AeonsPast.MODID);


    public static final RegistryObject<ItemProperty> EMBLEM = ITEM_PROPERTIES.register("emblem", () -> new ItemRarity());
    public static final RegistryObject<ItemProperty> COMMON = ITEM_PROPERTIES.register("common", () -> new ItemRarity());
    public static final RegistryObject<ItemProperty> RARE = ITEM_PROPERTIES.register("rare", () -> new ItemRarity());
    public static final RegistryObject<ItemProperty> EPIC = ITEM_PROPERTIES.register("epic", () -> new ItemRarity());
    public static final RegistryObject<ItemProperty> FABLED = ITEM_PROPERTIES.register("fabled", () -> new ItemRarity());
    public static final RegistryObject<ItemProperty> LEGENDARY = ITEM_PROPERTIES.register("legendary", () -> new ItemRarity());
    public static final RegistryObject<ItemProperty> MYTHIC = ITEM_PROPERTIES.register("mythic", () -> new ItemRarity());


    public static final RegistryObject<ItemProperty> GILDED = ITEM_PROPERTIES.register("gilded_weapon", () -> new com.javisel.aeonspast.common.items.properties.weapon.Gilded());
    public static final RegistryObject<ItemProperty> FIRE_PROOF = ITEM_PROPERTIES.register("fireproof", () -> new FireProof());
    public static final RegistryObject<ItemProperty> SWEEPING = ITEM_PROPERTIES.register("sweeping", () -> new com.javisel.aeonspast.common.items.properties.weapon.Gilded());
    public static final RegistryObject<ItemProperty> VERSATILE = ITEM_PROPERTIES.register("versatile", () -> new Versatile());
    public static final RegistryObject<ItemProperty> BRUTAL = ITEM_PROPERTIES.register("brutal", () -> new Brutal());
    public static final RegistryObject<ItemProperty> KINETIC = ITEM_PROPERTIES.register("kinetic", () -> new Kinetic());


}
