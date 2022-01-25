package com.javisel.aeonspast.common.playerclasses;

import com.javisel.aeonspast.common.attributes.APAttributeContainer;
import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.IPlayerData;
import com.javisel.aeonspast.common.items.emblem.BasicEmblem;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.utilities.APUtilities;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.UUID;

import static com.javisel.aeonspast.common.playerclasses.ClassStatistics.BASE_STATISTICS;

public class PlayerGameClass extends net.minecraftforge.registries.ForgeRegistryEntry<PlayerGameClass> {


    private final ClassStatistics classStatistics;




    private final RegistryObject<Resource> castResource;
    private final RegistryObject<Item> classEmblem;

    public PlayerGameClass(ClassStatistics classStatistics, RegistryObject<Resource> getCastResource, RegistryObject<Item> classEmblem) {

        this.classStatistics = classStatistics;
        this.castResource = getCastResource;


        this.classEmblem = classEmblem;
    }

    public ClassStatistics getClassStatistics() {
        return classStatistics;
    }

    public BasicEmblem getClassEmblem() {


        return (BasicEmblem) classEmblem.get();
    }


    public void activateOnPlayer(Player player) {


        IEntityData entityData = APUtilities.getEntityData(player);
        IPlayerData playerData = APUtilities.getPlayerData(player);
        playerData.getOrCreatePlayerClass(this);
        playerData.setActiveGameClass(this);
        for (APAttributeContainer container : classStatistics.getAttributeContainers()) {




            player.getAttribute(container.getAttribute()).addPermanentModifier(container.getModifier());





        }

        player.getAttribute(castResource.get().getResourceMaxAttribute().get()).addPermanentModifier( new AttributeModifier(UUID.fromString(BASE_STATISTICS), "base_stat",classStatistics.getMaxResource(), AttributeModifier.Operation.ADDITION));
        player.getAttribute(castResource.get().getResourceRegenAttribute().get()).addPermanentModifier(new AttributeModifier(UUID.fromString(BASE_STATISTICS), "base_stat",classStatistics.getResourceRegen(), AttributeModifier.Operation.ADDITION));

        castResource.get().setResourceAmount(player, classStatistics.getMaxResource(),false     );



        if (!player.getLevel().isClientSide) {

            APUtilities.syncTotalPlayerData(player);

        }



    }


    public void deActivateOnPlayer(Player player) {





        IEntityData entityData = APUtilities.getEntityData(player);
        IPlayerData playerData = APUtilities.getPlayerData(player);
        playerData.getOrCreatePlayerClass(this);
        playerData.setActiveGameClass(this);
        for (APAttributeContainer container : classStatistics.getAttributeContainers()) {




            player.getAttribute(container.getAttribute()).removeModifier(container.getModifier());





        }

        player.getAttribute(castResource.get().getResourceMaxAttribute().get()).removeModifier(UUID.fromString(BASE_STATISTICS));
        player.getAttribute(castResource.get().getResourceRegenAttribute().get()).removeModifier(UUID.fromString(BASE_STATISTICS));

        castResource.get().setResourceAmount(player, classStatistics.getMaxResource(),false     );



        if (!player.getLevel().isClientSide) {

            APUtilities.syncTotalPlayerData(player);

        }




    }








    public Resource getCastResource() {
        return castResource.get();
    }


}
