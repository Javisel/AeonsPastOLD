package com.javisel.aeonspast.client.particles;

import com.javisel.aeonspast.common.particles.WorldTextOptions;
import com.javisel.aeonspast.common.particles.WorldTextType;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class WorldTextParticle extends SingleQuadParticle {


    Component component;
    public WorldTextParticle(Component component, ClientLevel level, double xo, double yo, double zo) {
        super(level, xo, yo, zo);
        this.component=component;
        this.hasPhysics=false;
        this.gravity=0;
     }

    public WorldTextParticle(Component component, ClientLevel level, double xo, double yo, double zo, double xd, double yd, double zd) {
        super(level, xo, yo, zo, xd, yd, zd);
        this.component=component;
        this.hasPhysics=false;
        this.gravity=0;


    }
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }


    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float ticks) {

        super.render(vertexConsumer, camera, ticks);

        Font font = Minecraft.getInstance().font;


        double x =xo;
        double y = yo;
        double z =zo;

        float scale = 0.03f;




        Vec3 camPos = camera.getPosition();
        double camX = camPos.x;
        double camY = camPos.y;
        double camZ = camPos.z;

        PoseStack stack = RenderSystem.getModelViewStack();
        RenderSystem.disableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.disableColorLogicOp();
        RenderSystem.disablePolygonOffset();
           stack.pushPose();

      stack.translate(x - (camX), (y) - camY, z - (camZ));

        stack.mulPose(Vector3f.YP.rotationDegrees(-camera.getYRot()));
       stack.mulPose(Vector3f.XP.rotationDegrees(camera.getXRot()));
        stack.scale(-scale, -scale, scale);





         font.draw(stack,component,(float)0,(float)0,0);
           stack.popPose();

    }

    @Override
    protected float getU0() {
        return 0;
    }

    @Override
    protected float getU1() {
        return 0;
    }

    @Override
    protected float getV0() {
        return 0;
    }

    @Override
    protected float getV1() {
        return 0;
    }


    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<WorldTextOptions> {

        public Provider () {

         }

        @Override
        public Particle createParticle(WorldTextOptions worldTextOptions, ClientLevel level, double xo, double yo, double zo, double xd, double yd, double zd) {


            System.out.println("Particle Created at " + xo + "," + yo + "," + zo);
            return  new WorldTextParticle(worldTextOptions.getComponent(),level,xo,yo,zo,xd,yd,zd);
        }
    }





}
