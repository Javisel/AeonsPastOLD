package com.javisel.aeonspast.common.networking;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.capabiltiies.entity.EntityCapability;
import com.javisel.aeonspast.common.capabiltiies.player.APPlayerCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.apache.logging.log4j.Level;

import java.util.function.Supplier;

public class PlayerCapabiltiiesMessage {

    public static CompoundTag entityDataTag;
    public static CompoundTag playerDataTag;


    public PlayerCapabiltiiesMessage(CompoundTag entityDataTag, CompoundTag playerDataTag) {

        PlayerCapabiltiiesMessage.entityDataTag = entityDataTag;
        PlayerCapabiltiiesMessage.playerDataTag = playerDataTag;
    }


    public static void encode(PlayerCapabiltiiesMessage pkt, FriendlyByteBuf buf) {
        buf.writeNbt(entityDataTag);
        buf.writeNbt(playerDataTag);

        AeonsPast.LOGGER.log(Level.TRACE, "Expensive Operation, syncing total capabiltiies!");
    }

    public static PlayerCapabiltiiesMessage decode(FriendlyByteBuf buf) {
        return new PlayerCapabiltiiesMessage(buf.readNbt(), buf.readNbt());
    }


    public static class Handler {

        public static void handle(final PlayerCapabiltiiesMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ctx.get().enqueueWork(() -> {

                Minecraft minecraft = Minecraft.getInstance();
                minecraft.player.getCapability(EntityCapability.ENTITY_DATA_CAP, null).orElseThrow(NullPointerException::new).readNBT(entityDataTag);
                minecraft.player.getCapability(APPlayerCapability.PLAYER_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new).readNBT(playerDataTag);

            });

            ctx.get().setPacketHandled(true);

        }
    }


}
