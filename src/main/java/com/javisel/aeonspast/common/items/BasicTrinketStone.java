package com.javisel.aeonspast.common.items;

import com.javisel.aeonspast.common.enums.TrinketEnums;
import com.javisel.aeonspast.common.items.itemproperties.APItemProperties;
import com.javisel.aeonspast.common.items.itemproperties.APItemRarity;
import com.javisel.aeonspast.common.spell.Spell;
import net.minecraftforge.registries.RegistryObject;


public class BasicTrinketStone extends TrinketItem {

    public BasicTrinketStone(RegistryObject<Spell> spellRegistryObject) {
        super(TrinketEnums.AMULET, new Properties().stacksTo(1), new APItemProperties(APItemRarity.COMMON), spellRegistryObject);
    }
}
