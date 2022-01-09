package com.javisel.aeonspast.utilities;

import com.javisel.aeonspast.common.capabiltiies.APEntityCapability;
import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import com.javisel.aeonspast.common.registration.CapabilityRegistration;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class APUtilities {


    public static IEntityData getEntityData(LivingEntity entity) {

        return  entity.getCapability(CapabilityRegistration.PLAYER_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);



    }


}
