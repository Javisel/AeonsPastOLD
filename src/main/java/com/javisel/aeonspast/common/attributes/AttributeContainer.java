package com.javisel.aeonspast.common.attributes;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.UUID;

public class AttributeContainer {


    private final AttributeModifier modifier;
    private final Attribute attribute;


    public AttributeContainer(Attribute attribute, AttributeModifier modifier) {

        this.attribute = attribute;
        this.modifier = modifier;

    }


    public static AttributeContainer withUUID(Attribute attribute, UUID modID, double amount, AttributeModifier.Operation operation) {

        AttributeModifier modifier = new AttributeModifier(modID, attribute.getRegistryName().toString() + modID.toString(), amount, operation);
        return new AttributeContainer(attribute, modifier);


    }


    public AttributeModifier getModifier() {
        return modifier;
    }

    public Attribute getAttribute() {
        return attribute;
    }





}
