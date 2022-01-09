package com.javisel.aeonspast.common.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.javisel.aeonspast.common.attributes.APAttributeContainer;
import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import com.javisel.aeonspast.common.enums.TrinketEnums;
import com.javisel.aeonspast.common.items.itemproperties.APItemProperties;
import com.javisel.aeonspast.utilities.APUtilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class TrinketItem extends BaseItem implements ICurioItem {


    private final TrinketEnums TRINKET_TYPE;
    protected Multimap<Attribute, AttributeModifier> defaultModifiers;
    private ArrayList<APAttributeContainer> attributeList;
    private float cost;


    public TrinketItem(TrinketEnums type, Item.Properties properties, APItemProperties itemProperties, @Nullable APAttributeContainer... attributeContainers) {
        super(properties, itemProperties);


        TRINKET_TYPE = type;

        if (attributeContainers != null) {

            attributeList = new ArrayList<>();
            attributeList.addAll(Arrays.asList(attributeContainers));


        }

    }

    public TrinketItem(TrinketEnums type, Item.Properties properties, APItemProperties itemProperties,  float cost, @Nullable APAttributeContainer... attributeContainers) {
        super(properties, itemProperties);


        TRINKET_TYPE = type;

        this.cost = cost;
        if (attributeContainers != null) {

            attributeList = new ArrayList<>();
            attributeList.addAll(Arrays.asList(attributeContainers));


        }

    }

    public TrinketItem(TrinketEnums type, Item.Properties properties, APItemProperties itemProperties,  ArrayList<APAttributeContainer> attributeContainers) {
        super(properties, itemProperties);


        TRINKET_TYPE = type;
        if (attributeContainers != null) {

            attributeList = new ArrayList<>();
            attributeList.addAll(attributeContainers);


        }

    }

    public static void commitCosts(LivingEntity Caster, ItemStack stack) {


    }

    public boolean attemptCast(LivingEntity caster, ItemStack stack) {


        return false;
    }

    public void castTick(LivingEntity caster, ItemStack stack) {


    }

    public void endCast(LivingEntity caster, ItemStack stack) {


    }

    public boolean canCast(LivingEntity caster, ItemStack stack) {

        boolean cancast = true;
        IEntityData data = APUtilities.getEntityData(caster);

        if (data.getMana() < getCost(caster, stack)) {

            cancast=false;
        }

        if (caster instanceof Player) {
            Player player = (Player) caster;

            if (player.getCooldowns().isOnCooldown(stack.getItem())) {
                cancast=false;
            }


        }



        return cancast;

    }

    public float getCost(LivingEntity caster, ItemStack stack) {


        return cost;
    }

    public void setCost(float amount) {

        this.cost = amount;

    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        //TODO hook into Events
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {


        if (defaultModifiers == null) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

            for (APAttributeContainer container : attributeList) {

                builder.put(container.getAttribute(), container.getModifier());

            }
            defaultModifiers = builder.build();


        }
        return defaultModifiers;
    }
}
