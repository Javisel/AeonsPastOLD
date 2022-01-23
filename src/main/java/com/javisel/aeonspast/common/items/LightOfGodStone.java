package com.javisel.aeonspast.common.items;

import com.javisel.aeonspast.common.enums.TrinketEnums;
import com.javisel.aeonspast.common.items.itemproperties.APItemProperties;
import com.javisel.aeonspast.common.items.itemproperties.APItemRarity;
import com.javisel.aeonspast.common.registration.SpellRegistration;

public class LightOfGodStone extends TrinketItem {
    public LightOfGodStone() {
        super(TrinketEnums.RELIC, new Properties().stacksTo(1), new APItemProperties(APItemRarity.COMMON), SpellRegistration.LIGHT_OF_GOD);
    }
}
