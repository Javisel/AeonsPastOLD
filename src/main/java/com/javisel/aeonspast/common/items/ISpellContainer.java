package com.javisel.aeonspast.common.items;

import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface ISpellContainer {


    default SpellStack getSpellStack(Player caster, ItemStack stack) {


        return Utilities.getOrCreateSpellstack(caster,getSpell(caster,stack));


    }

    Spell getSpell(LivingEntity caster, ItemStack stack);
}
