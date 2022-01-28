package com.javisel.aeonspast.common.resource;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;

//Thanks Mojang!
public class ResourceContainer {

    private final float currentValue;

    private AttributeInstance resourceMaxInstance;


    public ResourceContainer(float value, Attribute maxResourceAttribute) {

        this.currentValue = value;


    }


}
