package com.javisel.aeonspast.common.spell;

public abstract class ClassSpell extends Spell {
    public ClassSpell(int defaultMaxCharges, int defaultChargeTime, int defaultCooldown, float defaultCost) {
        super(defaultMaxCharges, defaultChargeTime, defaultCooldown, defaultCost, SpellRank.CLASS_SPELL);
    }

    public ClassSpell(int defaultCooldown, float defaultCost) {
        super(defaultCooldown, defaultCost, SpellRank.CLASS_SPELL);
    }





}
