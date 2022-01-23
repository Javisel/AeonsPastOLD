package com.javisel.aeonspast.common.spell.spells;

import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.ToggleSpell;

public class ElementalBoon extends ToggleSpell {

    private final String FIRE = "FIRE", WATER = "WATER", EARTH = "EARTH", WIND = "WIND";

    public ElementalBoon(int defaultChargeTime, float defaultCost, SpellRank spellRank) {
        super(10, 0, SpellRank.SKILL_ULTIMATE);
    }


}
