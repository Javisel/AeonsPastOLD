package com.javisel.aeonspast.common.capabiltiies.itemstack;

import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class ItemStackDataCapability extends CapabilityProvider {


    public static Capability<IItemStackData> ITEM_STACK_DATA_CAP = CapabilityManager.get(new CapabilityToken<>() {
    });


    protected ItemStackDataCapability(Class baseClass) {


        super(baseClass);
    }

    protected ItemStackDataCapability(Class baseClass, boolean isLazy) {
        super(baseClass, isLazy);
    }
}