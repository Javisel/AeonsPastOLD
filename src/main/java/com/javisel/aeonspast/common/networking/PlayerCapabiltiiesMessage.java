package com.javisel.aeonspast.common.networking;

import com.javisel.aeonspast.common.capabiltiies.APEntityCapability;
import com.javisel.aeonspast.common.capabiltiies.APPlayerCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerCapabiltiiesMessage {
    
    public static CompoundTag entityDataTag;
    public static CompoundTag playerDataTag;




    public PlayerCapabiltiiesMessage(CompoundTag entityDataTag, CompoundTag playerDataTag) {

        this.entityDataTag=entityDataTag;
        this.playerDataTag=playerDataTag;
    }


    public static void encode(PlayerCapabiltiiesMessage pkt, FriendlyByteBuf buf) {
        buf.writeNbt(entityDataTag);
        buf.writeNbt(playerDataTag);
    }

    public static PlayerCapabiltiiesMessage decode(FriendlyByteBuf buf) {
        return new PlayerCapabiltiiesMessage(buf.readNbt(),buf.readNbt());
    }


    public static class Handler {

        public static void handle(final PlayerCapabiltiiesMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ctx.get().enqueueWork(() -> {

                Minecraft minecraft = Minecraft.getInstance();
                minecraft.player.getCapability(APEntityCapability.ENTITY_DATA_CAP, null).orElseThrow(NullPointerException::new).readNBT(mes.entityDataTag);
               minecraft.player.getCapability(APPlayerCapability.PLAYER_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new).readNBT(mes.playerDataTag);

            });

            ctx.get().setPacketHandled(true);

        }
    }






}
