package com.javisel.aeonspast.common.networking.playercapabilitiesmessage;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.capabiltiies.entity.EntityCapability;
import com.javisel.aeonspast.common.capabiltiies.player.APPlayerCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import org.apache.logging.log4j.Level;

import java.util.function.Supplier;

public class PlayerCapabiltiiesMessage {

    public   CompoundTag entityDataTag;
    public   CompoundTag playerDataTag;


    public PlayerCapabiltiiesMessage(CompoundTag entityDataTag, CompoundTag playerDataTag) {

         this.entityDataTag = entityDataTag;
       this.playerDataTag = playerDataTag;
    }


    public static void encode(PlayerCapabiltiiesMessage pkt, FriendlyByteBuf buf) {
        buf.writeNbt(pkt.entityDataTag);
        buf.writeNbt(pkt.playerDataTag);

        AeonsPast.LOGGER.log(Level.TRACE, "Expensive Operation, syncing total capabiltiies!");
    }

    public static PlayerCapabiltiiesMessage decode(FriendlyByteBuf buf) {
        return new PlayerCapabiltiiesMessage(buf.readNbt(), buf.readNbt());
    }


    public static class Handler {

        public static void handle(final PlayerCapabiltiiesMessage mes, Supplier<NetworkEvent.Context> ctx) {


            PlayerCapabilitiesMessageHandler.handle(mes,ctx);

        }
    }


}
