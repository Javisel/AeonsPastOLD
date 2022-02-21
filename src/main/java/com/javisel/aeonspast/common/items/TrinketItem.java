package com.javisel.aeonspast.common.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.utilities.StringKeys;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.UUID;

public class TrinketItem extends BaseItem implements ICurioItem, ISpellContainer {


    private final TrinketTypes TRINKET_TYPE;

    private RegistryObject<Spell> spell;


    public TrinketItem(TrinketTypes type, Item.Properties properties, RegistryObject<Spell> spell) {
        super(properties);


        TRINKET_TYPE = type;

        this.spell = spell;


    }


    public TrinketItem(TrinketTypes type, Item.Properties properties) {
        super(properties);


        TRINKET_TYPE = type;


    }


    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        //TODO hook into Events

        LivingEntity entity = slotContext.entity();


        if (entity instanceof Player) {

            Player player = (Player) entity;
            getSpell(entity, stack) .onSpellEquipped(player, getSpellStack((Player) entity, stack));
            IPlayerData playerData = Utilities.getPlayerData(player);
            int index = -1;

            if (TRINKET_TYPE == TrinketTypes.TRINKET) {

                return;
            }
            if (TRINKET_TYPE == TrinketTypes.EMBLEM) {

                index = 0;


            }

            if (TRINKET_TYPE == TrinketTypes.ACTIVE) {


                index = 1 + slotContext.index();
            }
            if (TRINKET_TYPE == TrinketTypes.ULTIMATE) {


                index = 3;
            }


            playerData.getSpellBar().getSpellList().set(index, getSpell(player, stack));

            Utilities.syncTotalPlayerData(player);


        }


    }


    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {


        if (ItemEngine.isItemInitialized(stack)) {


            CompoundTag aeonsPastTag = ItemEngine.getAeonsPastTag(stack);
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

            Multimap<Attribute, AttributeModifier> modifierMultimap;

            if (aeonsPastTag.contains(StringKeys.ATTRIBUTE_MODIFIERS)) {


                CompoundTag modTag = aeonsPastTag.getCompound(StringKeys.ATTRIBUTE_MODIFIERS);


                for (String key : modTag.getAllKeys()) {

                      Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(key));

                    AttributeModifier modifier = AttributeModifier.load(modTag.getCompound(key));


                    builder.put(attribute, modifier);
                }
            }
            modifierMultimap = builder.build();

            return modifierMultimap;

        }


        return ICurioItem.defaultInstance.getAttributeModifiers(slotContext, uuid);
    }


    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {

        Entity entity = slotContext.entity();


        if (entity instanceof Player) {
            getSpell(slotContext.entity(), stack) .onSpellUnEquipped((Player) slotContext.entity(), getSpellStack((Player) slotContext.entity(), stack));

            Player player = (Player) entity;
            IPlayerData playerData = Utilities.getPlayerData(player);
            int index = -1;

            if (TRINKET_TYPE == TrinketTypes.EMBLEM) {

                index = 0;


            }

            if (TRINKET_TYPE == TrinketTypes.ACTIVE) {


                index = 1 + slotContext.index();
            }
            if (TRINKET_TYPE == TrinketTypes.ULTIMATE) {


                index = 3;
            }


            playerData.getSpellBar().getSpellList().set(index, Spell.getDefaultSpell());

            Utilities.syncTotalPlayerData(player);


        }


    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {

    }

 
    @Override
    public Spell getSpell(LivingEntity caster, ItemStack stack) {
        
        if (spell !=null) {
             return spell.get() ;
        }
        else {
            
            return null;
        }
        
        
        
        
        
    }


}
