package com.javisel.aeonspast.common.playerclasses;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.items.emblem.BasicEmblem;
import com.javisel.aeonspast.common.items.emblem.ClassStatistics;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.RegistryObject;

import java.rmi.registry.Registry;

public class PlayerGameClass extends net.minecraftforge.registries.ForgeRegistryEntry<PlayerGameClass> {







    private final ClassStatistics classStatistics;

    private final RegistryObject <Item>  classEmblem;

    public PlayerGameClass(ClassStatistics classStatistics, RegistryObject <Item>  classEmblem) {

        this.classStatistics = classStatistics;



        this.classEmblem = classEmblem;
    }

    public ClassStatistics getClassStatistics() {
        return classStatistics;
    }

    public BasicEmblem getClassEmblem() {



        return  (BasicEmblem) classEmblem.get();
    }







}
