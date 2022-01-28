package com.javisel.aeonspast.common.items;


import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.items.properties.ItemRarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BaseItem extends Item {




    public BaseItem(Properties properties ) {
        super(properties.tab(AeonsPast.APTAB));


     }


}
