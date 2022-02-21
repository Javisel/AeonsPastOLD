package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.items.BasicSpellStone;
import com.javisel.aeonspast.common.items.TrinketTypes;
import com.javisel.aeonspast.common.items.emblem.BasicEmblem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistration {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AeonsPast.MODID);

    public static final RegistryObject<Item> WARRIOR_EMBLEM = ITEMS.register("warrior_emblem", () -> new BasicEmblem(ClassRegistration.WARRIOR, SpellRegistration.WARRIOR_CLASS_SPELL));


    public static final RegistryObject<Item> SWIFT_STRIKES_STONE = ITEMS.register("swift_strikes_spell_stone", () -> new BasicSpellStone(TrinketTypes.ACTIVE, SpellRegistration.SWIFT_STRIKE));


}