package com.javisel.aeonspast.common.items;

import com.javisel.aeonspast.common.spell.Spell;
import net.minecraftforge.registries.RegistryObject;

public class BasicSpellStone extends TrinketItem {

    public BasicSpellStone(TrinketTypes type, RegistryObject<Spell> spell) {
        super(type, new Properties().stacksTo(1), spell);
    }


}
