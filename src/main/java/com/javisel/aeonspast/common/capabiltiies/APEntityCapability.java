package com.javisel.aeonspast.common.capabiltiies;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.CapabilityToken;


public class APEntityCapability extends CapabilityProvider {


    public static Capability<IEntityData> ENTITY_DATA_CAP = CapabilityManager.get(new CapabilityToken<>() {
    });


    protected APEntityCapability(Class baseClass) {


        super(baseClass);
    }

    protected APEntityCapability(Class baseClass, boolean isLazy) {
        super(baseClass, isLazy);
    }
}