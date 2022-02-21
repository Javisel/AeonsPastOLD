package com.javisel.aeonspast.client.world;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.client.RenderUtilities;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)

public class InWorldRenderer {

     private final ResourceLocation IN_WORLD_BAR_TEXTURES = new ResourceLocation(AeonsPast.MODID, "textures/gui/in_world_health_bar.png");


    public static ArrayList<Entity> entities = new ArrayList<>();
    public boolean canRender(Entity entity) {


        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;


        boolean result = true;
        if (!(entity instanceof LivingEntity)) {
            result=  false;
        }
        if (entity == player) {
            result=  false;        }
        if (entity.isInvisible() || entity.isInvisibleTo(player)) {

            result=  false;
        }

        if (!player.hasLineOfSight(entity) || entity.distanceTo(player) > 32) {

            result=  false;        }


        if (result) {
            entities.add(entity);

        } else {
            entities.remove(entity);
        }


      return  result;
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

        float half = width/ 2;

        vertexbuilder.vertex(matrix4f, x-half, y + height, z).uv(u * widthRatio, (v + textureHeight) * heightRatio).endVertex();
        vertexbuilder.vertex(matrix4f, x + half, y + height, z).uv((u + textureWidth) * widthRatio, (v + textureHeight) * heightRatio).endVertex();
        vertexbuilder.vertex(matrix4f, x + half, y, z).uv((u + textureWidth) * widthRatio, v * heightRatio).endVertex();
        vertexbuilder.vertex(matrix4f, x-half, y, z).uv(u * widthRatio, v * widthRatio).endVertex();


        tesselator.end();

        RenderSystem.disableBlend();

        poseStack.popPose();


    }


    public void renderBar(PoseStack stack, LivingEntity entity, Camera camera) {


        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;




            float tickDelta = minecraft.getDeltaFrameTime();

        float scale = 0.025f;
            scale*=  entity.getBoundingBox().getSize();
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
            stack.translate(x - (camX), (y + height) - camY, z - (camZ));

            stack.mulPose(Vector3f.YP.rotationDegrees(-camera.getYRot()));
            stack.mulPose(Vector3f.XP.rotationDegrees(camera.getXRot()));
            stack.scale(-scale, -scale, scale);
            stack.translate(-50,0,0);

            float health = entity.getHealth() / entity.getMaxHealth();

             RenderUtilities.renderTextureFromSprite(stack, IN_WORLD_BAR_TEXTURES, (100) , 4, 0, 0, -1, 0, 0, 180, 4);


             RenderUtilities.renderTextureFromSprite(stack, IN_WORLD_BAR_TEXTURES, (100) * health, 2, .5f, 1, 1, 1, 4, 176 * health, 2);

             String healthString = ((int) (entity.getHealth())) + "/" + ((int) entity.getMaxHealth());


            MutableComponent component = new TextComponent(healthString).withStyle(ChatFormatting.WHITE);
            Font font = minecraft.font;


            stack.pushPose();


            RenderSystem.setShaderColor(1, 1, 1, 1);

            stack.scale(0.5f, 0.5f, 1);


            font.draw(stack, component, 100 -  (float)font.width(healthString) / 2, 0, 1);



            if (entity.hasCustomName()) {


                font.draw(stack, entity.getDisplayName().copy().withStyle(ChatFormatting.WHITE), 100 - font.width(entity.getDisplayName()) / 2, -8, 1);


            }

            stack.popPose();
            stack.popPose();



    }

}
