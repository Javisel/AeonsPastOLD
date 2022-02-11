package com.javisel.aeonspast.client.main;

import com.google.common.collect.ImmutableMap;
import com.javisel.aeonspast.client.world.InWorldRenderer;
import com.javisel.aeonspast.client.overlays.SpellAtlasHolder;
import com.javisel.aeonspast.client.spell.SpellRenderer;
import com.javisel.aeonspast.common.config.ArmorData;
import com.javisel.aeonspast.common.config.WeaponData;
import com.javisel.aeonspast.common.config.EntityStatisticalData;
import com.javisel.aeonspast.common.playerclasses.ClassData;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;

import static com.javisel.aeonspast.AeonsPast.MODID;


@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)

public class ClientProxy {


    public static final ResourceLocation SPELL_TEXTURES_LOCATION = new ResourceLocation(MODID, "textures/atlas/spells.png");
    public static SpellRenderer spellRenderer;

    public static SpellAtlasHolder spellAtlasHolder;
    public static InWorldRenderer inWorldRenderer = new InWorldRenderer();

    public static Map<ResourceLocation, WeaponData> weaponStatisticsMap = ImmutableMap.of();
    public static Map<ResourceLocation, ClassData> classStatisticsMap = ImmutableMap.of();
    public static Map<ResourceLocation, ArmorData> armorStatisticsMap = ImmutableMap.of();
    public static Map<ResourceLocation, EntityStatisticalData> entityStatisticsMap = ImmutableMap.of();








    public static void Init() {


        Minecraft minecraft = Minecraft.getInstance();


        spellRenderer = new SpellRenderer(minecraft.textureManager, minecraft.getModelManager());


    }


    @OnlyIn(Dist.CLIENT)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class modBusEvents {


        @SubscribeEvent
        public static void reload(RegisterClientReloadListenersEvent event) {


            Minecraft minecraft = Minecraft.getInstance();

            spellAtlasHolder = new SpellAtlasHolder(minecraft.getTextureManager());

            event.registerReloadListener(spellAtlasHolder);


        }


    }















}
