package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AttributeRegistration {


    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, AeonsPast.MODID);

    //Defensive
    public static final RegistryObject<Attribute> HEALTH_REGENERATION = ATTRIBUTES.register("health_regeneration", () -> new RangedAttribute("generic.health_regeneration", 1, 0, 100));
    public static final RegistryObject<Attribute> MAGIC_RESISTANCE = ATTRIBUTES.register("magic_resistance", () -> new RangedAttribute("generic.magic_resistance", 0, 0, 100000));

    public static final RegistryObject<Attribute> HEALTH_SHIELD = ATTRIBUTES.register("health_shield", () -> new RangedAttribute("generic.barrier", 0, 0, 100000));


    //Offensive
    public static final RegistryObject<Attribute> SPELL_VAMP = ATTRIBUTES.register("spell_vamp", () -> new RangedAttribute("generic.spell_vamp", 0, 0, 100));

    public static final RegistryObject<Attribute> LIFESTEAL = ATTRIBUTES.register("lifesteal", () -> new RangedAttribute("generic.lifesteal", 0, 0, 100));
    public static final RegistryObject<Attribute> MAGIC_POWER = ATTRIBUTES.register("magic_power", () -> new RangedAttribute("generic.magic_power", 0, 0, 100));
    public static final RegistryObject<Attribute> PHYSICAL_POWER = ATTRIBUTES.register("physical_power", () -> new RangedAttribute("generic.physical_power", 0, 0, 100));


    //Resources
    public static final RegistryObject<Attribute> MAXIMUM_MANA = ATTRIBUTES.register("maximum_mana", () -> new RangedAttribute("generic.maximum_mana", 100, 0, 10000));
     public static final RegistryObject<Attribute> MANA_REGENERATION = ATTRIBUTES.register("mana_regeneration", () -> new RangedAttribute("generic.mana_regeneration", 0.5, 0, 10000));

    public static final RegistryObject<Attribute> MAXIMUM_FOOD = ATTRIBUTES.register("maximum_food", () -> new RangedAttribute("generic.maximum_food", 100, 0, 10000));
    public static final RegistryObject<Attribute> FOOD_REGENERATION = ATTRIBUTES.register("mana_regeneration", () -> new RangedAttribute("generic.food_regeneration", 0, 0, 10000));





    public static final RegistryObject<Attribute> COOLDOWN_REDUCTION = ATTRIBUTES.register("cooldown_reduction", () -> new RangedAttribute("generic.cooldown_reduction", 0, 0, 10000));








}