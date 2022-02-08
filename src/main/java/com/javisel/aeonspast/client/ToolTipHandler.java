package com.javisel.aeonspast.client;


import com.google.common.collect.Multimap;
import com.javisel.aeonspast.GameEventHandler;
import com.javisel.aeonspast.common.config.ArmorData;
import com.javisel.aeonspast.common.config.AttributeStatisticsPair;
import com.javisel.aeonspast.common.config.StatisticPair;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.items.properties.ItemRarity;
import com.javisel.aeonspast.common.config.WeaponData;
import com.javisel.aeonspast.common.networking.stacksyncmessage.StackSyncMessage;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.registration.PacketRegistration;
import com.javisel.aeonspast.common.spell.Spell;
import com.mojang.datafixers.util.Either;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkDirection;
import org.antlr.v4.runtime.misc.MultiMap;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static net.minecraft.world.item.ItemStack.ATTRIBUTE_MODIFIER_FORMAT;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ToolTipHandler {


    @SubscribeEvent
    public static void overrideToolTip(RenderTooltipEvent.GatherComponents event) {

        if (event.getItemStack().isEmpty()) {
            return;
        }

        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();

        if (ItemEngine.isRPGItem(stack)) {


            List<Either<FormattedText, TooltipComponent>> tooltips = event.getTooltipElements();

            clearAllKeepName(stack, tooltips);


            if (ItemEngine.isItemInitialized(stack)) {


                addPropertyToolTips(stack, tooltips);


                if (ItemEngine.isWeapon(item)) {
                    adjustWeaponTooltip(stack, tooltips);

                } else if (ItemEngine.isArmor(item)) {


                    adjustArmorTooltip(stack, tooltips);
                }


            } else {
                PacketRegistration.INSTANCE.sendTo(new StackSyncMessage(), Minecraft.getInstance().getConnection().getConnection(), NetworkDirection.PLAY_TO_SERVER);

                MutableComponent translatableComponent = new TranslatableComponent("tooltip.sleepingweapon.desc").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.RED);

                tooltips.add(Either.left(translatableComponent));


            }


        }


    }


    public static void clearAllKeepName(ItemStack stack, List<Either<FormattedText, TooltipComponent>> tooltips) {


        tooltips.clear();


        tooltips.add(Either.left(stack.getHoverName()));

    }


    public static void addPropertyToolTips(ItemStack stack, List<Either<FormattedText, TooltipComponent>> tooltips) {


        CompoundTag tag = ItemEngine.getPropertyTag(stack);


        ArrayList<ItemProperty> properties = ItemEngine.getItemProperties(stack);



        ArrayList<ItemProperty> displayproperty = new ArrayList<>();


        for (ItemProperty property : properties) {

            if (property.isDisplayed()) {
                displayproperty.add(property);
            }

        }


         if (!displayproperty.isEmpty()) {
             MutableComponent propertycomponent = new TranslatableComponent("");

            for (ItemProperty property : displayproperty) {

                if (property instanceof ItemRarity) {
                    MutableComponent mutableComponent = new TranslatableComponent(property.getRegistryName().toString());

                    tooltips.add(Either.left(mutableComponent));
                } else {

                         if (properties.indexOf(property) != 1) {
                            propertycomponent.append(" ");
                        }
                        propertycomponent.append(new TranslatableComponent(property.getRegistryName().toString()));

                        if (properties.indexOf(property) != properties.size() - 1) {

                            propertycomponent.append(",");
                        }


                }


            }
             tooltips.add(Either.left(propertycomponent));

         }

     }

    public static void adjustWeaponTooltip(ItemStack stack, List<Either<FormattedText, TooltipComponent>> tooltips) {


        WeaponData weaponData = GameEventHandler.WEAPON_STATISTICS_LOADER.getWeaponData(stack.getItem());


        Multimap<Attribute, AttributeModifier> attributeMods = stack.getAttributeModifiers(EquipmentSlot.MAINHAND);


        double power = ItemEngine.getItemFlatAttributeValue(AttributeRegistration.WEAPON_POWER.get(), stack, EquipmentSlot.MAINHAND);
        ;
        double dps = power;

        tooltips.add(Either.left(getAttributeComponent(AttributeRegistration.WEAPON_POWER.get(), stack, weaponData.getAttack_damage(), power, EquipmentSlot.MAINHAND)));

        double attack_speed = 1 + ItemEngine.getItemFlatAttributeValue(Attributes.ATTACK_SPEED, stack, EquipmentSlot.MAINHAND);
        ;
        dps *= attack_speed;


        tooltips.add((Either.left(getAttributeComponent(Attributes.ATTACK_SPEED, stack, weaponData.getAttack_speed(), attack_speed, EquipmentSlot.MAINHAND))));
        double crit_chance = ItemEngine.getItemFlatAttributeValue(AttributeRegistration.CRITICAL_CHANCE.get(), stack, EquipmentSlot.MAINHAND);
        ;


        if (crit_chance != 0) {
            tooltips.add(Either.left(getAttributeComponent(AttributeRegistration.CRITICAL_CHANCE.get(), stack, weaponData.getCritical_chance(), crit_chance, EquipmentSlot.MAINHAND)));


        }

        double crit_damage = ItemEngine.getItemFlatAttributeValue(AttributeRegistration.CRITICAL_DAMAGE.get(), stack, EquipmentSlot.MAINHAND);
        ;


        if (crit_damage != 2) {

            tooltips.add(Either.left(getAttributeComponent(AttributeRegistration.CRITICAL_DAMAGE.get(), stack, weaponData.getCritical_damage(), crit_damage, EquipmentSlot.MAINHAND)));


        }


        double range = 5 + ItemEngine.getItemFlatAttributeValue(ForgeMod.REACH_DISTANCE.get(), stack, EquipmentSlot.MAINHAND);
        ;

        tooltips.add(Either.left(getAttributeComponent(ForgeMod.REACH_DISTANCE.get(), stack, weaponData.getRange(), range, EquipmentSlot.MAINHAND)));


        Component component = getVariableStringComponent("aeonspast:dps");


        Component numbercomponent = new TranslatableComponent(ATTRIBUTE_MODIFIER_FORMAT.format(dps));

        numbercomponent = applyColourFormattings(weaponData.getDPS().getmin(), weaponData.getDPS().getMax(), dps, numbercomponent);

        component = component.copy().append(numbercomponent).withStyle(ChatFormatting.ITALIC);
        tooltips.add(Either.left(component));


        Spell spell = ItemEngine.getSpellFromItem(stack);

        if (spell != null) {
            Component spellcomponent = new TranslatableComponent("tooltip.weaponspell").append(": ").append(new TranslatableComponent(spell.getRegistryName().toString()));

            tooltips.add(Either.left(spellcomponent));
            tooltips.add(Either.left(new TranslatableComponent(spell.getSimpleDescription())));
        }

    }


    public static void adjustArmorTooltip(ItemStack stack, List<Either<FormattedText, TooltipComponent>> tooltips) {

        ArmorData armorData = GameEventHandler.ARMOR_DATA_LOADER.getArmorData(stack.getItem());


        Multimap<Attribute, AttributeModifier> attributeMods = stack.getAttributeModifiers(armorData.getEquipmentSlot());


        for (Map.Entry entry : attributeMods.entries()) {

            Attribute key = (Attribute) entry.getKey();

            AttributeModifier modifier = (AttributeModifier) entry.getValue();
            StatisticPair pair = armorData.getStatisticPair(key);


            double value = modifier.getAmount();


             tooltips.add(Either.left(getAttributeComponent(key, stack, armorData.getStatisticPair(key), value, armorData.getEquipmentSlot())));


        }


    }


    public static MutableComponent applyColourFormattings(double min, double max, double value, Component component) {


        double average = (min + max) / 2;


        ChatFormatting formatting = ChatFormatting.BLACK;


        double averageToHighest = Math.abs(max - average) / ((max + average) / 2);
        double lowestToAverage = Math.abs(min - average) / ((average + min) / 2);
        double mincheck = min * lowestToAverage;
        double maxcheck = max * averageToHighest;
        lowestToAverage /= 10;
        averageToHighest /= 10;
        if (lowestToAverage < 0) {
            lowestToAverage *= -1;
        }


        if (min == max) {
            formatting = ChatFormatting.DARK_GREEN;

            return component.copy().withStyle(formatting);
        }

        if (value < average) {

            if (value <= min + ((mincheck * .5))) {


                formatting = ChatFormatting.GRAY;

            } else if (value > min + (mincheck * 0.5) && value < min + (mincheck * 0.9)) {


                formatting = ChatFormatting.DARK_GRAY;

            } else if (value >= min + ((mincheck * 0.9))) {


                formatting = ChatFormatting.GREEN;
            }

        } else if (value >= average) {


            if (value <= average + ((maxcheck * 0.10))) {

                formatting = ChatFormatting.GREEN;

            } else if (value > average + (maxcheck * 0.10) && value <= average + (maxcheck * 0.5)) {

                formatting = ChatFormatting.AQUA;

            } else if (value > average + (maxcheck * 0.5) && value < average + (maxcheck * 0.9)) {


                formatting = ChatFormatting.DARK_PURPLE;

            } else if (value >= average + (maxcheck * 0.9)) {


                formatting = ChatFormatting.GOLD;
            }


        }


        return component.copy().withStyle(formatting);

    }

    public static Component getVariableStringComponent(String key) {


        TranslatableComponent attributeName = new TranslatableComponent(key);

        Component component = attributeName.append(": ");


        return component;
    }
    public static final DecimalFormat ATTRIBUTE_MODIFIER_FORMAT = Util.make(new DecimalFormat("#.##"), (p_41704_) -> {
        p_41704_.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT));
    });
    public static Component getAttributeComponent(Attribute attribute, ItemStack stack, StatisticPair pair, double value, EquipmentSlot slot) {

            boolean isminute=false;
        if (pair instanceof AttributeStatisticsPair) {

            AttributeStatisticsPair attributePair = (AttributeStatisticsPair) pair;


             if (attributePair.getOperation()== AttributeModifier.Operation.MULTIPLY_BASE ||attributePair.getOperation()== AttributeModifier.Operation.MULTIPLY_TOTAL  ) {


                isminute=true;


            }


        }


        double inputvalue = value;



        TranslatableComponent component = (TranslatableComponent) getVariableStringComponent(attribute.getDescriptionId());

        //component.append(new TranslatableComponent("+ "));

         if (isminute) {
            inputvalue= value*100;
        }
        Component numbercomponent = new TranslatableComponent(ATTRIBUTE_MODIFIER_FORMAT.format(inputvalue));

        if (isminute) {
            numbercomponent = applyColourFormattings(pair.getmin()*100, pair.getMax()*100, inputvalue, numbercomponent);


        } else{
            numbercomponent = applyColourFormattings(pair.getmin(), pair.getMax(), inputvalue, numbercomponent);

        }
         component = (TranslatableComponent) component.copy().append(numbercomponent);


        if (isminute) {
            component = (TranslatableComponent) component.append("%");

        }
        return component;

    }

}


