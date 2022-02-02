package com.javisel.aeonspast.common.networking;

import com.javisel.aeonspast.GameEventHandler;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class StackSyncMessage {


    public StackSyncMessage() {


    }


    public static void encode(StackSyncMessage pkt, FriendlyByteBuf buf) {
    }

    public static StackSyncMessage decode(FriendlyByteBuf buf) {

        return new StackSyncMessage();
    }


    public static class Handler {

        public static void handle(final StackSyncMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ctx.get().enqueueWork(() -> {


                 Player player = ctx.get().getSender();


                IPlayerData playerData = Utilities.getPlayerData(player);


                for (ItemStack stack : player.getInventory().items) {

                    if (!stack.isEmpty()) {

                       ItemEngine.initializeItem(player,stack);

                     }


                }


                AbstractContainerMenu menu = player.containerMenu;






                     try {
                         if (menu.getType() != MenuType.CRAFTING) {

                             for (ItemStack stack : menu.getItems()) {

                                 if (!stack.isEmpty()) {

                                     ItemEngine.initializeItem(player,stack);
                                 }


                             }
                         }
                     }
                     catch (UnsupportedOperationException e){


                     }





            });

                ctx.get().setPacketHandled(true);

            }
        }


    }
