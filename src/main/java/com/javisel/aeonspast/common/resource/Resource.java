package com.javisel.aeonspast.common.resource;

import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import com.javisel.aeonspast.utilities.APUtilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.registries.RegistryObject;

public  class Resource extends net.minecraftforge.registries.ForgeRegistryEntry<Resource> {


    private final RegistryObject<Attribute> ResourceMaxAttribute;
    private final RegistryObject<Attribute> ResourceRegenAttribute;
    private  boolean doesRegenerate = true;

    public Resource(RegistryObject<Attribute> resourceMaxAttribute, RegistryObject<Attribute> resourceRegenAttribute) {
        ResourceMaxAttribute = resourceMaxAttribute;
        ResourceRegenAttribute = resourceRegenAttribute;
    }


    public boolean doesNaturallyRegenerate(LivingEntity entity) {


        return true;
    }

    public Resource setNoRegen(){


        doesRegenerate=false;
        return this;

    }

    public void setResource(LivingEntity entity, float amount) {

        IEntityData data = APUtilities.getEntityData(entity);

        float old = data.getResourceAmount(this);
        data.setResourceAmount(this,amount);

        resourceAmountChange(entity,old,amount);



    }


    public void tick(LivingEntity entity) {

        IEntityData data = APUtilities.getEntityData(entity);

        if (data.getTicks()==20) {

            if (doesNaturallyRegenerate(entity)) {



                addResource(entity, (float) entity.getAttributeValue(ResourceRegenAttribute.get()));


            }

        }



    }



    public void resourceAmountChange(LivingEntity entity, float oldamount, float newamount) {

    }

    public void addResource(LivingEntity entity, float amount) {

        IEntityData data = APUtilities.getEntityData(entity);

        float old = data.getResourceAmount(this);

        data.setResourceAmount(this,   old +amount);

        resourceAmountChange(entity,old,old+amount);

    }

    public RegistryObject<Attribute> getResourceMaxAttribute() {
        return ResourceMaxAttribute;
    }

    public RegistryObject<Attribute> getResourceRegenAttribute() {
        return ResourceRegenAttribute;
    }

    public boolean isDoesRegenerate() {
        return doesRegenerate;
    }
}
