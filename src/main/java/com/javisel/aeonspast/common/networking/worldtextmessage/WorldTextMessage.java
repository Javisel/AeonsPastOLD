package com.javisel.aeonspast.common.networking.worldtextmessage;

import com.javisel.aeonspast.common.combat.DamageTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WorldTextMessage {

    public static int amount;
    public static int id;

    public static boolean critical = false;

    public WorldTextMessage(int amount, int type, boolean critical) {

        WorldTextMessage.amount = amount;
        id = type;
        WorldTextMessage.critical = critical;

    }


    public static void encode(WorldTextMessage pkt, FriendlyByteBuf buf) {
        buf.writeInt(amount);
        buf.writeInt(id);
        buf.writeBoolean(critical);


    }

    public static WorldTextMessage decode(FriendlyByteBuf buf) {
        return new WorldTextMessage(buf.readInt(), buf.readInt(), buf.readBoolean());
    }




    public static class Handler {

        public static void handle(final WorldTextMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ctx.get().enqueueWork(() -> {


            });

            ctx.get().setPacketHandled(true);

        }
    }
}









