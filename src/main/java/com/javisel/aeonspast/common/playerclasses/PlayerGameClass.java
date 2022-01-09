package com.javisel.aeonspast.common.playerclasses;

import com.javisel.aeonspast.common.items.emblem.BasicEmblem;
import com.javisel.aeonspast.common.items.emblem.ClassStatistics;

public class PlayerGameClass extends  net.minecraftforge.registries.ForgeRegistryEntry<PlayerGameClass> {


    private final ClassStatistics classStatistics;

    private final BasicEmblem  classEmblem;

    public PlayerGameClass(ClassStatistics classStatistics, BasicEmblem classEmblem) {

        this.classStatistics=classStatistics;


        this.classEmblem = classEmblem;
    }

    public ClassStatistics getClassStatistics() {
        return classStatistics;
    }

    public BasicEmblem getClassEmblem() {
        return classEmblem;
    }
}
