package com.javisel.aeonspast.common.config;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.items.ArmorType;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.*;

public class ArmorData {


    public static final String ITEM_MOD_ID = "4703e862-a7ae-4697-aeea-f58ac8697e10";

    public static final String BASE_STATS = "aeonspast:armorstats";



    private final ArmorType armor_type;
    private final ArrayList<AttributeStatisticsPair> statistics = new ArrayList<>();
    private final List<String> properties = new ArrayList<>();

    public ArmorData(ArmorType item_type) {


        this.armor_type = item_type;
    }

    public CompoundTag toNBT() {

        CompoundTag tag = new CompoundTag();


        return tag;


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




             System.out.println("Entry Key: " + statisticsPair.getAttribute().getRegistryName().toString());
            System.out.println("Value Max: "+ statisticsPair.max);

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
