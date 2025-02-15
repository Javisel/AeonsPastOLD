package com.javisel.aeonspast;

import com.javisel.aeonspast.client.InputHandler;
import com.javisel.aeonspast.client.main.ClientProxy;
import com.javisel.aeonspast.client.main.OverlayRegistration;
import com.javisel.aeonspast.common.items.AeonsPastItemGroup;
import com.javisel.aeonspast.common.registration.*;
import com.javisel.aeonspast.server.ServerHandler;
import com.javisel.aeonspast.utilities.StringKeys;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.chunk.RenderRegionCache;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
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
    public static final CreativeModeTab APTAB = new AeonsPastItemGroup();

    public AeonsPast() {
        GeckoLib.initialize();

        ParticleTypeRegistration.PARTICLE_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        AttributeRegistration.ATTRIBUTES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ResourceRegistration.RESOURCES.register(FMLJavaModLoadingContext.get().getModEventBus());

        SpellRegistration.SPELLS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemPropertyRegistration.ITEM_PROPERTIES.register(FMLJavaModLoadingContext.get().getModEventBus());

        ItemRegistration.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ClassRegistration.PLAYER_GAME_CLASSES.register(FMLJavaModLoadingContext.get().getModEventBus());

        EffectRegistration.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntityTraitRegistration.ENTITY_TRAITS.register(FMLJavaModLoadingContext.get().getModEventBus());
        EntityTypeRegistration.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());

        SoundEventRegistration.SOUND_EVENTS.register(FMLJavaModLoadingContext.get().getModEventBus());

         FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
         FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);

        MinecraftForge.EVENT_BUS.register(new GameEventHandler());
        MinecraftForge.EVENT_BUS.register(new ServerHandler());

        MinecraftForge.EVENT_BUS.register(this);
    }


    private void serverSetup(final FMLDedicatedServerSetupEvent setupEvent) {


    }

    private void commonSetup(final FMLCommonSetupEvent event) {


        PacketRegistration.registerMessages();

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
        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder(StringKeys.ACTIVE_SLOT_IDENTIFIER).priority(2).size(2).build());
        //Relic - Ultimate

        InterModComms.sendTo(CuriosApi.MODID, SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder(StringKeys.ULTIMATE_SLOT_IDENTIFIER).priority(3).size(1).build());

    }


}
