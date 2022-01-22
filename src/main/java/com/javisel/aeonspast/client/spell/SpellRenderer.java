package com.javisel.aeonspast.client.spell;

import com.javisel.aeonspast.client.RenderUtilities;
import com.javisel.aeonspast.client.main.ClientProxy;
import com.javisel.aeonspast.client.overlays.APAbilityBar;
import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.IPlayerData;
import com.javisel.aeonspast.common.spell.ICustomUseRequirement;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.APUtilities;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

import java.util.List;
import java.util.Random;

import static com.javisel.aeonspast.common.registration.SpellRegistration.SPELLS;

public class SpellRenderer {


    private final SpellModelShaper spellModelShaper;
    private final TextureManager textureManager;

    private final RenderType SPELL_RENDER_TYPE = RenderType.translucent();

    public SpellRenderer(TextureManager textureManager, ModelManager modelManager) {
        this.spellModelShaper = new SpellModelShaper(modelManager);
        this.textureManager = textureManager;


        for (RegistryObject<Spell> spell : SPELLS.getEntries()) {

            this.spellModelShaper.register(spell.get(), new ModelResourceLocation(spell.getId(), "hud"));


        }


    }

    public void render(ForgeIngameGui gui, SpellStack stack, PoseStack poseStack, int x, int y, int z, boolean renderDefault) {




        TextureAtlasSprite sprite = ClientProxy.spellAtlasHolder.get(stack.getSpell());




        RenderUtilities.renderFromAtlas(poseStack,sprite,16,16,x,y,z,sprite.getU1(),sprite.getV1(),sprite.getWidth(),sprite.getHeight());

    }


    public void renderSpellAndSpellInfo(ForgeIngameGui gui, SpellStack spellStack, PoseStack poseStack, int x, int y) {


        renderSpellInfo(gui, poseStack, spellStack, x, y);

    }


    public void renderSpellInfo(ForgeIngameGui gui, PoseStack poseStack, SpellStack spellStack, int xpos, int ypos) {

        Minecraft minecraft = Minecraft.getInstance();

        Player player = minecraft.player;
        IPlayerData playerData = APUtilities.getPlayerData(player);
        IEntityData entityData = APUtilities.getEntityData(player);


        Spell spell = spellStack.getSpell();

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();


        poseStack.pushPose();
        ;

        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();


        render(gui, spellStack, poseStack, xpos, ypos, 0, false);
        if (spellStack.getCharges() < 1) {


            RenderSystem.disableColorLogicOp();

            RenderSystem.setShaderColor(128 / 255, 128 / 255, 128 / 255, 1f);
        }


        poseStack.pushPose();
        ;

        poseStack.popPose();
        ;

        if (entityData.getMana() < spell.getCost(player, spellStack)) {


            RenderUtilities.renderTextureFromSprite(poseStack, APAbilityBar.ABILITY_BAR_TEXTURES, 16, 16, xpos, ypos, 127, 75, 12, 16, 16);

        }


        if (spell instanceof ICustomUseRequirement) {

            ICustomUseRequirement customUseRequirement = (ICustomUseRequirement) spell;


            if (!customUseRequirement.canMeetRequirement(player, spellStack)) {

                RenderUtilities.renderTextureFromSprite(poseStack, APAbilityBar.ABILITY_BAR_TEXTURES, 16, 16, xpos, ypos, 128, 114, 12, 16, 16);

            }


        }

        if (spellStack.getCurrentMaxCooldownOrCharge() != 0) {


            float cdpercent = spellStack.getCooldownOrChargeRemaining() / spellStack.getCurrentMaxCooldownOrCharge();

            if (cdpercent != 0) {

                RenderUtilities.renderTextureFromSprite(poseStack, APAbilityBar.ABILITY_BAR_TEXTURES, 16f, 16f * cdpercent, xpos, ypos - 16, 129, 94, 12, 16, 16 - (16 * cdpercent));

            }

        }


        if (spell.getCost(player, spellStack) >= 1) {
            String spellCost = Integer.toString((int) spell.getCost(player, spellStack));

            float stringscale = 0.7f;
            int stringxpos = xpos + 16 - (gui.getFont().width(spellCost));
            stringxpos *= 1 / stringscale;
            int stringypos = ypos;
            stringypos *= 1 / stringscale;


            Gui.drawString(poseStack, gui.getFont(), spellCost, stringxpos, stringypos, 130);


        }


        if (spell.getMaxCharges(player, spellStack) > 1) {


            float stringscale = 0.5f;

            String spellCharges = Integer.toString(spellStack.getCharges());

            int stringxpos = xpos + 16 - (gui.getFont().width(spellCharges));
            stringxpos *= 1 / stringscale;
            int stringypos = ypos + 16;
            stringypos *= 1 / stringscale;

            Gui.drawString(poseStack, gui.getFont(), spellCharges, stringxpos, stringypos, 131);


        }


        poseStack.popPose();


    }


    public void renderModelLists(BakedModel bakedModel, SpellStack spellStack, int x, int y, PoseStack poseStack, VertexConsumer vertexConsumer) {
        Random random = new Random();
        long i = 42L;

        for (Direction direction : Direction.values()) {
            random.setSeed(42L);
            this.renderQuadList(poseStack, vertexConsumer, bakedModel.getQuads((BlockState) null, direction, random), spellStack, x, y);
        }

        random.setSeed(42L);
        this.renderQuadList(poseStack, vertexConsumer, bakedModel.getQuads((BlockState) null, (Direction) null, random), spellStack, x, y);
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
