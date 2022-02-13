package com.javisel.aeonspast.common.spell.spells.weaponspell;

import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.entity.LivingEntity;

public class Cleave extends WeaponSpell {
    public Cleave() {
        super(60, 25, SpellRank.WEAPON_SPELL);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {

    }
}
