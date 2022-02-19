package com.javisel.aeonspast.common.playerclasses;

import com.javisel.aeonspast.common.attributes.AttributeContainer;
import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.config.playerclasses.ClassData;
import com.javisel.aeonspast.common.items.emblem.BasicEmblem;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.server.ServerHandler;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;

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


            IPlayerData playerData = Utilities.getPlayerData(player);
        IEntityData entityData = Utilities.getEntityData(player);

        playerData.setActiveGameClass(this);

        for (AttributeContainer container : getClassData().getAttributeModifiers(castResource.get())) {



                player.getAttribute(container.getAttribute()).removeModifier(container.getModifier());


             player.getAttribute(container.getAttribute()).addPermanentModifier(container.getModifier());


        }

        playerData.getResourceMap().put(getCastResource(), (float) player.getAttributeValue(castResource.get().getResourceMaxAttribute().get()));
        player.heal(player.getMaxHealth());
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


    public void onLevelUp(Player player) {


        ClassData classData = getClassData();


        IPlayerData playerData = Utilities.getPlayerData(player);
        int level  =playerData.getOrCreatePlayerClass(this).getLevel();
      ArrayList<AttributeContainer> containers= classData.getLevelModifiers(getCastResource(),level);



        for (AttributeContainer container : containers) {


            player.getAttribute(container.getAttribute()).removeModifier(container.getModifier().getId());
            player.getAttribute(container.getAttribute()).addPermanentModifier(container.getModifier());


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
