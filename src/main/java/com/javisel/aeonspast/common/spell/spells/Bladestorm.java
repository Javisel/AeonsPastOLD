package com.javisel.aeonspast.common.spell.spells;

import com.javisel.aeonspast.common.registration.ResourceRegistration;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.entity.LivingEntity;

public class Bladestorm extends Spell {

    public Bladestorm() {
        super(2, 100, 5, 5, SpellRank.SKILL_BASIC);
        setSpellResource(ResourceRegistration.MANA);

    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {


    }
}
