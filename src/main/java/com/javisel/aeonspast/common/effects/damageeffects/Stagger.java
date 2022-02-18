package com.javisel.aeonspast.common.effects.damageeffects;

import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.effects.DamageStatusDebuff;
import com.javisel.aeonspast.common.effects.StatusEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class Stagger  extends StatusEffect implements DamageStatusDebuff {



    public Stagger() {
        super(MobEffectCategory.HARMFUL,0);
    }


    @Override
    public DamageTypeEnum getDamageType() {
        return DamageTypeEnum.IMPACT;
    }

    @Override
    public void applyFromDamage(LivingEntity source, LivingEntity target, DamageInstance damageInstance) {

    }
}
