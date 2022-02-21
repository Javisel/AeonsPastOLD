package com.javisel.aeonspast.server;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.config.armor.ArmorDataLoader;
import com.javisel.aeonspast.common.config.entity.EntityDataLoader;
import com.javisel.aeonspast.common.config.entity.EntityStatisticalData;
import com.javisel.aeonspast.common.config.playerclasses.ClassDataLoader;
import com.javisel.aeonspast.common.config.trinket.TrinketDataLoader;
import com.javisel.aeonspast.common.config.weapon.WeaponDataLoader;
import com.javisel.aeonspast.common.networking.serverdatamessage.ServerDataMessage;
import com.javisel.aeonspast.common.registration.PacketRegistration;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkDirection;
import org.apache.logging.log4j.Level;


@Mod.EventBusSubscriber()
public class ServerHandler {
    public static ClassDataLoader CLASS_STATISTICS_LOADER;
    public static WeaponDataLoader WEAPON_STATISTICS_LOADER;
    public static ArmorDataLoader ARMOR_DATA_LOADER;
    public static EntityDataLoader ENTITY_DATA_LOADER;
    public static TrinketDataLoader TRINKET_DATA_LOADER;

    @SubscribeEvent
    public static void addDataHandlers(AddReloadListenerEvent event) {


        ServerHandler.WEAPON_STATISTICS_LOADER = new WeaponDataLoader();
        ServerHandler.ARMOR_DATA_LOADER = new ArmorDataLoader();


        ServerHandler.CLASS_STATISTICS_LOADER = new ClassDataLoader();

        ServerHandler.ENTITY_DATA_LOADER = new EntityDataLoader();
        TRINKET_DATA_LOADER = new TrinketDataLoader();
        event.addListener(ServerHandler.CLASS_STATISTICS_LOADER);
        event.addListener(ServerHandler.WEAPON_STATISTICS_LOADER);
        event.addListener(ServerHandler.ENTITY_DATA_LOADER);
        event.addListener(ServerHandler.ARMOR_DATA_LOADER);
        event.addListener(ServerHandler.TRINKET_DATA_LOADER);


    }


    @SubscribeEvent
    public static void syncPlayerData(PlayerEvent.PlayerLoggedInEvent event) {


        if (!event.getPlayer().level.isClientSide) {


            Utilities.syncTotalPlayerData(event.getPlayer());


            ServerDataMessage serverDataMessage = new ServerDataMessage(WEAPON_STATISTICS_LOADER.toNBT(), ARMOR_DATA_LOADER.toNBT(), TRINKET_DATA_LOADER.toNBT());

            ServerPlayer player = (ServerPlayer) event.getPlayer();
            PacketRegistration.INSTANCE.sendTo(serverDataMessage, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);

        }


    }


    @SubscribeEvent
    public static void newEntityData(EntityJoinWorldEvent event) {


        if (!event.getWorld().isClientSide) {
            if (ServerHandler.ENTITY_DATA_LOADER.getEntityData(event.getEntity()) != null) {

                EntityStatisticalData data = ServerHandler.ENTITY_DATA_LOADER.getEntityData(event.getEntity());


                data.loadtoEntity((LivingEntity) event.getEntity());


            } else {

                AeonsPast.LOGGER.log(Level.TRACE, event.getEntity().getType().getRegistryName().toString() + " has no stat data!");

            }


        }

    }
}
