package com.javisel.aeonspast.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;


@OnlyIn(Dist.CLIENT)
public class NewPlayerModel extends AnimatedGeoModel {
    @Override
    public void setLivingAnimations(Object entity, Integer uniqueID, AnimationEvent customPredicate) {

    }

    @Override
    public ResourceLocation getModelLocation(Object object) {
        return null;
    }

    @Override
    public ResourceLocation getTextureLocation(Object object) {
        return null;
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Object animatable) {
        return null;
    }
}
