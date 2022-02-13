package com.javisel.aeonspast.common.spell.spells.basicspell;

import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.entity.LivingEntity;

public class SwiftStrikes extends Spell {
    public SwiftStrikes(int defaultChargeTime, float defaultCost, SpellRank spellRank) {
        super(defaultChargeTime, defaultCost, spellRank);
    }



    @Override
    public void cast(LivingEntity entity, SpellStack stack) {

    }
}
