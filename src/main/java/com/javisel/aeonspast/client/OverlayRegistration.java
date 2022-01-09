package com.javisel.aeonspast.client;

import com.javisel.aeonspast.client.overlays.APHealthBar;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class OverlayRegistration {


    public static void registerOverlays() {

        Minecraft minecraft = Minecraft.getInstance();
        OverlayRegistry.registerOverlayBottom("ap_health", new APHealthBar());


    }

}
