package com.javisel.aeonspast.common.effects.damageeffects;

import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import com.javisel.aeonspast.common.combat.damagesource.APEntityDamageSource;
import com.javisel.aeonspast.common.effects.ComplexEffectInstance;
import com.javisel.aeonspast.common.effects.DamageStatusDebuff;
import com.javisel.aeonspast.common.effects.StatusEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class Bleed extends StatusEffect implements DamageStatusDebuff {

    public Bleed( ) {
        super(MobEffectCategory.HARMFUL,0);
    }


    @Override
    public void applyTickableEffect(ComplexEffectInstance instance, LivingEntity entity) {
        super.applyTickableEffect(instance, entity);


        Level level = entity.getLevel();

        if (!level.isClientSide) {


            ServerLevel serverLevel = (ServerLevel) level;


            DamageInstance bleed = DamageInstance.getBleedDamage(instance.power);

            APDamageSource damageSource;


            Entity source = serverLevel.getEntity(instance.source);
            if (source instanceof LivingEntity) {



                damageSource = new APEntityDamageSource("bleed.entity",bleed,source);



            } else {

                damageSource = new APDamageSource("bleed", bleed);



            }

            entity.hurt(damageSource, (float) bleed.preMitigationsAmount);




        }











    }

    @Override
    public DamageTypeEnum getDamageType() {
        return DamageTypeEnum.SLASH;
    }

    @Override
    public void applyFromDamage(LivingEntity source, LivingEntity target, DamageInstance damageInstance) {


        UUID sourceid = source !=null ? source.getUUID() : null;
        ComplexEffectInstance effectInstance = new ComplexEffectInstance(UUID.randomUUID(),sourceid, (float) (damageInstance.postMitigationsAmount * 0.10),100,100);



        addnewComplexInstance(effectInstance,target);

    }

}
