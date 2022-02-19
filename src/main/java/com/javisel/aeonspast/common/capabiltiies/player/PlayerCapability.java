package com.javisel.aeonspast.common.capabiltiies.player;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class PlayerCapability extends CapabilityProvider {


    public static Capability<IPlayerData> PLAYER_DATA_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });


    protected PlayerCapability(Class baseClass) {


        super(baseClass);
    }

    protected PlayerCapability(Class baseClass, boolean isLazy) {
        super(baseClass, isLazy);
    }
}