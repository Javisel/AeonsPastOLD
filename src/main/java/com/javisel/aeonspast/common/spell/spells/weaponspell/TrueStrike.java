package com.javisel.aeonspast.common.spell.spells.weaponspell;

import com.javisel.aeonspast.common.registration.EffectRegistration;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class TrueStrike  extends WeaponSpell {
    public TrueStrike( ) {
        super(300, 50, SpellRank.WEAPON_SPELL);
    }


    @Override
    public void cast(LivingEntity entity, SpellStack stack) {

         entity.addEffect(new MobEffectInstance(EffectRegistration.TRUE_STRIKE_SPELL_BUFF.get(),100,0));

    }
}
