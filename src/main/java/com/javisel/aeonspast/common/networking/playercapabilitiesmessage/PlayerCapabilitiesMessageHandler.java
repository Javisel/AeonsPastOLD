package com.javisel.aeonspast.common.networking.playercapabilitiesmessage;

import com.javisel.aeonspast.common.capabiltiies.entity.EntityCapability;
import com.javisel.aeonspast.common.capabiltiies.player.PlayerCapability;
import net.minecraft.client.Minecraft;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerCapabilitiesMessageHandler {


    public static void handle(final PlayerCapabiltiiesMessage mes, Supplier<NetworkEvent.Context> ctx) {


        ctx.get().enqueueWork(() -> {

            Minecraft minecraft = Minecraft.getInstance();
            minecraft.player.getCapability(EntityCapability.ENTITY_DATA_CAP, null).orElseThrow(NullPointerException::new).readNBT(mes.entityDataTag);
            minecraft.player.getCapability(PlayerCapability.PLAYER_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new).readNBT(mes.playerDataTag);

        });

        ctx.get().setPacketHandled(true);

    }
}
