package com.javisel.aeonspast.common.items.emblem;

import com.javisel.aeonspast.common.capabiltiies.IPlayerData;
import com.javisel.aeonspast.common.enums.TrinketEnums;
import com.javisel.aeonspast.common.items.TrinketItem;
import com.javisel.aeonspast.common.items.itemproperties.APItemProperties;
import com.javisel.aeonspast.common.items.itemproperties.APItemRarity;
import com.javisel.aeonspast.common.playerclasses.PlayerGameClass;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.utilities.APUtilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;

public class BasicEmblem extends TrinketItem {


    final RegistryObject<PlayerGameClass> gameClass;


    public BasicEmblem(Properties properties, RegistryObject<PlayerGameClass> gameClass, RegistryObject<Spell> spell) {
        super(TrinketEnums.EMBLEM, properties.stacksTo(1), new APItemProperties(APItemRarity.EMBLEM), spell);


        this.gameClass = gameClass;


    }


    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {

        super.onEquip(slotContext, prevStack, stack);


        if (slotContext.entity() instanceof Player) {

            Player player = (Player) slotContext.entity();

            IPlayerData playerData = APUtilities.getPlayerData(player);


            playerData.getActiveSpells().add(super.getSpell(player, stack).get());
            playerData.getSpellBar().getSpellList().set(0, super.getSpell(player,stack).get());
            getGameClass().get().activateOnPlayer(player);

        }


    }


    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {


        super.onUnequip(slotContext, newStack, stack);


        if (slotContext.entity() instanceof Player) {

            Player player = (Player) slotContext.entity();


            
            getGameClass().get().deActivateOnPlayer(player);


        }


    }


    public RegistryObject<PlayerGameClass> getGameClass() {
        return gameClass;
    }

}
