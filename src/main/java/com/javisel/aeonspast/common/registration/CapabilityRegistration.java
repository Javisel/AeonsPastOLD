package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class CapabilityRegistration {



    public static Capability<IEntityData> PLAYER_DATA_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});








}
