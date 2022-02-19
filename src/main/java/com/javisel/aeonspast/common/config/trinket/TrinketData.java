package com.javisel.aeonspast.common.config.trinket;

import com.google.common.collect.Multimap;
import com.javisel.aeonspast.common.config.AttributeStatisticsPair;
import com.javisel.aeonspast.common.config.StatisticPair;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.items.TrinketTypes;
import com.javisel.aeonspast.common.items.properties.ItemRarity;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.spell.Spell;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.javisel.aeonspast.utilities.StringKeys.*;

public class TrinketData {
    public static final UUID TRINKET_UUID = UUID.fromString("78d0f267-a519-40d9-b34f-8b7e794a05b4");

    public static final String BASE_STATS = "aeonspast:trinket_stats";


    private final ItemRarity rarity;
    private final TrinketTypes trinket_type;
     private final String spell;
    private final ArrayList<AttributeStatisticsPair> statistics = new ArrayList<>();
    private final List<String> properties = new ArrayList<>();
     public TrinketData(TrinketTypes trinket_type, ItemRarity rarity, String spell) {


        this.trinket_type = trinket_type;
        this.rarity = rarity;
        this.spell = spell;
    }






    public void loadToTrinket(@Nullable LivingEntity entity, ItemStack stack) {

        Random random = entity == null ? new Random() : entity.getRandom();

        float luck = 0;
        if (entity != null) {

            luck = (float) entity.getAttributeValue(AttributeRegistration.FORTUNE.get());

        }
        ItemEngine.getAeonsPastTag(stack).putString(TRINKET_TYPE, trinket_type.toString());

        ItemEngine.getAeonsPastTag(stack).putString(RARITY, rarity.name());

        ItemEngine.getAeonsPastTag(stack).putUUID(UNIQUE_ID,UUID.randomUUID());


        SlotContext context = new SlotContext(trinket_type.getIdentifier(),entity,0,false,true);


         Multimap<Attribute, AttributeModifier> mods;



         if (stack.getItem() instanceof ICurioItem) {

             ICurioItem curioItem = (ICurioItem) stack.getItem();


             mods=curioItem.getAttributeModifiers(context,TRINKET_UUID,stack);


         } else {
             return;
         }





             for (AttributeStatisticsPair attributeStatisticsPair : statistics) {


                 AttributeModifier modifier = new AttributeModifier(TRINKET_UUID, BASE_STATS, attributeStatisticsPair.roll(luck, random), attributeStatisticsPair.getOperation());

                mods.put(attributeStatisticsPair.getAttribute(),modifier);

             }






    }






    public CompoundTag toNBT() {

        CompoundTag tag = new CompoundTag();


        tag.putString(TRINKET_TYPE,trinket_type.toString());
        tag.putString(RARITY,rarity.toString());

        CompoundTag props = new CompoundTag();
        for  (String prop : properties) {


            props.putString(prop,"");




        }

        CompoundTag stattag = new CompoundTag();
        int i = 0;
        for (AttributeStatisticsPair pair : statistics) {

            stattag.put("entry"+i, pair.toNBT());

            i++;

        }

        tag.put(STATISTICS,stattag);



        tag.put(ITEM_PROPERTIES,props);

        tag.putString(SPELL,spell);


        return tag;


    }


    public  static TrinketData fromNBT(CompoundTag tag) {


        TrinketData data = new TrinketData(TrinketTypes.valueOf(tag.getString(TRINKET_TYPE)), ItemRarity.valueOf(tag.getString(RARITY)),tag.getString(SPELL));

        CompoundTag stats = tag.getCompound(STATISTICS);


        for (String key : stats.getAllKeys()) {

            AttributeStatisticsPair pair =  AttributeStatisticsPair.getPairFromNBT(stats.getCompound(key));


            data.statistics.add(pair);

        }



        data.properties.addAll(tag.getCompound(ITEM_PROPERTIES).getAllKeys());





        return  data;

    }





    public TrinketTypes getTrinket_type() {
        return trinket_type;
    }

    public Spell getSpell() {







        return  Spell.getSpellByResourceLocation(new ResourceLocation(spell));




    }

    public StatisticPair getStatisticPair(Attribute attribute) {


        for (AttributeStatisticsPair statisticsPair : statistics){
            if (statisticsPair.getAttribute() == attribute) {

                return  statisticsPair;
            }


        }

        return  null;
    }

}