package com.javisel.aeonspast.client.main;

import com.javisel.aeonspast.client.overlays.SpellAtlasHolder;
import com.javisel.aeonspast.client.spell.SpellRenderer;
import com.javisel.aeonspast.common.spell.Spell;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryManager;

import java.util.ArrayList;

import static com.javisel.aeonspast.AeonsPast.MODID;
import static com.javisel.aeonspast.ModBusEventHandler.SPELL_REGISTRY_NAME;


@Mod.EventBusSubscriber
@OnlyIn(Dist.CLIENT)
public class ClientProxy {


    public static final ResourceLocation SPELL_TEXTURES_LOCATION = new ResourceLocation(MODID, "textures/atlas/spells.png");
    public static SpellRenderer spellRenderer;

    public static SpellAtlasHolder spellAtlasHolder;


    public static void Init() {


        Minecraft minecraft = Minecraft.getInstance();


        spellRenderer = new SpellRenderer(minecraft.textureManager, minecraft.getModelManager());


    }


    @OnlyIn(Dist.CLIENT)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class modBusEvents {


        @SubscribeEvent
        public static void textures(TextureStitchEvent.Pre event) {



        }

        @SubscribeEvent
        public static void reload(RegisterClientReloadListenersEvent event) {


            Minecraft minecraft = Minecraft.getInstance();

            spellAtlasHolder = new SpellAtlasHolder(minecraft.getTextureManager());

            event.registerReloadListener(spellAtlasHolder);


        }


    }

}
