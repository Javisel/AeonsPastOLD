package com.javisel.aeonspast.client.particles;

import com.javisel.aeonspast.common.particle.particletypes.WorldTextType;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
@OnlyIn(Dist.CLIENT)

public class WorldTextParticle extends Particle {


    MutableComponent text;


    protected WorldTextParticle(ClientLevel level, double x, double y, double z, MutableComponent text) {
        super(level, x, y, z);
        this.text = text;
    }

    public WorldTextParticle(ClientLevel level, double x, double y, double z, double xd, double yd, double zd, MutableComponent text) {
        super(level, x, y, z, xd, yd, zd);
        this.text = text;
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float ticksIn) {


        Minecraft minecraft = Minecraft.getInstance();

        PoseStack stack = new PoseStack();
        minecraft.gui.getFont().draw(stack, text, 1, 1, 1);


    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }


    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<WorldTextType> {

        public Factory() {
        }


        @Nullable
        @Override
        public Particle createParticle(WorldTextType worldTextType, ClientLevel level, double xo, double yo, double zo, double xd, double yd, double zd) {

            WorldTextParticle worldTextParticle = new WorldTextParticle(level, xo, yo, zo, xd, yd, zd, worldTextType.getComponent());
            return worldTextParticle;
        }
    }


}
