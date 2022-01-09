package com.javisel.aeonspast.common.items.emblem;

import com.javisel.aeonspast.common.attributes.APAttributeContainer;
import com.javisel.aeonspast.common.enums.TrinketEnums;
import com.javisel.aeonspast.common.items.TrinketItem;
import com.javisel.aeonspast.common.items.itemproperties.APItemProperties;
import com.javisel.aeonspast.common.items.itemproperties.APItemRarity;
import com.javisel.aeonspast.common.playerclasses.PlayerGameClass;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;

public class BasicEmblem extends TrinketItem {



    final  RegistryObject<PlayerGameClass> gameClass;

    public BasicEmblem(Properties properties, RegistryObject<PlayerGameClass> gameClass) {
        super(TrinketEnums.EMBLEM, properties.stacksTo(1), new APItemProperties(APItemRarity.EMBLEM),  (APAttributeContainer) null);


        this.gameClass=gameClass;



    }


    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        //TODO hook into Events



    }


    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {

    }





    public RegistryObject<PlayerGameClass> getGameClass() {
        return gameClass;
    }

}
