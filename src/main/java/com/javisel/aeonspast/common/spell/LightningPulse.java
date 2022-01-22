package com.javisel.aeonspast.common.spell;

import net.minecraft.world.entity.LivingEntity;

public class LightningPulse extends ToggleSpell {
    public LightningPulse(int defaultChargeTime, float defaultCost, SpellRank spellRank) {
        super(defaultChargeTime, defaultCost, spellRank);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {
        super.cast(entity, stack);




    }

    @Override
    public void offTick(LivingEntity entity, SpellStack stack) {
        super.offTick(entity, stack);
    }

    @Override
    public void onTick(LivingEntity entity, SpellStack stack) {
        super.onTick(entity, stack);

    }

    @Override
    public SpellState toggleState(LivingEntity entity, SpellStack stack) {
        return super.toggleState(entity, stack);
    }
}
