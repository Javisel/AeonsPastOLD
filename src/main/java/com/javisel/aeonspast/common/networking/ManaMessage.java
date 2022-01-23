package com.javisel.aeonspast.common.networking;

import com.javisel.aeonspast.common.capabiltiies.APEntityCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaMessage {

    public static float mana;


    public ManaMessage(float mana) {

        this.mana = mana;

    }


    public static void encode(ManaMessage pkt, FriendlyByteBuf buf) {
        buf.writeFloat(mana);
    }

    public static ManaMessage decode(FriendlyByteBuf buf) {
        return new ManaMessage(buf.readFloat());
    }


    public static class Handler {

        public static void handle(final ManaMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ctx.get().enqueueWork(() -> {

                Minecraft minecraft = Minecraft.getInstance();
                minecraft.player.getCapability(APEntityCapability.ENTITY_DATA_CAP, null).orElseThrow(NullPointerException::new).setMana(mes.mana);

            });

            ctx.get().setPacketHandled(true);

        }
    }

}