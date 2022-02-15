package com.javisel.aeonspast.common.effects;

import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import com.javisel.aeonspast.common.combat.damagesource.APEntityDamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class TrueStrikeSpellBuff extends ComplexEffect {
    public TrueStrikeSpellBuff() {
        super(MobEffectCategory.BENEFICIAL, 123);
    }





    @Override
    public void onHitEffect(LivingEntity attacker, LivingEntity blocker, APDamageSource damageSource) {


        DamageInstance instance = damageSource.instance;

        if (instance.doesProcWeaponHitEffects && instance.damageDevice instanceof ItemStack) {


             float damage = (float) (instance.getPreMitigationsAmount()*  0.25f);

             DamageInstance damageInstance = new DamageInstance(DamageTypeEnum.TRUE, damage);
            APEntityDamageSource apEntityDamageSource = new APEntityDamageSource(damageSource.getMsgId(), damageInstance,attacker);
            blocker.hurt(apEntityDamageSource,  damage );
            consumeEffect(attacker);

        }
    }

}
