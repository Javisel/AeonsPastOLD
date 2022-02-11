package com.javisel.aeonspast.common.items.emblem;

import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.items.TrinketEnums;
import com.javisel.aeonspast.common.items.TrinketItem;
import com.javisel.aeonspast.common.playerclasses.PlayerGameClass;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;

public class BasicEmblem extends TrinketItem {


    final RegistryObject<PlayerGameClass> gameClass;


    public BasicEmblem(RegistryObject<PlayerGameClass> gameClass, RegistryObject<Spell> spell) {
        super(TrinketEnums.EMBLEM, new Properties().stacksTo(1).setNoRepair(), spell);


        this.gameClass = gameClass;


    }


    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {

        super.onEquip(slotContext, prevStack, stack);


        if (slotContext.entity() instanceof Player) {

            Player player = (Player) slotContext.entity();


            IPlayerData playerData = Utilities.getPlayerData(player);
            IEntityData entityData = Utilities.getEntityData(player);

             entityData.getActiveSpells().add(super.getSpell(player, stack).get());
            playerData.getSpellBar().getSpellList().set(0, super.getSpell(player, stack).get());
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
