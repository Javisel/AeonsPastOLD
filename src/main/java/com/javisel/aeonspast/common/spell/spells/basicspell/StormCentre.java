package com.javisel.aeonspast.common.spell.spells.basicspell;

import com.javisel.aeonspast.common.registration.ResourceRegistration;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.common.spell.SpellState;
import com.javisel.aeonspast.common.spell.ToggleSpell;
import net.minecraft.world.entity.LivingEntity;

public class StormCentre extends ToggleSpell {
    public StormCentre() {
        super(10, 1000, SpellRank.RANK_4);
        setSpellResource(ResourceRegistration.MANA);

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
