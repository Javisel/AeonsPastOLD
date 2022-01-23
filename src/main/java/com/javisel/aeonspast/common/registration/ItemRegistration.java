package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.items.BasicTrinketStone;
import com.javisel.aeonspast.common.items.LightOfGodStone;
import com.javisel.aeonspast.common.items.WarriorShoutStone;
import com.javisel.aeonspast.common.items.emblem.WarriorEmblem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistration {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AeonsPast.MODID);

    public static final RegistryObject<Item> WARRIOR_EMBLEM = ITEMS.register("warrior_emblem", () -> new WarriorEmblem());


    public static final RegistryObject<Item> AssaultGuardStone = ITEMS.register("assault_guard_stone", () -> new BasicTrinketStone(SpellRegistration.ASSAULTGUARD));
    public static final RegistryObject<Item> BATTLE_FRENZY = ITEMS.register("battle_frenzy_stone", () -> new BasicTrinketStone(SpellRegistration.BATTLE_FRENZY));
    public static final RegistryObject<Item> WARRIOR_SHOUT = ITEMS.register("warrior_shout_stone", () -> new WarriorShoutStone());
    public static final RegistryObject<Item> STORM_STRIKE = ITEMS.register("storm_strike_stone", () -> new BasicTrinketStone(SpellRegistration.STORM_STRIKE));
    public static final RegistryObject<Item> BLADESTORM = ITEMS.register("bladestorm_stone", () -> new BasicTrinketStone(SpellRegistration.BLADESTORM));
    public static final RegistryObject<Item> LIGHT_OF_GOD = ITEMS.register("light_of_god_stone", () -> new LightOfGodStone());


}