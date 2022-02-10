package com.javisel.aeonspast.common.playerclasses;

import com.javisel.aeonspast.common.attributes.AttributeContainer;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.items.emblem.BasicEmblem;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.server.ServerHandler;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class PlayerGameClass extends net.minecraftforge.registries.ForgeRegistryEntry<PlayerGameClass> {


    private final RegistryObject<Resource> castResource;
    private final RegistryObject<Item> classEmblem;


    public PlayerGameClass(RegistryObject<Resource> getCastResource, RegistryObject<Item> classEmblem) {


        this.castResource = getCastResource;


        this.classEmblem = classEmblem;
    }

    public ClassData getClassData() {


        return ServerHandler.CLASS_STATISTICS_LOADER.getClassStatisticsMap().get(this.getRegistryName());
    }

    public BasicEmblem getClassEmblem() {


        return (BasicEmblem) classEmblem.get();
    }


    public void activateOnPlayer(Player player) {


        for (AttributeContainer container : getClassData().getAttributeModifiers(castResource.get())) {


            if (player.getAttribute(container.getAttribute()).hasModifier(container.getModifier())) {
                player.getAttribute(container.getAttribute()).removeModifier(container.getModifier());
            }

             player.getAttribute(container.getAttribute()).addPermanentModifier(container.getModifier());


        }


        if (!player.getLevel().isClientSide) {

            Utilities.syncTotalPlayerData(player);

        }


    }


    public void deActivateOnPlayer(Player player) {


        IPlayerData playerData = Utilities.getPlayerData(player);
        playerData.getOrCreatePlayerClass(this);

         playerData.setActiveGameClass(null);
        for (AttributeContainer container : getClassData().getAttributeModifiers(castResource.get())) {


            player.getAttribute(container.getAttribute()).removeModifier(container.getModifier());


        }

        if (!player.getLevel().isClientSide) {

            Utilities.syncTotalPlayerData(player);

        }


    }


    public Resource getCastResource() {
        return castResource.get();
    }


    public boolean canPlayerEquipSpell(Player player, Spell spell) {


        return getClassData().hasSpell(spell);

    }

    public boolean canWieldWeapon(Player player, String weaponType) {


        return getClassData().hasWeaponType(weaponType);

    }


}
