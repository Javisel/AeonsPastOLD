package com.javisel.aeonspast.common.effects;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

public class ComplexEffect extends MobEffect{





    protected ComplexEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }




    public void onEffectAdded(LivingEntity user) {




    }




    public boolean onpreHitEffect(LivingEntity attacker, LivingEntity blocker, APDamageSource damageSource) {


        return  true;
    }

    public void onHitEffect(LivingEntity attacker, LivingEntity blocker, APDamageSource damageSource) {


    }

    public void onpostHitEffect(LivingEntity attacker, LivingEntity blocker, APDamageSource damageSource) {


    }



    public boolean onOwnerpreHitEffect(LivingEntity attacker, LivingEntity blocker, APDamageSource damageSource) {


        return  true;
    }

    public void onOwnerHitEffect(LivingEntity attacker, LivingEntity blocker, APDamageSource damageSource) {


    }

    public void onOwnerpostHitEffect(LivingEntity attacker, LivingEntity blocker, APDamageSource damageSource) {


    }



public void consumeEffect(LivingEntity holder) {

        IEntityData entityData = Utilities.getEntityData(holder);

        entityData.getMobEffectArrayListHashMap().remove(this);

        holder.removeEffect(this);
}





}
