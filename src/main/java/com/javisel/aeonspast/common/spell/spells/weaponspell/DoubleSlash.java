package com.javisel.aeonspast.common.spell.spells.weaponspell;

import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class DoubleSlash extends WeaponSpell {
    public DoubleSlash() {
        super(60, 25, SpellRank.WEAPON_SPELL);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {


        if (entity instanceof Player) {


            Player player = (Player) entity;






        }







    }
}
