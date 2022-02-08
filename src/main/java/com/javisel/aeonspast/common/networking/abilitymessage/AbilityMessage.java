package com.javisel.aeonspast.common.networking.abilitymessage;

import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AbilityMessage {


    private static int slot;

    public AbilityMessage(int slot) {

        AbilityMessage.slot = slot;


    }


    public static void encode(AbilityMessage pkt, FriendlyByteBuf buf) {
        buf.writeInt(slot);
    }

    public static AbilityMessage decode(FriendlyByteBuf buf) {

        return new AbilityMessage(buf.readInt());
    }


    public static class Handler {

        public static void handle(final AbilityMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ctx.get().enqueueWork(() -> {


                Player player = ctx.get().getSender();


                IPlayerData playerData = Utilities.getPlayerData(player);

                playerData.getSpellBar().getSpellList().get(slot).attemptCast(player, Utilities.getEntityData(player).getOrCreateSpellStack(playerData.getSpellBar().getSpellList().get(slot)));


            });

            ctx.get().setPacketHandled(true);

        }
    }


}
