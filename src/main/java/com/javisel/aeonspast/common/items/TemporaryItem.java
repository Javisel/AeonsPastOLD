package com.javisel.aeonspast.common.items;

import com.javisel.aeonspast.common.items.itemproperties.APItemProperties;
import com.javisel.aeonspast.common.items.itemproperties.APItemRarity;

public class TemporaryItem extends BaseItem {

    private int itemDuration = 0;


    public TemporaryItem(Properties properties, int itemDuration) {
        super(properties, new APItemProperties(APItemRarity.MYTHICAL));
        this.itemDuration = itemDuration;
    }


}
