package com.javisel.aeonspast.client.world;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.client.RenderUtilities;
import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.utilities.Utilities;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.shadowed.eliotlash.mclib.utils.MathHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
@OnlyIn(Dist.CLIENT)

public class InWorldRenderer {

    private final ResourceLocation IN_WORLD_BAR_TEXTURES = new ResourceLocation(AeonsPast.MODID, "textures/gui/in_world_health_bar.png");



    public static final ArrayList<LivingEntity> entities = new ArrayList<>();



    public void addEntityToRenderQue(LivingEntity entity) {


        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (entity==player) {
            return;
        }
        if (entity.isInvisible() || entity.isInvisibleTo(player) ) {

            return;

        }

        if (            !player.hasLineOfSight(entity) || entity.distanceTo(player) > 32) {

            return;
        }



        entities.add(entity);
    }


    public void renderBar(PoseStack stack, Camera camera) {


        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;
  ;


        for (LivingEntity entity : entities) {

            float tickDelta = minecraft.getDeltaFrameTime();

            float scale = 0.025f;
            float height = entity.getBbHeight() + entity.getEyeHeight() + 1F - (entity.isCrouching() ? 0.25F : 0.0F);

            double x = Mth.lerp((double) tickDelta, entity.xOld, entity.getX());
            double y = Mth.lerp((double) tickDelta, entity.yOld, entity.getY());
            double z = Mth.lerp((double) tickDelta, entity.zOld, entity.getZ());


            Vec3 camPos = camera.getPosition();
            double camX = camPos.x;
            double camY = camPos.y;
            double camZ = camPos.z;

            RenderSystem.disableBlend();
            stack.pushPose();
            stack.translate(x - (camX), (y+height) - camY, z - (camZ));
            stack.mulPose(Vector3f.YP.rotationDegrees(-camera.getYRot()));
            stack.mulPose(Vector3f.XP.rotationDegrees(camera.getXRot()));
             stack.scale(-scale, -scale, scale);

            float health = entity.getHealth()/entity.getMaxHealth();
            RenderUtilities.renderTextureFromSprite(stack,IN_WORLD_BAR_TEXTURES,   100,4,0,0,-1,0,0,180,4);


         RenderUtilities.renderTextureFromSprite(stack,IN_WORLD_BAR_TEXTURES,( 100 )*health,2,.5f,1,-1,1,4,176*health,2);

             String healthString = ((int)(entity.getHealth())) + "/" +  ((int)entity.getMaxHealth());




            Font font = minecraft.font;




            stack.pushPose();

            float f1 = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);

            RenderSystem.setShaderColor(1,1,1,1);
            stack.scale(0.5f,0.5f,1);


        font.draw(stack,healthString,100- font.width(healthString)/2,0,1);

     /*
                float offset =  entity.hasCustomName() ? -16 : - 8;

            IEntityData entityData = Utilities.getEntityData(entity);
            MutableComponent mutableComponent = new TextComponent("Level " + entityData.getLevel());



            font.draw(stack,mutableComponent.copy().withStyle(ChatFormatting.WHITE),100-font.width(mutableComponent)/2,offset,1);


             */

            if (entity.hasCustomName()) {


                font.draw(stack,entity.getDisplayName().copy().withStyle(ChatFormatting.WHITE),100-font.width(entity.getDisplayName())/2,-8,1);


            }

            stack.popPose();
            stack.popPose();



        }

        entities.clear();

    }


    public static void renderHealthbar(PoseStack poseStack, ResourceLocation texture, float width, float height, float x, float y, float z, float u, float v, float textureWidth, float textureHeight) {


        Tesselator tesselator = Tesselator.getInstance();

        poseStack.pushPose();
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.enableBlend();


        BufferBuilder vertexbuilder = Tesselator.getInstance().getBuilder();


        float widthRatio = 1.0F / 256;
        float heightRatio = 1.0F / 256;


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

}
