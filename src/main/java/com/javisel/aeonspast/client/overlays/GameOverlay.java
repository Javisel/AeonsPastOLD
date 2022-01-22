package com.javisel.aeonspast.client.overlays;

import com.javisel.aeonspast.client.overlays.healthbar.APHealthBar;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class GameOverlay {


    static APHealthBar apHealthBar = new APHealthBar();

    @SubscribeEvent
    public static void disableOldElements(RenderGameOverlayEvent.PreLayer prerenderGameOverlayEvent) {


        if (prerenderGameOverlayEvent.getOverlay() == ForgeIngameGui.PLAYER_HEALTH_ELEMENT) {

            prerenderGameOverlayEvent.setCanceled(true);


        }


        if (prerenderGameOverlayEvent.getOverlay() == ForgeIngameGui.ARMOR_LEVEL_ELEMENT) {

            prerenderGameOverlayEvent.setCanceled(true);

        }


        if (prerenderGameOverlayEvent.getOverlay() == ForgeIngameGui.FOOD_LEVEL_ELEMENT) {

            //   prerenderGameOverlayEvent.setCanceled(true);

        }


    }


}
