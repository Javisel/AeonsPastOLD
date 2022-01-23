package com.javisel.aeonspast.common.spell;

import net.minecraft.world.entity.LivingEntity;

public abstract class ToggleSpell extends Spell {


    public ToggleSpell(int defaultChargeTime, float defaultCost, SpellRank spellRank) {
        super(defaultChargeTime, defaultCost, spellRank);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {

    }

    public void offTick(LivingEntity entity, SpellStack stack) {
    }

    public void onTick(LivingEntity entity, SpellStack stack) {
    }

    public SpellState toggleState(LivingEntity entity, SpellStack stack) {

        if (stack.spellState == SpellState.OFF) {

            stack.spellState = SpellState.ON;
        } else if (stack.spellState == SpellState.ON) {

            stack.spellState = SpellState.OFF;


        } else {

            stack.spellState = SpellState.OFF;


        }


        return stack.spellState;
    }


}
