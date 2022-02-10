package com.javisel.aeonspast.common.networking.serverdatamessage;

import com.javisel.aeonspast.client.main.ClientProxy;
import com.javisel.aeonspast.common.config.ArmorData;
import com.javisel.aeonspast.common.config.WeaponData;
import com.javisel.aeonspast.common.networking.resourcemessage.ResourceMessageHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ServerDataMessage {


    public CompoundTag weaponDataTag;
    public CompoundTag armorDataTag;

    public ServerDataMessage(CompoundTag weaponDataTag, CompoundTag armorDataTag ) {
        this.weaponDataTag = weaponDataTag;
        this.armorDataTag = armorDataTag;

    }

    public static void encode(ServerDataMessage pkt, FriendlyByteBuf buf) {
      buf.writeNbt(pkt.weaponDataTag);
        buf.writeNbt(pkt.armorDataTag);



    }

    public static ServerDataMessage decode(FriendlyByteBuf buf) {


        return  new ServerDataMessage(buf.readNbt(),buf.readNbt() );
    }



    public static class Handler {

        public static void handle(final ServerDataMessage mes, Supplier<NetworkEvent.Context> ctx) {





          ServerDataMessageHandler.handle(mes,ctx);


        }
    }

}