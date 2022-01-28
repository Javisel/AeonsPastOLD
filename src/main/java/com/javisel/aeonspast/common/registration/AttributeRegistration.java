package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AttributeRegistration {


    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, AeonsPast.MODID);


    //Six Primary Attributes
    public static final RegistryObject<Attribute> STRENGTH = ATTRIBUTES.register("strength", () -> new RangedAttribute("generic.strength", 0, 0, 100).setSyncable(true));
    public static final RegistryObject<Attribute> CONSTITUTION = ATTRIBUTES.register("constitution", () -> new RangedAttribute("generic.constitution", 0, 0, 100).setSyncable(true));
    public static final RegistryObject<Attribute> DEXTERITY = ATTRIBUTES.register("dexterity", () -> new RangedAttribute("generic.dexterity", 0, 0, 100).setSyncable(true));
    public static final RegistryObject<Attribute> INTELLIGENCE = ATTRIBUTES.register("intelligence", () -> new RangedAttribute("generic.intelligence", 0, 0, 100).setSyncable(true));
    public static final RegistryObject<Attribute> WISDOM = ATTRIBUTES.register("wisdom", () -> new RangedAttribute("generic.wisdom", 0, 0, 100).setSyncable(true));
    public static final RegistryObject<Attribute> CHARISMA = ATTRIBUTES.register("charisma", () -> new RangedAttribute("generic.charisma", 0, 0, 100).setSyncable(true));


    //Defensive
    public static final RegistryObject<Attribute> HEALTH_REGENERATION = ATTRIBUTES.register("health_regeneration", () -> new RangedAttribute("generic.health_regeneration", 1, 0, 100).setSyncable(true));

    public static final RegistryObject<Attribute> ARMOR = ATTRIBUTES.register("armor", () -> new RangedAttribute("generic.armor", 0, 0, 100000).setSyncable(true));
    public static final RegistryObject<Attribute> ARMOR_TOUGHNESS = ATTRIBUTES.register("armor_toughness", () -> new RangedAttribute("generic.armor_toughness", 0, 0, 100000).setSyncable(true));

    public static final RegistryObject<Attribute> MAGIC_RESISTANCE = ATTRIBUTES.register("magic_resistance", () -> new RangedAttribute("generic.magic_resistance", 0, 0, 100000).setSyncable(true));
    public static final RegistryObject<Attribute> DAMAGE_INTAKE = ATTRIBUTES.register("damage_intake", () -> new RangedAttribute("generic.damage_intake", 1, -200, 100).setSyncable(true));
    public static final RegistryObject<Attribute> TENACITY = ATTRIBUTES.register("tenacity", () -> new RangedAttribute("generic.tenacity", 1, 0, 100).setSyncable(true));
    public static final RegistryObject<Attribute> VITALITY = ATTRIBUTES.register("vitality", () -> new RangedAttribute("generic.vitality", 1, 0, 100).setSyncable(true));


    public static final RegistryObject<Attribute> HEALTH_SHIELD = ATTRIBUTES.register("health_shield", () -> new RangedAttribute("generic.barrier", 0, 0, 100000).setSyncable(true));


    //Offensive
    public static final RegistryObject<Attribute> SPELL_LIFESTEAL = ATTRIBUTES.register("spell_lifesteal", () -> new RangedAttribute("generic.spell_lifesteal", 0, 0, 100).setSyncable(true));


    public static final RegistryObject<Attribute> WEAPON_POWER = ATTRIBUTES.register("weapon_power", () -> new RangedAttribute("generic.weapon_power", 0, 0, 100).setSyncable(true));
    public static final RegistryObject<Attribute> WEAPON_LIFESTEAL = ATTRIBUTES.register("weapon_lifesteal", () -> new RangedAttribute("generic.weapon_power", 0, 0, 100).setSyncable(true));
    public static final RegistryObject<Attribute> SPELL_POWER = ATTRIBUTES.register("spell_power", () -> new RangedAttribute("generic.spell_power", 0, 0, 1000).setSyncable(true));
    public static final RegistryObject<Attribute> PHYSICAL_POWER = ATTRIBUTES.register("physical_power", () -> new RangedAttribute("generic.physical_power", 0, 0, 1000).setSyncable(true));
    public static final RegistryObject<Attribute> MELEE_POWER = ATTRIBUTES.register("melee_power", () -> new RangedAttribute("generic.melee_power", 0, 0, 1000).setSyncable(true));
    public static final RegistryObject<Attribute> RANGED_POWER = ATTRIBUTES.register("ranged_power", () -> new RangedAttribute("generic.ranged_power", 0, 0, 1000).setSyncable(true));
    public static final RegistryObject<Attribute> DAMAGE_OUTPUT = ATTRIBUTES.register("damage_output", () -> new RangedAttribute("generic.damage_output", 1, -100, 100).setSyncable(true));
    public static final RegistryObject<Attribute> CRITICAL_CHANCE = ATTRIBUTES.register("critical_chance", () -> new RangedAttribute("generic.critical_chance", 5, 0, 100).setSyncable(true));
    public static final RegistryObject<Attribute> CRITICAL_DAMAGE = ATTRIBUTES.register("critical_damage", () -> new RangedAttribute("generic.critical_damage", 2, 0, 100).setSyncable(true));


    //Resources
    public static final RegistryObject<Attribute> MAXIMUM_MANA = ATTRIBUTES.register("maximum_mana", () -> new RangedAttribute("generic.maximum_mana", 100, 0, 10000) .setSyncable(true));
    public static final RegistryObject<Attribute> MANA_REGENERATION = ATTRIBUTES.register("mana_regeneration", () -> new RangedAttribute("generic.mana_regeneration", 0.5, 0, 10000).setSyncable(true));

    public static final RegistryObject<Attribute> MAXIMUM_FOOD = ATTRIBUTES.register("maximum_food", () -> new RangedAttribute("generic.maximum_food", 100, 0, 10000).setSyncable(true).setSyncable(true));
    public static final RegistryObject<Attribute> FOOD_REGENERATION = ATTRIBUTES.register("food_regeneration", () -> new RangedAttribute("generic.food_regeneration", 0, 0, 10000).setSyncable(true));


    //Utility
    public static final RegistryObject<Attribute> COOLDOWN_REDUCTION = ATTRIBUTES.register("cooldown_reduction", () -> new RangedAttribute("generic.cooldown_reduction", 0, 0, 10000).setSyncable(true));
    public static final RegistryObject<Attribute> FORTUNE = ATTRIBUTES.register("fortune", () -> new RangedAttribute("generic.fortune", 0, -100, 10000).setSyncable(true));


    //AI
    public static final RegistryObject<Attribute> PRESCENCE = ATTRIBUTES.register("prescence", () -> new RangedAttribute("generic.cooldown_reduction", 1, 0, 10000).setSyncable(true));


}