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
    public final boolean isDurationEffectTick(int p_19455_, int p_19456_) {
        return  true;
    }

    @Override
    public final void applyEffectTick(LivingEntity livingEntity, int durationIn) {
        super.applyEffectTick(livingEntity, durationIn);


        System.out.println("EFFECT TICKS!");
        for (ComplexEffectInstance instance : getAllInstancesOnEntity(livingEntity)) {


            System.out.println("There's an Instance!");

            instance.duration--;


            if (instance.duration==0) {
                System.out.println("Removed it!");

                instance.remove=true;
                removeComplexInstance(instance.source,livingEntity);
            }

           if (instance.duration % instance.tickRate == 0) {

               System.out.println("APPLY THE TICKS!");
                applyTickableEffect(instance, livingEntity);
            }


        }






    }


    public void applyTickableEffect(ComplexEffectInstance instance, LivingEntity entity) {





    }

    public void addnewComplexInstance(ComplexEffectInstance instance, LivingEntity user) {



        System.out.println("Adding new instance!");

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
                user.addEffect(new MobEffectInstance(this,0, (int) instance.duration));

            }


        }





    }



    public void removeComplexInstance(UUID id, LivingEntity user) {


        for (ComplexEffectInstance effectInstance : getAllInstancesOnEntity(user)) {

            if (effectInstance.instanceID.equals(id)) {

                effectInstance.remove=true;

                recalculateInstances(user);

                return;
            }



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
