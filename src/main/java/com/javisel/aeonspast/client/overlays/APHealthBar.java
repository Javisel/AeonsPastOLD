package com.javisel.aeonspast.client.overlays;

import com.javisel.aeonspast.AeonsPast;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;

@OnlyIn(Dist.CLIENT)
public class APHealthBar implements IIngameOverlay {


    private final ResourceLocation HEALTH_BAR_TEXTURE = new ResourceLocation(AeonsPast.MODID, "textures/gui/healthbar.png");

    Minecraft minecraft = Minecraft.getInstance();
    Player player = minecraft.player;

    @Override
    public void render(ForgeIngameGui gui, PoseStack mStack, float partialTicks, int width, int height) {


    }


}
