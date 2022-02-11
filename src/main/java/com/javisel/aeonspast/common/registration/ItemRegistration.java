package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.items.TrinketEnums;
import com.javisel.aeonspast.common.items.BasicSpellStone;
import com.javisel.aeonspast.common.items.emblem.BasicEmblem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistration {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AeonsPast.MODID);

    public static final RegistryObject<Item> WARRIOR_EMBLEM = ITEMS.register("warrior_emblem", () -> new BasicEmblem(ClassRegistration.WARRIOR, SpellRegistration.WARRIOR_CLASS_SPELL));


    public static final RegistryObject<Item> ASSAULT_GUARD_STONE = ITEMS.register("assault_guard_stone", () -> new BasicSpellStone(TrinketEnums.AMULET, SpellRegistration.ASSAULT_GUARD));
    public static final RegistryObject<Item> BATTLE_FRENZY = ITEMS.register("battle_frenzy_stone", () -> new BasicSpellStone(TrinketEnums.AMULET, SpellRegistration.BATTLE_FRENZY));
    public static final RegistryObject<Item> WARRIOR_SHOUT = ITEMS.register("warrior_shout_stone", () -> new BasicSpellStone(TrinketEnums.AMULET, SpellRegistration.WARRIOR_SHOUT));
    public static final RegistryObject<Item> STORM_STRIKE = ITEMS.register("storm_strike_stone", () -> new BasicSpellStone(TrinketEnums.AMULET, SpellRegistration.STORM_STRIKE));
    public static final RegistryObject<Item> BLADESTORM = ITEMS.register("bladestorm_stone", () -> new BasicSpellStone(TrinketEnums.AMULET, SpellRegistration.BLADESTORM));
    public static final RegistryObject<Item> LIGHT_OF_GOD = ITEMS.register("light_of_god_stone", () -> new BasicSpellStone(TrinketEnums.RELIC, SpellRegistration.LIGHT_OF_GOD));
    public static final RegistryObject<Item> DEBUGSPELLITEM = ITEMS.register("debug_spell_item", () -> new BasicSpellStone(TrinketEnums.AMULET, SpellRegistration.HOP));


}