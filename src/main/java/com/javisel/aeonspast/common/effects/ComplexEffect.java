package com.javisel.aeonspast.common.effects;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.UUID;

public class ComplexEffect extends MobEffect{





    //TODO Color codes for all Effects
    public ComplexEffect(MobEffectCategory p_19451_, int p_19452_) {
        super(p_19451_, p_19452_);
    }


    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return  true;
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int durationIn) {
        super.applyEffectTick(livingEntity, durationIn);



        for (ComplexEffectInstance instance : getAllInstancesOnEntity(livingEntity)) {


            instance.duration--;


            if (instance.duration==0) {

                instance.remove=true;
                removeComplexInstance(instance.source,livingEntity);
            }

            if (instance.duration > durationIn) {



            }


        }






    }

    public void addnewComplexInstance(ComplexEffectInstance instance, LivingEntity user) {


        if (user.hasEffect(this)) {


            MobEffectInstance mobInstance = user.getEffect(this);





        }



    }



    public void removeComplexInstance(UUID id, LivingEntity user) {



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


    public ArrayList<ComplexEffectInstance> getAllInstancesOnEntity(LivingEntity entity) {



        ArrayList<ComplexEffectInstance> effectInstances = new ArrayList<>();



        IEntityData entityData = Utilities.getEntityData(entity);


        if (entityData.getMobEffectArrayListHashMap().containsKey(this)) {

            effectInstances = entityData.getMobEffectArrayListHashMap().get(this);

        }


        return  effectInstances;



    }









public void consumeEffect(LivingEntity holder) {

        IEntityData entityData = Utilities.getEntityData(holder);

        entityData.getMobEffectArrayListHashMap().remove(this);

        holder.removeEffect(this);
}





}
