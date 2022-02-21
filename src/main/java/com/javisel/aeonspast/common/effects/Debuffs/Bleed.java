package com.javisel.aeonspast.common.effects.Debuffs;

import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import com.javisel.aeonspast.common.combat.damagesource.APEntityDamageSource;
import com.javisel.aeonspast.common.effects.ComplexEffect;
import com.javisel.aeonspast.common.effects.ComplexEffectInstance;
import com.javisel.aeonspast.common.effects.IDamageStatus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class Bleed extends ComplexEffect implements IDamageStatus {


    public Bleed( ) {
        super(MobEffectCategory.HARMFUL, 0xFF0000);
    }

    @Override
    public void applyTickableEffect(ComplexEffectInstance instance, LivingEntity entity) {
        super.applyTickableEffect(instance, entity);

        Level level = entity.getLevel();

        if (!level.isClientSide) {

            ServerLevel serverLevel = (ServerLevel) level;
            DamageInstance dmg = DamageInstance.getBleedDamage(instance.power);

            DamageSource source  = instance.source !=null ? new APEntityDamageSource("bleed",dmg,serverLevel.getEntity(instance.source)) : new APDamageSource("bleed",dmg);

            entity.hurt(source, (float) instance.power);

        }
     }

    @Override
    public DamageTypeEnum getDamageType() {
        return DamageTypeEnum.BLEED;
    }

    @Override
    public ComplexEffectInstance getDefaultDamageInstance(LivingEntity attacker, LivingEntity victim, DamageInstance procInstance) {


        double power = (float) (procInstance.postMitigationsAmount  * 0.15);




        ComplexEffectInstance instance = new ComplexEffectInstance(UUID.randomUUID(),attacker.getUUID(),power,(20 * 5) +1);






        return instance;
    }
}
