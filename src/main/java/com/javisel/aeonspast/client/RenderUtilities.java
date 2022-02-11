package com.javisel.aeonspast.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderUtilities {

    private static final float SPRITE_SIZE = 256;


    public static void renderTextureFromSprite(PoseStack poseStack, ResourceLocation texture, float width, float height, float x, float y, float z, float u, float v, float textureWidth, float textureHeight) {


        Tesselator tesselator = Tesselator.getInstance();

        poseStack.pushPose();
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.enableBlend();


        BufferBuilder vertexbuilder = Tesselator.getInstance().getBuilder();


        float widthRatio = 1.0F / SPRITE_SIZE;
        float heightRatio = 1.0F / SPRITE_SIZE;


        Matrix4f matrix4f = poseStack.last().pose();
        vertexbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);


        vertexbuilder.vertex(matrix4f, x, y + height, z).uv(u * widthRatio, (v + textureHeight) * heightRatio).endVertex();
        vertexbuilder.vertex(matrix4f, x + width, y + height, z).uv((u + textureWidth) * widthRatio, (v + textureHeight) * heightRatio).endVertex();
        vertexbuilder.vertex(matrix4f, x + width, y, z).uv((u + textureWidth) * widthRatio, v * heightRatio).endVertex();
        vertexbuilder.vertex(matrix4f, x, y, z).uv(u * widthRatio, v * widthRatio).endVertex();


        tesselator.end();

        RenderSystem.disableBlend();

        poseStack.popPose();


    }


    public static void renderFromAtlas(PoseStack poseStack, TextureAtlasSprite sprite, float width, float height, float x, float y, float z, float u, float v, float textureWidth, float textureHeight) {


        RenderSystem.setShaderTexture(0, sprite.atlas().location());


        Gui.blit(poseStack, (int) x, (int) y, (int) z, (int) width, (int) height, sprite);


    }



}
