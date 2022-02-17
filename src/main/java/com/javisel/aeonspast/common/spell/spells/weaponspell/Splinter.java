package com.javisel.aeonspast.common.spell.spells.weaponspell;

import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.entity.LivingEntity;

public class Splinter extends WeaponSpell {
    public Splinter(int defaultMaxCharges, int defaultChargeTime, int defaultCooldown, float defaultCost, SpellRank spellRank) {
        super(defaultMaxCharges, defaultChargeTime, defaultCooldown, defaultCost, spellRank);
    }

    public Splinter(int defaultChargeTime, float defaultCost, SpellRank spellRank) {
        super(defaultChargeTime, defaultCost, spellRank);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {

    }
}
