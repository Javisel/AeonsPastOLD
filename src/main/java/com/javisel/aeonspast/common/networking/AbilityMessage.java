package com.javisel.aeonspast.common.networking;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.capabiltiies.APEntityCapability;
import com.javisel.aeonspast.common.capabiltiies.IPlayerData;
import com.javisel.aeonspast.common.enums.TrinketEnums;
import com.javisel.aeonspast.common.items.TrinketItem;
import com.javisel.aeonspast.utilities.APUtilities;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AbilityMessage {


     private static int slot;

    public AbilityMessage( int slot) {

         this.slot=slot;



    }




    public static void encode(AbilityMessage pkt, FriendlyByteBuf buf) {
         buf.writeInt(slot);
    }

    public static AbilityMessage decode(FriendlyByteBuf buf) {

        return new  AbilityMessage( buf.readInt());
    }


    public static class Handler {

        public static void handle(final AbilityMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ctx.get().enqueueWork(() -> {

                Player player = ctx.get().getSender();


                IPlayerData playerData = APUtilities.getPlayerData(player);

                playerData.getSpellBar().get(mes.slot).attemptCast(player,APUtilities.getEntityData(player).getOrCreateSpellStack(playerData.getSpellBar().get(mes.slot)));



            });

            ctx.get().setPacketHandled(true);

        }
    }



}
