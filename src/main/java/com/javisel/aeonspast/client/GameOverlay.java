package com.javisel.aeonspast.client;

import com.javisel.aeonspast.AeonsPast;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class GameOverlay {

    static long healthBlinkTime;
    static int tickCount;
    static int lastHealth;
    static long lastHealthTime;
    private final ResourceLocation HEALTH_BAR_TEXTURE = new ResourceLocation(AeonsPast.MODID, "textures/gui/healthbar.png");
    Minecraft minecraft = Minecraft.getInstance();
    Player player = minecraft.player;

    @SubscribeEvent
    public static void disableOldElements(RenderGameOverlayEvent.PreLayer prerenderGameOverlayEvent) {


        if (prerenderGameOverlayEvent.getOverlay() == ForgeIngameGui.PLAYER_HEALTH_ELEMENT) {

            prerenderGameOverlayEvent.setCanceled(true);


        }


        if (prerenderGameOverlayEvent.getOverlay() == ForgeIngameGui.ARMOR_LEVEL_ELEMENT) {

            prerenderGameOverlayEvent.setCanceled(true);

        }
        if (prerenderGameOverlayEvent.getOverlay() == ForgeIngameGui.EXPERIENCE_BAR_ELEMENT) {

            prerenderGameOverlayEvent.setCanceled(true);

        }


    }


}
