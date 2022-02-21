package com.javisel.aeonspast.common.networking.abilitymessage;

import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WeaponAbilityMessage {


    public WeaponAbilityMessage() {


    }


    public static void encode(WeaponAbilityMessage pkt, FriendlyByteBuf buf) {

    }

    public static WeaponAbilityMessage decode(FriendlyByteBuf buf) {

        return new WeaponAbilityMessage();
    }


    public static class Handler {

        public static void handle(final WeaponAbilityMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ctx.get().enqueueWork(() -> {


                Player player = ctx.get().getSender();


                IPlayerData playerData = Utilities.getPlayerData(player);


                if (Spell.isSpellDefault(playerData.getActiveWeaponSpell())) {
                    return;
                }


                playerData.getActiveWeaponSpell().attemptCast(player, Utilities.getPlayerData(player).getSpellStack(playerData.getActiveWeaponSpell()));

            });

            ctx.get().setPacketHandled(true);

        }
    }


}
