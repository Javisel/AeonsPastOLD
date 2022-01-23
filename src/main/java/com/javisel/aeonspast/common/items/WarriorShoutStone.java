package com.javisel.aeonspast.common.items;

import com.javisel.aeonspast.common.enums.TrinketEnums;
import com.javisel.aeonspast.common.items.itemproperties.APItemProperties;
import com.javisel.aeonspast.common.items.itemproperties.APItemRarity;
import com.javisel.aeonspast.common.registration.SpellRegistration;

public class WarriorShoutStone extends TrinketItem {
    public WarriorShoutStone() {
        super(TrinketEnums.RELIC, new Properties().stacksTo(1), new APItemProperties(APItemRarity.COMMON), SpellRegistration.WARRIOR_SHOUT);
    }
}
