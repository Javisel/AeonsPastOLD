package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.items.properties.armor.Dyeable;
import com.javisel.aeonspast.common.items.properties.armor.Nimble;
import com.javisel.aeonspast.common.items.properties.armor.Sturdy;
import com.javisel.aeonspast.common.items.properties.generic.Versatile;
import com.javisel.aeonspast.common.items.properties.itementities.FireProof;
import com.javisel.aeonspast.common.items.properties.weapon.Brutal;
import com.javisel.aeonspast.common.items.properties.weapon.Kinetic;
import com.javisel.aeonspast.common.items.properties.weapon.Sweeping;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ItemPropertyRegistration {


    public static final DeferredRegister<ItemProperty> ITEM_PROPERTIES = DeferredRegister.create(ItemProperty.class, AeonsPast.MODID);


    public static final RegistryObject<ItemProperty> GILDED_WEAPON = ITEM_PROPERTIES.register("gilded_weapon", () -> new com.javisel.aeonspast.common.items.properties.weapon.Gilded());
    public static final RegistryObject<ItemProperty> FIRE_PROOF = ITEM_PROPERTIES.register("fireproof", () -> new FireProof());
    public static final RegistryObject<ItemProperty> SWEEPING = ITEM_PROPERTIES.register("sweeping", () -> new Sweeping());
    public static final RegistryObject<ItemProperty> VERSATILE = ITEM_PROPERTIES.register("versatile", () -> new Versatile());
    public static final RegistryObject<ItemProperty> BRUTAL = ITEM_PROPERTIES.register("brutal", () -> new Brutal());
    public static final RegistryObject<ItemProperty> KINETIC = ITEM_PROPERTIES.register("kinetic", () -> new Kinetic());


    public static final RegistryObject<ItemProperty> NIMBLE = ITEM_PROPERTIES.register("nimble", () -> new Nimble());
    public static final RegistryObject<ItemProperty> DYEABLE = ITEM_PROPERTIES.register("dyeable", () -> new Dyeable());
    public static final RegistryObject<ItemProperty> STURDY = ITEM_PROPERTIES.register("sturdy", () -> new Sturdy());
    public static final RegistryObject<ItemProperty> GILDED_ARMOUR = ITEM_PROPERTIES.register("gilded_armour", () -> new com.javisel.aeonspast.common.items.properties.weapon.Gilded());

}
