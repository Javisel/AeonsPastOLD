package com.javisel.aeonspast.client.spell.renderers;

import com.javisel.aeonspast.client.world.InWorldRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.UUID;

public  abstract class SpellRenderer {








     public abstract void render(UUID entityId,double castX, double castY, double castZ, MultiBufferSource bufferSource, Camera camera, PoseStack stack);







     public Entity getEntityById(UUID uuid) {


          Minecraft minecraft = Minecraft.getInstance();
          ArrayList<Entity> entities = InWorldRenderer.entities;

          for (Entity entity : entities) {



               if (entity.getUUID().equals(uuid)) {
                    return  entity;
               }





          }




          return null;
     }






}
