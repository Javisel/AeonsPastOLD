package com.javisel.aeonspast.common.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.javisel.aeonspast.common.attributes.APAttributeContainer;
import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import com.javisel.aeonspast.common.enums.TrinketEnums;
import com.javisel.aeonspast.common.items.itemproperties.APItemProperties;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.utilities.APUtilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class TrinketItem extends SpellContainer implements ICurioItem {


    private final TrinketEnums TRINKET_TYPE;
    protected Multimap<Attribute, AttributeModifier> defaultModifiers;
    private ArrayList<APAttributeContainer> attributeList;



    public TrinketItem(TrinketEnums type, Item.Properties properties, APItemProperties itemProperties, RegistryObject<Spell> spell, @Nullable APAttributeContainer... attributeContainers) {
        super(properties, itemProperties,spell);


        TRINKET_TYPE = type;

        if (attributeContainers != null) {

            attributeList = new ArrayList<>();
            attributeList.addAll(Arrays.asList(attributeContainers));


        }

     }



    public TrinketItem(TrinketEnums type, Item.Properties properties, APItemProperties itemProperties, RegistryObject<Spell>   spell) {
        super(properties, itemProperties,spell);


        TRINKET_TYPE = type;

    }



    public boolean attemptCast(LivingEntity caster, ItemStack stack) {



        return super.getSpell(caster,stack).get().attemptCast(caster,getSpellStack(caster,stack));



    }










    public TrinketItem(TrinketEnums type, Item.Properties properties, APItemProperties itemProperties, ArrayList<APAttributeContainer> attributeContainers) {
        super(properties, itemProperties,null);


        TRINKET_TYPE = type;
        if (attributeContainers != null) {

            attributeList = new ArrayList<>();
            attributeList.addAll(attributeContainers);


        }

    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        //TODO hook into Events



        super.getSpell(slotContext.entity(),stack).get().onSpellEquipped(slotContext.entity(),getSpellStack(slotContext.entity(),stack));



    }







    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {


        if (defaultModifiers == null) {


            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

            if (attributeList != null) {

                for (APAttributeContainer container : attributeList) {

                    builder.put(container.getAttribute(), container.getModifier());


                }
            }
            defaultModifiers = builder.build();

            return defaultModifiers;

        }


        return ICurioItem.defaultInstance.getAttributeModifiers(slotContext, uuid);
    }


    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        super.getSpell(slotContext.entity(),stack).get().onSpellUnEquipped(slotContext.entity(),getSpellStack(slotContext.entity(),stack));

    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {

        super.getSpell(slotContext.entity(),stack).get().tick(slotContext.entity(),getSpellStack(slotContext.entity(),stack));
    }
}
