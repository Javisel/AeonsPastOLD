package com.javisel.aeonspast.common.items;

import com.javisel.aeonspast.common.items.itemproperties.APItemProperties;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.APUtilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

public class SpellContainer  extends BaseItem{


    private final RegistryObject<Spell> spell;

    public SpellContainer(Properties properties, APItemProperties apItemProperties, RegistryObject<Spell> spell) {
        super(properties, apItemProperties);

        this.spell=spell;
    }





    public SpellStack getSpellStack(LivingEntity caster, ItemStack stack) {





        return APUtilities.getEntityData(caster).getOrCreateSpellStack(getSpell(caster,stack).get());


    }
    public RegistryObject<Spell> getSpell(LivingEntity caster, ItemStack stack) {
        return spell;
    }
}
