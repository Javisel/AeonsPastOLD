package com.javisel.aeonspast.common.attributes;


import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.registries.RegistryObject;

public class APAttributeContainer {


    private final AttributeModifier modifier;
    private RegistryObject<Attribute> attributeRegistryObject;
    private Attribute vanillaAttribute;

    public APAttributeContainer(RegistryObject<Attribute> attributeRegistryObject, AttributeModifier modifier) {

        this.attributeRegistryObject = attributeRegistryObject;
        this.modifier = modifier;

    }

    public APAttributeContainer(Attribute attribute, AttributeModifier modifier) {

        vanillaAttribute = attribute;
        this.modifier = modifier;

    }


    public AttributeModifier getModifier() {
        return modifier;
    }

    public Attribute getAttribute() {
        return attributeRegistryObject == null ? vanillaAttribute : attributeRegistryObject.get();
    }
}
