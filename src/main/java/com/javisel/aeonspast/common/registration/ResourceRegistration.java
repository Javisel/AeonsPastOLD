package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ResourceRegistration {


    public static final DeferredRegister<Resource> RESOURCES = DeferredRegister.create(Resource.class, AeonsPast.MODID);



    public static final RegistryObject<Resource> MANA = RESOURCES.register("mana", () -> new Resource(AttributeRegistration.MAXIMUM_MANA,AttributeRegistration.MANA_REGENERATION));
    public static final RegistryObject<Resource> FOOD = RESOURCES.register("food", () -> new Resource(AttributeRegistration.MAXIMUM_FOOD,AttributeRegistration.FOOD_REGENERATION));






}
