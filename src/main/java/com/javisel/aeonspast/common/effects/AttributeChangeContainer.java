package com.javisel.aeonspast.common.effects;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class AttributeChangeContainer {


    private final Attribute attribute;
    private final AttributeModifier.Operation operation;

    public AttributeChangeContainer(Attribute attribute, AttributeModifier.Operation operation) {
        this.attribute = attribute;
        this.operation = operation;
    }


    public Attribute getAttribute() {
        return attribute;
    }

    public AttributeModifier.Operation getOperation() {
        return operation;
    }
}


