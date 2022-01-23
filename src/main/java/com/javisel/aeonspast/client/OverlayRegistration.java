package com.javisel.aeonspast.client;

import com.javisel.aeonspast.client.overlays.APAbilityBar;
import com.javisel.aeonspast.client.overlays.healthbar.APHealthBar;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class OverlayRegistration {


    public static void registerOverlays() {

        OverlayRegistry.registerOverlayTop("Health_Bar", new APHealthBar());
        OverlayRegistry.registerOverlayTop("ability_bar", new APAbilityBar());


    }

}
