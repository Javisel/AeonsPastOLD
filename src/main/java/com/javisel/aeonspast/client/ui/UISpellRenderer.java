package com.javisel.aeonspast.client.ui;

import com.javisel.aeonspast.client.RenderUtilities;
import com.javisel.aeonspast.client.main.ClientProxy;
import com.javisel.aeonspast.client.spell.SpellModelShaper;
import com.javisel.aeonspast.client.ui.AbilityBar;
import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.spell.ICustomUseRequirement;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.Utilities;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static com.javisel.aeonspast.common.registration.SpellRegistration.SPELLS;

@OnlyIn(Dist.CLIENT)

public class UISpellRenderer {


    private final SpellModelShaper spellModelShaper;
    private final TextureManager textureManager;

    private final RenderType SPELL_RENDER_TYPE = RenderType.translucent();

    public UISpellRenderer(TextureManager textureManager, ModelManager modelManager) {
        this.spellModelShaper = new SpellModelShaper(modelManager);
        this.textureManager = textureManager;


        for (RegistryObject<Spell> spell : SPELLS.getEntries()) {

            this.spellModelShaper.register(spell.get(), new ModelResourceLocation(spell.getId(), "hud"));


        }


    }

    public void render(ForgeIngameGui gui, SpellStack stack, PoseStack poseStack, int x, int y, int z, boolean renderDefault) {


        TextureAtlasSprite sprite = ClientProxy.spellAtlasHolder.get(stack.getSpell());


        RenderUtilities.renderFromAtlas(poseStack, sprite, 16, 16, x, y, z, sprite.getU1(), sprite.getV1(), sprite.getWidth(), sprite.getHeight());

    }


    public void renderSpellAndSpellInfo(ForgeIngameGui gui, SpellStack spellStack, PoseStack poseStack, int x, int y, int z) {


        renderSpellInfo(gui, poseStack, spellStack, x, y, z);

    }


