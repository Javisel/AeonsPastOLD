package com.javisel.aeonspast.common.networking.serverdatamessage;

import com.google.common.collect.ImmutableMap;
import com.javisel.aeonspast.client.main.ClientProxy;
import com.javisel.aeonspast.common.config.ArmorData;
import com.javisel.aeonspast.common.config.WeaponData;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ServerDataMessageHandler {

    public static void handle(final ServerDataMessage mes, Supplier<NetworkEvent.Context> ctx) {

        ImmutableMap.Builder<ResourceLocation, WeaponData> weaponDataBuilder = ImmutableMap.builder();


        for (String key : mes.weaponDataTag.getAllKeys()) {


            WeaponData data = WeaponData.fromNBT(mes.weaponDataTag.getCompound(key));



            ResourceLocation location = new ResourceLocation(key);


            weaponDataBuilder.put(location, data);




        }

        ClientProxy.weaponStatisticsMap=weaponDataBuilder.build();


        ImmutableMap.Builder<ResourceLocation, ArmorData> armorDataBuilder = ImmutableMap.builder();


        for (String key : mes.armorDataTag.getAllKeys()) {

            ResourceLocation location = new ResourceLocation(key);

            ArmorData data = ArmorData.fromNBT(mes.armorDataTag.getCompound(key));


                armorDataBuilder.put(location,data);



        }
        ClientProxy.armorStatisticsMap=armorDataBuilder.build();





    }
}
