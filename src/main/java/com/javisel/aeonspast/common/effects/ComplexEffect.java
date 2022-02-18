package com.javisel.aeonspast.common.effects;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.capabiltiies.entity.EntityData;
import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.UUID;

public class ComplexEffect extends MobEffect{





    //TODO Color codes for all Effects
    public ComplexEffect(MobEffectCategory effectCate, int effectColour) {
        super(effectCate, effectColour);

    }

    @Override
    public boolean isInstantenous() {
        return false;
    }

    @Override
    public   boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return  true;
    }



    @Override
    public final void applyEffectTick(LivingEntity livingEntity, int durationIn) {
        super.applyEffectTick(livingEntity, durationIn);


         for (ComplexEffectInstance instance : getAllInstancesOnEntity(livingEntity)) {



            instance.duration--;


            if (instance.duration==0) {

                instance.remove=true;
                removeComplexInstance(instance.source,livingEntity);
            }

           if (instance.duration % instance.tickRate == 0) {

                 applyTickableEffect(instance, livingEntity);
            }


        }






    }


    public void applyTickableEffect(ComplexEffectInstance instance, LivingEntity entity) {

        System.out.println("Tick Effect!");
    }



    public void addnewComplexInstance(ComplexEffectInstance instance, LivingEntity user) {

                    IEntityData entityData = Utilities.getEntityData(user);


                    ArrayList<ComplexEffectInstance> instances;
                    if (entityData.getMobEffectArrayListHashMap().containsKey(this)) {


                        instances=entityData.getMobEffectArrayListHashMap().get(this);



                    } else {
                        instances = new ArrayList<>();
                     }

                       instances.add(instance);

        entityData.getMobEffectArrayListHashMap().put(this,instances);


        if (user.hasEffect(this)) {

            if (user.getEffect(this).getDuration() < instance.duration) {
                user.forceAddEffect(new MobEffectInstance(this,0, (int) instance.duration,false, this instanceof StatusEffect, this instanceof  StatusEffect),null);

            }


        } else {

            user.forceAddEffect(new MobEffectInstance(this,0, (int) instance.duration,false, this instanceof StatusEffect, this instanceof  StatusEffect),null);

        }





    }



    public void removeComplexInstance(UUID id, LivingEntity user) {

        IEntityData entityData = Utilities.getEntityData(user);


        ArrayList<ComplexEffectInstance> instances;
        if (entityData.getMobEffectArrayListHashMap().containsKey(this)) {


            instances=entityData.getMobEffectArrayListHashMap().get(this);



        } else {
          return;
        }




        for (ComplexEffectInstance comp : instances) {

            if (comp.instanceID.equals(id)) {
                comp.remove=true;
                instances.remove(comp);
            }


        }
        entityData.getMobEffectArrayListHashMap().put(this,instances);

        if (instances.isEmpty()) {

            user.removeEffect(this);
        }








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



    public void recalculateInstances(LivingEntity user) {


    }



public void consumeEffect(LivingEntity holder) {

        IEntityData entityData = Utilities.getEntityData(holder);

        entityData.getMobEffectArrayListHashMap().remove(this);

        holder.removeEffect(this);




}





}
