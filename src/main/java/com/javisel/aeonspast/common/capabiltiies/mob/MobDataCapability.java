package com.javisel.aeonspast.common.capabiltiies.mob;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class MobDataCapability extends CapabilityProvider {


    public static Capability<IMobData> MOB_DATA_CAP = CapabilityManager.get(new CapabilityToken<>() {
    });


    protected MobDataCapability(Class baseClass) {


        super(baseClass);
    }

    protected MobDataCapability(Class baseClass, boolean isLazy) {
        super(baseClass, isLazy);
    }
}