package com.javisel.aeonspast.common.spell.weaponspell;

import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.entity.LivingEntity;

public class TrueStrike  extends Spell {
    public TrueStrike( ) {
        super(60, 21, SpellRank.WEAPON_SPELL);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {

    }
}
