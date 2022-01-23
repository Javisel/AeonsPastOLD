package com.javisel.aeonspast.common.spell.spells;

import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.entity.LivingEntity;

public class WarriorsShout extends Spell {


    public WarriorsShout() {
        super(1200, 1200, SpellRank.SKILL_ULTIMATE);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {

    }
}
