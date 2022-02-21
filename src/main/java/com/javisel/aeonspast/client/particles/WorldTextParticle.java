package com.javisel.aeonspast.client.particles;

import com.javisel.aeonspast.common.particles.WorldTextOptions;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WorldTextParticle extends Particle {

    protected float quadSize = 0.1F * (this.random.nextFloat() * 0.5F + 0.5F) * 2.0F;

    Component component;

    public WorldTextParticle(Component component, ClientLevel level, double xo, double yo, double zo) {
        super(level, xo, yo, zo);
        this.component = component;
        this.hasPhysics = false;
        this.gravity = 0;
    }

    public WorldTextParticle(Component component, ClientLevel level, double xo, double yo, double zo, double xd, double yd, double zd) {
        super(level, xo, yo, zo, xd, yd, zd);
        this.component = component;
        this.hasPhysics = false;
        this.gravity = 0;


    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }


    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float ticks) {

        PoseStack stack = new PoseStack();
        stack.pushPose();

        Vec3 vec3 = camera.getPosition();
        float x = (float) (Mth.lerp((double) ticks, this.xo, this.x) - vec3.x());
        float y = (float) (Mth.lerp((double) ticks, this.yo, this.y) - vec3.y());
        float z = (float) (Mth.lerp((double) ticks, this.zo, this.z) - vec3.z());


        Font font = Minecraft.getInstance().font;


        float scale = 0.025f;



        RenderSystem.disableBlend();
        RenderSystem.disableDepthTest();
        RenderSystem.disableColorLogicOp();
        RenderSystem.disablePolygonOffset();

        stack.translate(x, y, z);

        stack.mulPose(camera.rotation());


        stack.scale(-scale, -scale, scale);

        font.draw(stack, component, (float) 0, (float) 0, 0);
        stack.popPose();

    }


    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<WorldTextOptions> {

        public Provider() {

        }

        @Override
        public Particle createParticle(WorldTextOptions worldTextOptions, ClientLevel level, double xo, double yo, double zo, double xd, double yd, double zd) {


            return new WorldTextParticle(worldTextOptions.getComponent(), level, xo, yo, zo, xd, yd, zd);
        }
    }


}
