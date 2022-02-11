package com.javisel.aeonspast.common.config;

import com.javisel.aeonspast.common.items.ArmorType;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.items.properties.ItemRarity;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.*;

import static com.javisel.aeonspast.utilities.StringKeys.*;

public class ArmorData {


    public static final String ITEM_MOD_ID = "4703e862-a7ae-4697-aeea-f58ac8697e10";

    public static final String BASE_STATS = "aeonspast:armorstats";



    private final ArmorType armor_type;
    private final ItemRarity rarity;
    private final ArrayList<AttributeStatisticsPair> statistics = new ArrayList<>();
    private final List<String> properties = new ArrayList<>();

    public ArmorData(ArmorType item_type, ItemRarity rarity) {


        this.armor_type = item_type;
        this.rarity = rarity;
    }

    public CompoundTag toNBT() {

        CompoundTag tag = new CompoundTag();


        tag.putString(ARMOR_TYPE,armor_type.toString());
        tag.putString(RARITY,rarity.toString());

        CompoundTag stattag = new CompoundTag();
        int i = 0;

        for (AttributeStatisticsPair pair : statistics) {

            stattag.put("entry"+i, pair.toNBT());

            i++;

        }

        tag.put(STATISTICS,stattag);

        CompoundTag props = new CompoundTag();
        for  (String prop : properties) {


            props.putString(prop,"");




        }



        tag.put(ITEM_PROPERTIES,props);



        return tag;


    }

    public static ArmorData fromNBT(CompoundTag tag) {


        ArmorData data = new ArmorData(ArmorType.valueOf(tag.getString(ARMOR_TYPE)), ItemRarity.valueOf(tag.getString(RARITY)));

        CompoundTag stats = tag.getCompound(STATISTICS);


        for (String key : stats.getAllKeys()) {

            AttributeStatisticsPair pair =  AttributeStatisticsPair.getPairFromNBT(stats.getCompound(key));


             data.statistics.add(pair);

        }




         data.properties.addAll(tag.getCompound(ITEM_PROPERTIES).getAllKeys());





        return  data;

    }





    public void loadToArmor(@Nullable LivingEntity entity, ItemStack stack) {


        if (stack.getAttributeModifiers(getEquipmentSlot()).containsKey(ITEM_MOD_ID)) {
            return;
        }

        float luck = 0;
        Random random = entity == null ? new Random() : entity.getRandom();

        if (entity != null) {

            luck = (float) entity.getAttributeValue(AttributeRegistration.FORTUNE.get());

        }




        for (String property : properties) {


            ItemProperty.getPropertyByLocation(new ResourceLocation(property)).applyToItem(stack);


        }






        for (AttributeStatisticsPair statisticsPair : statistics) {




            Attribute attribute = statisticsPair.getAttribute();




            stack.addAttributeModifier(attribute, new AttributeModifier(UUID.randomUUID(), BASE_STATS, statisticsPair.roll(luck,random), statisticsPair.getOperation()),getEquipmentSlot());











        }




    }


    public ArrayList<AttributeStatisticsPair> getStatistics() {
        return statistics;
    }

    public StatisticPair getStatisticPair(Attribute attribute) {


        for (AttributeStatisticsPair statisticsPair : statistics){
            if (statisticsPair.getAttribute() == attribute) {

                return  statisticsPair;
            }


        }

        return  null;
    }


    public ArmorType getArmorType() {
        return armor_type;
    }



    public EquipmentSlot getEquipmentSlot() {


        return  armor_type.getEquipmentSlot();


    }





}
