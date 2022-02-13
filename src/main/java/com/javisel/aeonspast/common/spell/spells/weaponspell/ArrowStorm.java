package com.javisel.aeonspast.common.spell.spells.weaponspell;

import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.entity.LivingEntity;

public class ArrowStorm  extends WeaponSpell {
    public ArrowStorm( ) {
        super(60, 21, SpellRank.WEAPON_SPELL);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {

    }
}
