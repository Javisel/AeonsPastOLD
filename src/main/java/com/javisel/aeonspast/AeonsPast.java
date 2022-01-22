package com.javisel.aeonspast;

import com.javisel.aeonspast.client.InputHandler;
import com.javisel.aeonspast.client.OverlayRegistration;
import com.javisel.aeonspast.client.main.ClientProxy;
import com.javisel.aeonspast.common.networking.PacketHandler;
import com.javisel.aeonspast.common.registration.*;
import com.javisel.aeonspast.utilities.StringKeys;
import net.minecraftforge.common.MinecraftForge;
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


        SpellRegistration.SPELLS.register(FMLJavaModLoadingContext.get().getModEventBus());

        ItemRegistration.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ClassRegistration.PLAYER_GAME_CLASSES.register(FMLJavaModLoadingContext.get().getModEventBus());

        EffectRegistration.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());


        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(new GameEventHandler());

        MinecraftForge.EVENT_BUS.register(this);
    }


    private void commonSetup(final FMLCommonSetupEvent event) {


        PacketHandler.registerMessages();

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        ClientProxy.Init();
        OverlayRegistration.registerOverlays();
        InputHandler.registerKeyBindings();

    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

        //Class Emblem
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder(StringKeys.EMBLEM_IDENTIFIER).priority(1).size(1).build());



        //Trinkets - 3 Passives
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder(StringKeys.TRINKET_IDENTIFIER).priority(4).size(3).build());
     //Relic - Ultimate



        //Amulets ( 2 Actives )
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder(StringKeys.AMULET_IDENTIFIER).priority(2).size(2).build());
        //Relic - Ultimate

        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder(StringKeys.RELIC_IDENTIFIER).priority(3).size(1).build());

    }











}
