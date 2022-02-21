package com.javisel.aeonspast.common.networking.serverdatamessage;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ServerDataMessage {


    public CompoundTag weaponDataTag;
    public CompoundTag armorDataTag;
    public CompoundTag trinketDataTag;

    public ServerDataMessage(CompoundTag weaponDataTag, CompoundTag armorDataTag, CompoundTag trinketDataTag) {
        this.weaponDataTag = weaponDataTag;
        this.armorDataTag = armorDataTag;
        this.trinketDataTag = trinketDataTag;

    }

    public static void encode(ServerDataMessage pkt, FriendlyByteBuf buf) {
        buf.writeNbt(pkt.weaponDataTag);
        buf.writeNbt(pkt.armorDataTag);
        buf.writeNbt(pkt.trinketDataTag);


    }

    public static ServerDataMessage decode(FriendlyByteBuf buf) {


        return new ServerDataMessage(buf.readNbt(), buf.readNbt(), buf.readNbt());
    }


    public static class Handler {

        public static void handle(final ServerDataMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ServerDataMessageHandler.handle(mes, ctx);


        }
    }

}