package com.javisel.aeonspast.client.spell.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class SwiftStrikesRenderer extends ParticleSpell {








    @Override
    public void render(UUID entityId, double castX, double castY, double castZ, MultiBufferSource bufferSource, Camera camera, PoseStack stack) {
        Level level = Minecraft.getInstance().level;


        Entity entity = super.getEntityById(entityId);

        if (entity == null) {
            return;
        }

        double x =  entity.getX();
        double y =  castY + entity.getBbHeight() /2  ;
        double z = entity.getZ();

        for (int i = 0; i < 360; i++) {



            double angle = Math.toRadians(i);
            double radius = 1.0d;
            double vx = radius * Math.cos(angle);
            double vz = radius * Math.sin(angle);





        }
    }
}
