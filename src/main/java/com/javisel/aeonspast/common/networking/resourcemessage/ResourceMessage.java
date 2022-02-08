package com.javisel.aeonspast.common.networking.resourcemessage;

import com.javisel.aeonspast.common.resource.Resource;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ResourceMessage {

    public   float resource;
    public   ResourceLocation resourceId;

    public ResourceMessage(float mana, ResourceLocation resourceid) {

        resource = mana;
        resourceId = resourceid;

    }


    public static void encode(ResourceMessage pkt, FriendlyByteBuf buf) {
        buf.writeFloat(pkt.resource);
        buf.writeResourceLocation(pkt.resourceId);


    }

    public static ResourceMessage decode(FriendlyByteBuf buf) {
        return new ResourceMessage(buf.readFloat(), buf.readResourceLocation());
    }



     public static class Handler {

        public static void handle(final ResourceMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ResourceMessageHandler.handle(mes,ctx);


        }
    }

}