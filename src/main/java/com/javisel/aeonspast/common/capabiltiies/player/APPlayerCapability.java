package com.javisel.aeonspast.common.capabiltiies.player;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class APPlayerCapability extends CapabilityProvider {


    public static Capability<IPlayerData> PLAYER_DATA_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });


    protected APPlayerCapability(Class baseClass) {


        super(baseClass);
    }

    protected APPlayerCapability(Class baseClass, boolean isLazy) {
        super(baseClass, isLazy);
    }
}