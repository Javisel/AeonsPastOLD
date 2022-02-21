package com.javisel.aeonspast.common.networking.abilitymessage;

import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AbilityMessage {


    public static int slot;

    public AbilityMessage(int slot) {

        AbilityMessage.slot = slot;


    }


    public static void encode(AbilityMessage pkt, FriendlyByteBuf buf) {
        buf.writeInt(pkt.slot);
    }

    public static AbilityMessage decode(FriendlyByteBuf buf) {

        return new AbilityMessage(buf.readInt());
    }


    public static class Handler {

        public static void handle(final AbilityMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ctx.get().enqueueWork(() -> {


                Player player = ctx.get().getSender();


                IPlayerData playerData = Utilities.getPlayerData(player);


                if (playerData.getSpellBar().getSpellList().isEmpty() || Spell.isSpellDefault(playerData.getSpellBar().getSpellList().get(mes.slot))) {
                    return;
                }
                playerData.getSpellBar().getSpellList().get(slot).attemptCast(player, Utilities.getPlayerData(player).getSpellStack(playerData.getSpellBar().getSpellList().get(mes.slot)));


            });

            ctx.get().setPacketHandled(true);

        }
    }


}
