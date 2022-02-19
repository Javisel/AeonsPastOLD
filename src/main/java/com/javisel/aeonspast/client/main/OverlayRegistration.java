package com.javisel.aeonspast.client.main;

import com.javisel.aeonspast.client.ui.AbilityBar;
import com.javisel.aeonspast.client.ui.healthbar.HealthBar;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(Dist.CLIENT)
public class OverlayRegistration {


    public static void registerOverlays() {

        OverlayRegistry.registerOverlayTop("Health_Bar", new HealthBar());
        OverlayRegistry.registerOverlayTop("ability_bar", new AbilityBar());


    }

}
