package com.javisel.aeonspast;

import com.javisel.aeonspast.client.OverlayRegistration;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.registration.ClassRegistration;
import com.javisel.aeonspast.common.registration.EffectRegistration;
import com.javisel.aeonspast.common.registration.ItemRegistration;
import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;


@Mod(AeonsPast.MODID)
public class AeonsPast {
    public static final String MODID = "aeonspast";
    public static final Logger LOGGER = LogManager.getLogger();

    public AeonsPast() {
        GeckoLib.initialize();

        AttributeRegistration.ATTRIBUTES.register(FMLJavaModLoadingContext.get().getModEventBus());

        ItemRegistration.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EffectRegistration.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());

        ClassRegistration.PLAYER_GAME_CLASSES.register(FMLJavaModLoadingContext.get().getModEventBus());

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(new GameEventHandler());

        MinecraftForge.EVENT_BUS.register(this);
    }


    private void commonSetup(final FMLCommonSetupEvent event) {
        //  CapabilityManager.INSTANCE.register(APILivingEntityData.class, new LivingEntityDataStorage(), LivingEntityData::new);

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        OverlayRegistration.registerOverlays();


    }


    private void enqueueIMC(final InterModEnqueueEvent event) {

        //Class Emblem
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("emblem").priority(1).size(1).build());

        //Relic - Ultimate
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("relic").priority(1).size(1).build());

        //Amulets ( 3 Actives )
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("amulet").priority(3).size(3).build());


        //Trinkets - 3 Passives
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("trinket").priority(4).size(3).build());

    }


}
