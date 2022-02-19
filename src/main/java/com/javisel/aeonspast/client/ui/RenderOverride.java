package com.javisel.aeonspast.client.ui;

import com.javisel.aeonspast.client.main.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderNameplateEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class RenderOverride {


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


    @SubscribeEvent
    public static void renderEntityOverride(RenderLivingEvent.Post entityViewRenderEvent) {


        ClientProxy.inWorldRenderer.addEntityToRenderQue(entityViewRenderEvent.getEntity());
         ClientProxy.inWorldRenderer.renderBar(        entityViewRenderEvent.getPoseStack(), Minecraft.getInstance().gameRenderer.getMainCamera());



     }


     @SubscribeEvent
    public static void disableRenderingOfNamePlates(RenderNameplateEvent event) {

        event.setResult(Event.Result.DENY);


     }


}
