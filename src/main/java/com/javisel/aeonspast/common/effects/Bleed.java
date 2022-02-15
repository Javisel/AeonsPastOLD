package com.javisel.aeonspast.common.effects;

import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import com.javisel.aeonspast.common.combat.damagesource.APEntityDamageSource;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.UUID;

public class Bleed extends DamageEffect{

    public Bleed( ) {
        super(0x660000, DamageTypeEnum.PUNCTURE, AttributeRegistration.BLEED_CHANCE);
    }


    @Override
    public void applyEffectTick(LivingEntity entity, int durationIn) {
        super.applyEffectTick(entity, durationIn);


        if (entity.level.isClientSide) {
            return;
        }
        IEntityData entityData = Utilities.getEntityData(entity);

        ArrayList<ComplexEffectInstance> instances = entityData.getMobEffectArrayListHashMap().get(this);

        if (instances == null || instances.isEmpty()) {


            consumeEffect(entity);


        } else {

            for (ComplexEffectInstance instance : instances) {

                instance.duration--;


                if (instance.duration > durationIn) {

                    entity.addEffect(new MobEffectInstance(this, (int) instance.duration));
                }
                if (instance.duration % 20 == 0) {


                    APDamageSource source;

                    Level level = entity.getLevel();
                    UUID id = instance.source;

                    DamageInstance bleedInstance = DamageInstance.getGenericProcDamage(DamageTypeEnum.BLEED,instance.power);


                    if (Utilities.getEntityByID((ServerLevel) level,id) !=null) {


                        Entity sourceentity = Utilities.getEntityByID((ServerLevel) level,id);

                        source = new APEntityDamageSource(sourceentity instanceof Player ? "player" : "mob",bleedInstance,Utilities.getEntityByID((ServerLevel) level,id));

                    }


                    else {

                        source = new APDamageSource("aeonspast:bleed",bleedInstance);

                    }

                    entity.hurt(source,instance.getPower());

                    if (instance.duration==0) {

                        instance.remove=true;
                    }


                }



            }


        }





    }



}
