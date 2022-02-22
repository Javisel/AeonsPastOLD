package com.javisel.aeonspast.common.capabiltiies.projectile;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ProjectileCapability extends CapabilityProvider {


    public static Capability<IProjectileData> PROJECTILE_DATA_CAP = CapabilityManager.get(new CapabilityToken<>() {
    });


    protected ProjectileCapability(Class baseClass) {


        super(baseClass);
    }

    protected ProjectileCapability(Class baseClass, boolean isLazy) {
        super(baseClass, isLazy);
    }
}