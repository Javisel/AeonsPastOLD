package com.javisel.aeonspast.common.playerclasses;

import com.javisel.aeonspast.common.items.emblem.BasicEmblem;
import com.javisel.aeonspast.common.resource.Resource;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

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


    public Resource getCastResource() {
        return castResource.get();
    }


}
