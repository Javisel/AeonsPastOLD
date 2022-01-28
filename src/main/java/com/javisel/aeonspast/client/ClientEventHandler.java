package com.javisel.aeonspast.client;


import com.javisel.aeonspast.GameEventHandler;
import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.itemstack.IItemStackData;
import com.javisel.aeonspast.common.capabiltiies.itemstack.ItemStackData;
import com.javisel.aeonspast.common.capabiltiies.itemstack.ItemStackDataCapability;
import com.javisel.aeonspast.common.capabiltiies.itemstack.ItemStackDataProvider;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.mojang.datafixers.util.Either;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber
public class ClientEventHandler {



    @SubscribeEvent
    public static void overrideToolTip(RenderTooltipEvent.GatherComponents event) {

        if (event.getItemStack().isEmpty()) {
            return;
        }

        ItemStack stack = event.getItemStack();

        if (GameEventHandler.WEAPON_STATISTICS_LOADER.getWeaponData(stack.getItem().getRegistryName()) !=null) {



            List<Either<FormattedText, TooltipComponent>> tooltips = event.getTooltipElements();

                adjustWeaponTooltip(stack,tooltips);


        }


    }


public static void adjustWeaponTooltip(ItemStack stack,  List<Either<FormattedText, TooltipComponent>> tooltips ){

    IItemStackData stackData = stack.getCapability(ItemStackDataCapability.ITEM_STACK_DATA_CAP, null).orElseThrow(NullPointerException::new);


    if (stackData.getItemProperties() == null) {
        return;
    }


    for (ItemProperty property : stackData.getItemProperties()) {


        TranslatableComponent translatableComponent = new TranslatableComponent(property.getRegistryName().toString());


        tooltips.add(Either.left(translatableComponent));





    }






}



}
