package com.javisel.aeonspast.common.config;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryManager;

public class AttributeStatisticsPair extends StatisticPair{

    private String attribute;
    private AttributeModifier.Operation operation;

    public AttributeStatisticsPair(String attribute,AttributeModifier.Operation operation, float min, float max) {
        super(min, max);
        this.attribute=attribute;
        this.operation=operation;
    }


    public Attribute getAttribute(){







        return ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(attribute));



    }

    public AttributeModifier.Operation getOperation() {
        return operation;
    }
}

