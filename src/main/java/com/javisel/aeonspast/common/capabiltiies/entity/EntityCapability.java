package com.javisel.aeonspast.common.capabiltiies.entity;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.CapabilityToken;








public class EntityCapability extends CapabilityProvider {


    public static Capability<IEntityData> ENTITY_DATA_CAP = CapabilityManager.get(new CapabilityToken<>() {
    });


    protected EntityCapability(Class baseClass) {


        super(baseClass);
    }

    protected EntityCapability(Class baseClass, boolean isLazy) {
        super(baseClass, isLazy);
    }
}