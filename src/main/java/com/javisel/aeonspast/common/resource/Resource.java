package com.javisel.aeonspast.common.resource;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.resources.ResourceLocation;
 import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;

public class Resource extends net.minecraftforge.registries.ForgeRegistryEntry<Resource> {


    private final RegistryObject<Attribute> ResourceMaxAttribute;
    private final RegistryObject<Attribute> ResourceRegenAttribute;
    private boolean doesRegenerate = true;

    public Resource(RegistryObject<Attribute> resourceMaxAttribute, RegistryObject<Attribute> resourceRegenAttribute) {
        ResourceMaxAttribute = resourceMaxAttribute;
        ResourceRegenAttribute = resourceRegenAttribute;
    }

    public static Resource getResourceByLocation(ResourceLocation resourceLocation) {


        return resourceLocation == null ? null : (Resource) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.RESOURCE_REGISTRY_NAME).getValue(resourceLocation);
    }

    public boolean doesNaturallyRegenerate(Player entity) {


        return true;
    }

    public Resource setNoRegen() {


        doesRegenerate = false;
        return this;

    }

    public void setResourceAmount(Player entity, float amount, boolean sync) {

        IPlayerData data = Utilities.getPlayerData(entity);

        float old = data.getOrCreateResource(this);


        if (data.getResourceMap().containsKey(this)) {


            data.getResourceMap().replace(this, amount);
        } else {


            data.getResourceMap().put(this, amount);
        }


        resourceAmountChange(entity, old, amount);

         if (sync && !entity.level.isClientSide) {


            Utilities.syncResourceData((Player) entity, this);
        }


    }

    public void tick(Player entity) {

        IEntityData data = Utilities.getEntityData(entity);

        if (data.getTicks() == 20) {

            if (doesNaturallyRegenerate(entity)) {


                addResource(entity, (float) entity.getAttributeValue(ResourceRegenAttribute.get()), false);


            }

        }


    }

    public void resourceAmountChange(Player entity, float oldamount, float newamount) {

    }

    public void addResource(Player entity, float amount, boolean sync) {

        IPlayerData data = Utilities.getPlayerData(entity);

        float old = data.getOrCreateResource(this);


        if (old + amount < 0) {

            amount = old;


        }

        if (old + amount > entity.getAttribute(ResourceMaxAttribute.get()).getValue()) {

            amount = (float) (entity.getAttributeValue(ResourceMaxAttribute.get()) - old);

        }

        setResourceAmount(entity, amount + old, sync);


    }

    public RegistryObject<Attribute> getResourceMaxAttribute() {
        return ResourceMaxAttribute;
    }

    public RegistryObject<Attribute> getResourceRegenAttribute() {
        return ResourceRegenAttribute;
    }

    public boolean doesRegenerate() {
        return doesRegenerate;
    }

}
