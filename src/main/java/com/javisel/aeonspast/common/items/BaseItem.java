package com.javisel.aeonspast.common.items;


import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.items.itemproperties.APItemProperties;
import net.minecraft.world.item.Item;

public class BaseItem extends Item {


    private final APItemProperties properties;

    public BaseItem(Properties properties, APItemProperties apItemProperties) {
        super(properties.tab(AeonsPast.APTAB));


        this.properties = apItemProperties;
    }

    public APItemProperties getProperties() {
        return properties;
    }
}
