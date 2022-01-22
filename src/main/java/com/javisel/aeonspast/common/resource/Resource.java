package com.javisel.aeonspast.common.resource;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;

public abstract class Resource extends net.minecraftforge.registries.ForgeRegistryEntry<Resource>{


    private final Attribute ResourceMaxAttribute;


    protected Resource(Attribute resourceMaxAttribute) {
        ResourceMaxAttribute = resourceMaxAttribute;
    }




    public boolean doesNaturallyRegenerate(LivingEntity entity) {



        return false;
    }


    public void setResource(LivingEntity entity, float amount) {





    }


    public void resourceAmountChange(LivingEntity entity, float amount) {

    }

    public void addResource(LivingEntity entity, float amount) {




    }

    public Attribute getResourceMaxAttribute() {
        return ResourceMaxAttribute;
    }
}
