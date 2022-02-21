package com.javisel.aeonspast.common.spell.spells.weaponspell;

import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.entity.LivingEntity;

public class Chuck extends WeaponSpell {

    public Chuck() {
        super(100, 40, SpellRank.WEAPON_SPELL);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {

    }
}
