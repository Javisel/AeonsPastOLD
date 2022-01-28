package com.javisel.aeonspast.common.items;

import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

public interface ISpellContainer {


    default SpellStack getSpellStack(LivingEntity caster, ItemStack stack) {


        return Utilities.getEntityData(caster).getOrCreateSpellStack(getSpell(caster, stack).get());


    }

    RegistryObject<Spell> getSpell(LivingEntity caster, ItemStack stack);
}
