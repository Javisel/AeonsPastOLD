package com.javisel.aeonspast.common.items.basic;

import com.javisel.aeonspast.common.items.BaseItem;
import com.javisel.aeonspast.common.items.itemproperties.APItemProperties;
import com.javisel.aeonspast.common.items.itemproperties.APItemRarity;
import com.javisel.aeonspast.utilities.APUtilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class debuggitem extends BaseItem {
    public debuggitem(Properties properties, APItemProperties apItemProperties) {
        super(new Properties(), new APItemProperties(APItemRarity.COMMON));
    }


    @Override
    public void onUseTick(Level p_41428_, LivingEntity livingEntity, ItemStack p_41430_, int p_41431_) {
        super.onUseTick(p_41428_, livingEntity, p_41430_, p_41431_);

        APUtilities.addManaToUnit(livingEntity, -5);


    }
}
