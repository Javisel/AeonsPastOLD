package com.javisel.aeonspast.common.config;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class AttributeStatisticsPair extends StatisticPair {

    private String attribute;
    private AttributeModifier.Operation operation;

    public AttributeStatisticsPair(String attribute, AttributeModifier.Operation operation, float min, float max) {
        super(min, max);
        this.attribute = attribute;
        this.operation = operation;
    }

    public static AttributeStatisticsPair getPairFromNBT(CompoundTag tag) {

        StatisticPair pair = StatisticPair.fromNBT(tag);


        return new AttributeStatisticsPair(tag.getString("attribute"), AttributeModifier.Operation.fromValue(tag.getInt("operation")), pair.getmin(), pair.getMax());


    }

    public Attribute getAttribute() {


        return ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(attribute));


    }

    public AttributeModifier.Operation getOperation() {
        return operation;
    }

    @Override
    public CompoundTag toNBT() {
        CompoundTag tag = super.toNBT();

        tag.putString("attribute", attribute);
        tag.putInt("operation", operation.toValue());


        return tag;
    }
}