    public void renderSpellInfo(ForgeIngameGui gui, PoseStack poseStack, SpellStack spellStack, int xpos, int ypos, int z) {

        Minecraft minecraft = Minecraft.getInstance();

        Player player = minecraft.player;
        IPlayerData playerData = Utilities.getPlayerData(player);
        IEntityData entityData = Utilities.getEntityData(player);

        if (spellStack == null) {
            return;
        }
        Spell spell = spellStack.getSpell();

        Tesselator tesselator = Tesselator.getInstance();


        poseStack.pushPose();

        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();


        if (spellStack.getCharges() < 1) {


            RenderSystem.disableColorLogicOp();

            RenderSystem.setShaderColor(128 / 255, 128 / 255, 128 / 255, 1f);
        }

        z++;
        render(gui, spellStack, poseStack, xpos, ypos, z, false);


        z++;


        if (spell.getCostResource(player, spellStack) != null) {
            if (playerData.getOrCreateResource(spell.getCostResource(player, spellStack)) < spell.getCost(player, spellStack)) {


                RenderUtilities.renderTextureFromSprite(poseStack, AbilityBar.ABILITY_BAR_TEXTURES, 16, 16, xpos, ypos, z, 60, 9, 16, 16);

            }

        }

        z++;

        RenderSystem.setShaderColor(1, 1, 1, 1);
        if (spell instanceof ICustomUseRequirement) {

            ICustomUseRequirement customUseRequirement = (ICustomUseRequirement) spell;


            if (!customUseRequirement.canMeetRequirement(player, spellStack)) {

                RenderUtilities.renderTextureFromSprite(poseStack, AbilityBar.ABILITY_BAR_TEXTURES, 16, 16, xpos, ypos, z, 92, 9, 16, 16);

            }


        }

        z++;

        if (spell.getCost(player, spellStack) >= 1) {
            String spellCost = Integer.toString((int) spell.getCost(player, spellStack));
            poseStack.pushPose();

            float stringscale = 0.5f;
            int stringxpos = xpos + 1;
            stringxpos *= 1 / stringscale;
            int stringypos = ypos + 1;
            stringypos *= 1 / stringscale;

            poseStack.scale(stringscale, stringscale, 1);

            Gui.drawString(poseStack, gui.getFont(), spellCost, stringxpos, stringypos, z);

            poseStack.popPose();

        }

        z++;

        if (spell.getMaxCharges(player, spellStack) > 1 && spellStack.getCharges() != 0) {


            poseStack.pushPose();
            float stringscale = 0.5f;

            String spellCharges = Integer.toString(spellStack.getCharges());

            int stringxpos = xpos;
            stringxpos *= 1 / stringscale;
            int stringypos = ypos + 12;
            stringypos *= 1 / stringscale;
            poseStack.scale(stringscale, stringscale, 1);

            Gui.drawString(poseStack, gui.getFont(), spellCharges, stringxpos, stringypos, z);
            poseStack.popPose();


        }


        z++;

        if (spellStack.isCoolingDown()) {


            float cdpercent = (float) spellStack.getCooldown() / (float) spellStack.getSpell().getDefaultCooldown();


            float stringscale = 1f;

            float displaycd = (float) spellStack.getCooldown() / 20;


            displaycd = Mth.floor(displaycd);

            String displaytext = String.valueOf((int) displaycd);

            int stringxpos = xpos + 16 / 2;
            stringxpos *= 1 / stringscale;
            int stringypos = ypos + 16 / 4;
            stringypos *= 1 / stringscale;
            poseStack.scale(stringscale, stringscale, 1);
            if (cdpercent > 0) {

                RenderUtilities.renderTextureFromSprite(poseStack, AbilityBar.ABILITY_BAR_TEXTURES, 16f, 16f * cdpercent, xpos, ypos + 16 * (1.0f - cdpercent), z, 76, 9, 16, 16 * cdpercent);

            }


            if (spellStack.getCooldown() <= 2) {


                z++;
                RenderSystem.setShaderColor(1, 1, 1, 1.0f);
                RenderUtilities.renderTextureFromSprite(poseStack, AbilityBar.ABILITY_BAR_TEXTURES, 16f, 16f, xpos, ypos, z, 108, 9, 16, 16);


            }
            z++;
            Gui.drawCenteredString(poseStack, gui.getFont(), displaytext, stringxpos, stringypos, z);


        } else {


            if (spellStack.isRecharging()) {
                poseStack.pushPose();
                float cdpercent = (float) spellStack.getChargeTime() / (float) spellStack.getSpell().getDefaultChargetime();


                float colorcoefficient = spellStack.getCharges() == 0 ? 0.80f : 0.01f;
                RenderSystem.setShaderColor(1, 1, 1, colorcoefficient);
                z++;
                RenderUtilities.renderTextureFromSprite(poseStack, AbilityBar.ABILITY_BAR_TEXTURES, 16f, 1f, xpos, ypos + 16 * (1.0f - cdpercent), z, 76, 9, 16, 1);

                if (spellStack.getCharges() == 0) {

                    float stringscale = 1f;

                    float displaycd = (float) spellStack.getChargeTime() / 20;
                    displaycd = Math.round(displaycd);

                    String displaytext = String.valueOf((int) displaycd);

                    int stringxpos = xpos + 16 / 2;
                    stringxpos *= 1 / stringscale;
                    int stringypos = ypos + 16 / 4;
                    stringypos *= 1 / stringscale;
                    poseStack.scale(stringscale, stringscale, 1);
                    Gui.drawCenteredString(poseStack, gui.getFont(), displaytext, stringxpos, stringypos, z);

                    if (spellStack.getChargeTime() <= 2) {


                        z++;
                        RenderSystem.setShaderColor(1, (float) 215 / 255, 0, 1.0f);
                        RenderUtilities.renderTextureFromSprite(poseStack, AbilityBar.ABILITY_BAR_TEXTURES, 16f, 16f, xpos, ypos, z, 76, 9, 16, 16);


                    }
                }


                poseStack.popPose();
            }
        }


        poseStack.popPose();


    }


    public void renderQuadList(PoseStack poseStack, VertexConsumer vertexConsumer, List<BakedQuad> p_115165_, SpellStack spellStack, int x, int y) {
        PoseStack.Pose posestack$pose = poseStack.last();

        for (BakedQuad bakedquad : p_115165_) {
            int i = -1;


            float f = (float) (i >> 16 & 255) / 255.0F;
            float f1 = (float) (i >> 8 & 255) / 255.0F;
            float f2 = (float) (i & 255) / 255.0F;
            vertexConsumer.putBulkData(posestack$pose, bakedquad, f, f1, f2, x, y, true);
        }

    }


}
