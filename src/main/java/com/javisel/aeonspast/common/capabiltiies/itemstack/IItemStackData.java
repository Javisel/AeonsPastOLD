package com.javisel.aeonspast.common.capabiltiies.itemstack;

import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.items.properties.ItemRarity;
import com.javisel.aeonspast.common.spell.Spell;

import java.util.ArrayList;

public interface IItemStackData {


     ArrayList<ItemProperty> getItemProperties();
    Spell getSpell();

    ItemRarity getRarity();

    void setSpell(Spell spell);
}
