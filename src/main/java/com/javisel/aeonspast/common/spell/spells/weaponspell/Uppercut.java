package com.javisel.aeonspast.common.spell.spells.weaponspell;

import com.javisel.aeonspast.common.registration.EffectRegistration;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class Uppercut extends WeaponSpell {
    public Uppercut() {
        super(300, 25, SpellRank.WEAPON_SPELL);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {


        if (entity.level.isClientSide) {






        } else {



        }


            attackReset(entity);
            entity.addEffect(new MobEffectInstance(EffectRegistration.UPPERCUT_SPELL_BUFF.get(),100,0));

    }
}
