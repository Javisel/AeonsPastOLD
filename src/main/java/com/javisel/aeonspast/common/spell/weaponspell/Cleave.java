package com.javisel.aeonspast.common.spell.weaponspell;

import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.entity.LivingEntity;

public class Cleave extends Spell {
    public Cleave() {
        super(60, 25, SpellRank.WEAPON_SPELL);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {

    }
}
