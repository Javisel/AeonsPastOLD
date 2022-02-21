package com.javisel.aeonspast.common.capabiltiies.projectile;

import com.javisel.aeonspast.common.capabiltiies.projectile.IProjectileData;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ProjectileCapability extends CapabilityProvider {


    public static Capability<IProjectileData> PLAYER_DATA_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });


    protected ProjectileCapability(Class baseClass) {


        super(baseClass);
    }

    protected ProjectileCapability(Class baseClass, boolean isLazy) {
        super(baseClass, isLazy);
    }
}