package com.javisel.aeonspast.client.entity;

import com.javisel.aeonspast.common.entities.spell.AxeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

public class AxeWeaponRenderer<T extends AxeEntity> extends ThrownItemRenderer<AxeEntity> {
    private static final float MIN_CAMERA_DISTANCE_SQUARED = 12.25F;
    private final ItemRenderer itemRenderer;
    private final float scale;
    private final boolean fullBright;

    public AxeWeaponRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer= Minecraft.getInstance().getItemRenderer();
        scale=1.25F;
        fullBright=false;

    }

    @Override
    public void render(AxeEntity axeEntity, float p_116086_, float p_116087_, PoseStack poseStack, MultiBufferSource bufferSource, int p_116090_) {
        if (axeEntity.tickCount >= 2 || !(this.entityRenderDispatcher.camera.getEntity().distanceToSqr(axeEntity) < 12.25D)) {
            poseStack.pushPose();
            poseStack.scale(this.scale, this.scale, this.scale);
            poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
            itemRenderer.renderStatic(axeEntity.getItem(), ItemTransforms.TransformType.GROUND, p_116090_, OverlayTexture.NO_OVERLAY, poseStack, bufferSource, axeEntity.getId());
            poseStack.popPose();
            super.render(axeEntity, p_116086_, p_116087_, poseStack, bufferSource, p_116090_);
        }


    }
}
