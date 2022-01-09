package com.javisel.aeonspast.common.capabiltiies;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class APEntityCapability extends CapabilityProvider {



    public static Capability<IEntityData> ENTITY_DATA_CAP = CapabilityManager.get(new CapabilityToken<>(){});




    protected APEntityCapability(Class baseClass) {






        super(baseClass);
    }

    protected APEntityCapability(Class baseClass, boolean isLazy) {
        super(baseClass, isLazy);
    }
}