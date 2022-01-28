package com.javisel.aeonspast.common.networking;

import com.javisel.aeonspast.common.resource.Resource;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ResourceMessage {

    public static float resource;
    public static ResourceLocation resourceId;

    public ResourceMessage(float mana, ResourceLocation resourceid) {

        resource = mana;
        resourceId = resourceid;

    }


    public static void encode(ResourceMessage pkt, FriendlyByteBuf buf) {
        buf.writeFloat(resource);
        buf.writeResourceLocation(resourceId);


    }

    public static ResourceMessage decode(FriendlyByteBuf buf) {
        return new ResourceMessage(buf.readFloat(), buf.readResourceLocation());
    }


    public static class Handler {

        public static void handle(final ResourceMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ctx.get().enqueueWork(() -> {

                Minecraft minecraft = Minecraft.getInstance();


                Resource resource = Resource.getResourceByLocation(resourceId);

                resource.setResourceAmount(minecraft.player, ResourceMessage.resource, false);


            });

            ctx.get().setPacketHandled(true);

        }
    }

}