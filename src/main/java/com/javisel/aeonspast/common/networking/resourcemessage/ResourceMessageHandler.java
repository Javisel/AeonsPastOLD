package com.javisel.aeonspast.common.networking.resourcemessage;

import com.javisel.aeonspast.common.resource.Resource;
import net.minecraft.client.Minecraft;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ResourceMessageHandler {


    public static void handle(final ResourceMessage mes, Supplier<NetworkEvent.Context> ctx) {


        ctx.get().enqueueWork(() -> {

            Minecraft minecraft = Minecraft.getInstance();


            Resource resource = Resource.getResourceByLocation(mes.resourceId);

            resource.setResourceAmount(minecraft.player, mes.resource, false);


        });

        ctx.get().setPacketHandled(true);

    }


}
