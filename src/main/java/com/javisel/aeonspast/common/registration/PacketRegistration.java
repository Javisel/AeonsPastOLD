package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.networking.abilitymessage.AbilityMessage;
import com.javisel.aeonspast.common.networking.playercapabilitiesmessage.PlayerCapabiltiiesMessage;
import com.javisel.aeonspast.common.networking.resourcemessage.ResourceMessage;
import com.javisel.aeonspast.common.networking.serverdatamessage.ServerDataMessage;
import com.javisel.aeonspast.common.networking.stacksyncmessage.StackSyncMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PacketRegistration {


    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(AeonsPast.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    private static int index = 1;

    public static void registerMessages() {

        registerMessage(ResourceMessage.class, ResourceMessage::encode, ResourceMessage::decode, ResourceMessage.Handler::handle);
        registerMessage(PlayerCapabiltiiesMessage.class, PlayerCapabiltiiesMessage::encode, PlayerCapabiltiiesMessage::decode, PlayerCapabiltiiesMessage.Handler::handle);
        registerMessage(AbilityMessage.class, AbilityMessage::encode, AbilityMessage::decode, AbilityMessage.Handler::handle);
        registerMessage(StackSyncMessage.class, StackSyncMessage::encode, StackSyncMessage::decode, StackSyncMessage.Handler::handle);
        registerMessage(ServerDataMessage.class, ServerDataMessage::encode, ServerDataMessage::decode, ServerDataMessage.Handler::handle);


    }


    private static <MSG> void registerMessage(Class<MSG> type, BiConsumer<MSG, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, MSG> decoder,
                                              BiConsumer<MSG, Supplier<NetworkEvent.Context>> consumer) {


        INSTANCE.registerMessage(++index, type, encoder, decoder, consumer);


    }
}
