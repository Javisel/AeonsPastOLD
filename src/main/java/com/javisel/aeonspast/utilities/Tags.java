package com.javisel.aeonspast.utilities;

import com.javisel.aeonspast.AeonsPast;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class Tags {

    public static final Tag.Named<Item> WEAPONS = ItemTags.createOptional(new ResourceLocation(AeonsPast.MODID, "weapons/weapons"));
    public static final Tag.Named<Item> ARMOR = ItemTags.createOptional(new ResourceLocation(AeonsPast.MODID, "armor/armor"));
    public static final Tag.Named<Item> EMBLEMS = ItemTags.createOptional(new ResourceLocation(AeonsPast.MODID, "trinkets/emblems"));

    public static final Tag.Named<Item> TRINKETS = ItemTags.createOptional(new ResourceLocation(AeonsPast.MODID, "trinkets/passive_trinkets"));
    public static final Tag.Named<Item> ACTIVE_TRINKETS = ItemTags.createOptional(new ResourceLocation(AeonsPast.MODID, "trinkets/active_trinkets"));
    public static final Tag.Named<Item> UTLIMATE_TRINKET = ItemTags.createOptional(new ResourceLocation(AeonsPast.MODID, "trinkets/ultimate_trinkets"));

    public static final Tag.Named<EntityType<?>> ENTITY_TYPE_STATISISTICS = EntityTypeTags.createOptional(new ResourceLocation(AeonsPast.MODID, "entity_types/entity_types"));


}
