package com.javisel.aeonspast.common.spell.spells.basicspell;

import com.javisel.aeonspast.common.registration.ResourceRegistration;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.entity.LivingEntity;

public class EnchantedArrows extends Spell {
    public EnchantedArrows(int defaultMaxCharges, int defaultChargeTime, int defaultCooldown, float defaultCost, SpellRank spellRank) {
        super(6, 35, 1, 25, SpellRank.RANK_4);
        setSpellResource(ResourceRegistration.MANA);

    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {

    }


}
