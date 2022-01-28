package com.javisel.aeonspast.common.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.javisel.aeonspast.common.attributes.AttributeContainer;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.enums.TrinketEnums;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class TrinketItem extends BaseItem implements ICurioItem, ISpellContainer {


    private final TrinketEnums TRINKET_TYPE;
    protected Multimap<Attribute, AttributeModifier> defaultModifiers;
    private ArrayList<AttributeContainer> attributeList;

    private RegistryObject<Spell> spell;


    public TrinketItem(TrinketEnums type, Item.Properties properties,  RegistryObject<Spell> spell, @Nullable AttributeContainer... attributeContainers) {
        super(properties);


        TRINKET_TYPE = type;

        this.spell = spell;


        if (attributeContainers != null) {

            attributeList = new ArrayList<>();
            attributeList.addAll(Arrays.asList(attributeContainers));


        }

    }
    public TrinketItem(TrinketEnums type, Item.Properties properties, RegistryObject<Spell> spell ) {
        super(properties);


        TRINKET_TYPE = type;

        this.spell = spell;


    }

    public TrinketItem(TrinketEnums type,Item.Properties itemProperties) {
        super(itemProperties);


        TRINKET_TYPE = type;

    }


    public TrinketItem(TrinketEnums type, Item.Properties properties, ArrayList<AttributeContainer> attributeContainers) {
        super(properties);


        TRINKET_TYPE = type;
        if (attributeContainers != null) {

            attributeList = new ArrayList<>();
            attributeList.addAll(attributeContainers);


        }

    }


    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        //TODO hook into Events

        LivingEntity entity = slotContext.entity();
        getSpell(entity, stack).get().onSpellEquipped(entity, getSpellStack(entity, stack));


        if (entity instanceof Player) {

            Player player = (Player) entity;
            IPlayerData playerData = Utilities.getPlayerData(player);
            int index = -1;

            if (TRINKET_TYPE == TrinketEnums.TRINKET) {

                return;
            }
            if (TRINKET_TYPE == TrinketEnums.EMBLEM) {

                index = 0;


            }

            if (TRINKET_TYPE == TrinketEnums.AMULET) {


                index = 1 + slotContext.index();
            }
            if (TRINKET_TYPE == TrinketEnums.RELIC) {


                index = 3;
            }


            System.out.println("Index: " + index + " Size: " + playerData.getSpellBar().getSpellList().size());


            playerData.getSpellBar().getSpellList().set(index, getSpell(player, stack).get());

            Utilities.syncTotalPlayerData(player);


        }


    }


    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {


        if (defaultModifiers == null) {


            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

            if (attributeList != null) {

                for (AttributeContainer container : attributeList) {

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


        getSpell(slotContext.entity(), stack).get().onSpellUnEquipped(slotContext.entity(), getSpellStack(slotContext.entity(), stack));

        Entity entity = slotContext.entity();

        if (entity instanceof Player) {

            Player player = (Player) entity;
            IPlayerData playerData = Utilities.getPlayerData(player);
            int index = -1;

            if (TRINKET_TYPE == TrinketEnums.EMBLEM) {

                index = 0;


            }

            if (TRINKET_TYPE == TrinketEnums.AMULET) {


                index = 1 + slotContext.index();
            }
            if (TRINKET_TYPE == TrinketEnums.RELIC) {


                index = 3;
            }


            playerData.getSpellBar().getSpellList().set(index, Spell.getDefaultSpell());

            Utilities.syncTotalPlayerData(player);


        }


    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {

    }


    public void onEventTrigger(LivingEntity entity, ItemStack stack, Event event) {


    }

    @Override
    public RegistryObject<Spell> getSpell(LivingEntity caster, ItemStack stack) {
        return spell;
    }
}
