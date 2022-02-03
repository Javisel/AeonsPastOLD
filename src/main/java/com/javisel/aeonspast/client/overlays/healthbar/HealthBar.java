package com.javisel.aeonspast.client.overlays.healthbar;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.client.RenderUtilities;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;

@OnlyIn(Dist.CLIENT)
public class HealthBar implements IIngameOverlay {


    private final ResourceLocation HEALTH_BAR_TEXTURES = new ResourceLocation(AeonsPast.MODID, "textures/gui/healthbar.png");
    private final float BAR_WIDTH = 80f;
    private final float BAR_HEIGHT = 10;
    private final float BAR_TEXTURE_WIDTH = 180;
    private final float BAR_TEXTURE_HEIGHT = 8;
    private final float FILLED_BAR_WIDTH = 78f;
    private final float FILLED_BAR_HEIGHT = 6;
    private final float FILLED_BAR_OFFSET_X = 1f;
    private final float FILLED_BAR_OFFSET_Y = 2f;
    private final float FILL_OFFSET_U = 1;
    private final float HEALTH_FILL_OFFSET_V = 8;
    private final float FILL_BAR_TEXTURE_WIDTH = 178;
    private final float FILL_BAR_TEXTURE_HEIGHT = 6;
    private final float WITHER_ICON_U = 183;
    private final float WITHER_ICON_V = 1;
    private final float WITHER_ICON_WIDTH = 9;
    private final float WITHER_ICON_HEIGHT = 9;
    Minecraft minecraft = Minecraft.getInstance();
    Player player = minecraft.player;
    private float lasthealth;
    private float healthBlinkTime;


    @Override
    public void render(ForgeIngameGui gui, PoseStack mStack, float partialTicks, int width, int height) {


        mStack.pushPose();
        if (gui.getGuiTicks() == 0) {

            lasthealth = player.getMaxHealth();

        }


        minecraft = Minecraft.getInstance();
        player = minecraft.player;
        lasthealth = player.getHealth();
        if (player.isCreative()) {

            return;
        }
        float lastHealthTime = Util.getMillis();
        float healthBlinkTime = gui.getGuiTicks() + 20;


        float currentHealth = player.getHealth();
        float maxHealth = player.getMaxHealth();

        float healthPercent = currentHealth / maxHealth;


        boolean highlight = false;


        if (lasthealth < currentHealth) {

            if (lasthealth + player.getAttributeValue(AttributeRegistration.HEALTH_REGENERATION.get()) < currentHealth) {

                highlight = true;

            }


        }

        if (lasthealth > currentHealth) {

            highlight = true;


        }


        float x = width / 2 - 91;
        float y = height - (30 + BAR_HEIGHT);


        RenderUtilities.renderTextureFromSprite(mStack, HEALTH_BAR_TEXTURES, BAR_WIDTH, BAR_HEIGHT, x, y, -3, 0, 0, BAR_TEXTURE_WIDTH, BAR_TEXTURE_HEIGHT);
        RenderUtilities.renderTextureFromSprite(mStack, HEALTH_BAR_TEXTURES, FILLED_BAR_WIDTH * healthPercent, FILLED_BAR_HEIGHT, x + FILLED_BAR_OFFSET_X, y + FILLED_BAR_OFFSET_Y, -2, FILL_OFFSET_U, HEALTH_FILL_OFFSET_V, FILL_BAR_TEXTURE_WIDTH * healthPercent, FILL_BAR_TEXTURE_HEIGHT);


        String healthdata = (int) (currentHealth) + "/" + (int) (maxHealth);







        if (player.getAttributeValue(AttributeRegistration.HEALTH_SHIELD.get()) > 0) {

            healthdata = (int) (currentHealth) + " + " + (int) player.getAttributeValue(AttributeRegistration.HEALTH_SHIELD.get()) + "/" + (int) (maxHealth);


        }


        mStack.scale(.9f, .9f, 1f);
        int stringx = (int) ((x) + BAR_WIDTH / 2);
        int stringy = (int) ((y) - (BAR_HEIGHT - 11));
        stringx *= 1 / .9;
        stringy *= 1 / .9;


        Gui.drawCenteredString(mStack, gui.getFont(), healthdata, stringx, stringy, -1);


        mStack.popPose();


    }


}
