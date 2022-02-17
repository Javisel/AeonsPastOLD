package com.javisel.aeonspast.common.items;

import com.google.common.collect.Multimap;
import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.server.ServerHandler;
import com.javisel.aeonspast.utilities.StringKeys;
import com.javisel.aeonspast.utilities.Tags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

import static com.javisel.aeonspast.AeonsPast.MODID;
import static com.javisel.aeonspast.utilities.StringKeys.*;

public class ItemEngine {


    public static boolean isWeaponRanged(ItemStack stack) {


        if (!isItemInitialized(stack)) {

            return  false;
        }
        if (!isWeapon(stack )) {
            return false;
        }



        return  getAeonsPastTag(stack).getBoolean(IS_RANGED);



    }


    public static boolean isRPGItem(ItemStack stack) {


        return  isArmor(stack ) || isWeapon(stack ) || isTrinket(stack);
    }


    public static boolean isTrinket(ItemStack stack) {




        return  stack.is(Tags.TRINKETS);
    }


    public static boolean isItemInitialized(ItemStack stack) {


        if (!stack.hasTag()) {

            return false;
        }


        CompoundTag tag = stack.getTag();


        return tag.contains(AeonsPast.MODID);


    }

    public static CompoundTag getAeonsPastTag(ItemStack stack) {

        CompoundTag basetag = stack.hasTag() ? stack.getTag() : new CompoundTag();


        if (basetag.contains(MODID)) {

            return basetag.getCompound(MODID);
        } else {

            CompoundTag modtag = new CompoundTag();

            basetag.put(MODID, modtag);
            return modtag;
        }


    }


    public static void initializeItem(LivingEntity entity, ItemStack stack) {


        if (!isItemInitialized(stack)) {

            getAeonsPastTag(stack).putUUID(UNIQUE_ID, UUID.randomUUID());




            if (isWeapon(stack )) {

                ServerHandler.WEAPON_STATISTICS_LOADER.getWeaponData(stack).loadToWeapon(entity, stack);

            }

          else  if (isArmor(stack )) {

                ServerHandler.ARMOR_DATA_LOADER.getArmorData(stack.getItem()).loadToArmor(entity, stack);


            }

          else if (isTrinket(stack)) {


              //TODO Trinket stuff
            }


        }


    }


    public static boolean isWeapon(ItemStack stack) {


        return  stack.is(Tags.WEAPONS);

    }

     public static boolean isArmor(ItemStack stack) {



             return  stack.is(Tags.ARMOR);


     }
    public static ArrayList<ItemProperty> getItemProperties(ItemStack stack) {

        ArrayList<ItemProperty> properties = new ArrayList<>();


        CompoundTag props = getPropertyTag(stack);


        if (!props.isEmpty()) {
            for (String key : props.getAllKeys()) {


                ItemProperty property = ItemProperty.getPropertyByLocation(new ResourceLocation(key));

                properties.add(property);
            }

        }


        return properties;
    }


    public static CompoundTag getPropertyTag(ItemStack stack) {


        CompoundTag itemTag = getAeonsPastTag(stack);

        if (itemTag.contains(ITEM_PROPERTIES)) {


            return itemTag.getCompound(ITEM_PROPERTIES);
        } else {


            CompoundTag newtag = new CompoundTag();

            itemTag.put(ITEM_PROPERTIES, newtag);

            return itemTag.getCompound(ITEM_PROPERTIES);
        }

    }


    public static Spell getSpellFromItem(ItemStack stack) {

        CompoundTag tag = getAeonsPastTag(stack);


        String key = tag.getString(SPELL);


        return Spell.getSpellByResourceLocation(new ResourceLocation(key));


    }


    public static CompoundTag getTagAndInitItem(LivingEntity initializer, ItemStack stack) {


        initializeItem(initializer, stack);


        return getAeonsPastTag(stack);
    }


    public static float getItemFlatAttributeValue(Attribute attribute, ItemStack stack, EquipmentSlot slot) {


        Multimap<Attribute, AttributeModifier> attributeMods = stack.getAttributeModifiers(slot);


        float value = 0;

        for (AttributeModifier attributeModifier : attributeMods.get(attribute)) {


            if (attributeModifier.getOperation() == AttributeModifier.Operation.ADDITION) {

                value += attributeModifier.getAmount();


            }


        }


        return value;


    }


    public static ArrayList<ItemStack> getAllAppicableItems(LivingEntity entity) {


        ArrayList<ItemStack> result = new ArrayList<>();


        for (ItemStack stack : entity.getAllSlots()) {


            result.add(stack);
        }


        if (entity instanceof Player) {
            Player player = (Player) entity;


            result.addAll(player.getInventory().items);



        }


//TODO Curios Link


        return result;


    }



}
