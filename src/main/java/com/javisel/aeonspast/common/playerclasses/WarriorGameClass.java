package com.javisel.aeonspast.common.playerclasses;

import com.javisel.aeonspast.common.items.emblem.BasicEmblem;
import com.javisel.aeonspast.common.items.emblem.ClassStatistics;
import com.javisel.aeonspast.common.registration.ItemRegistration;

public class WarriorGameClass extends PlayerGameClass {


    public WarriorGameClass() {
        super(new ClassStatistics(0, 80, 1.5, 30, 15, 100, 2, 2, 2), ItemRegistration.WARRIOR_EMBLEM);
    }
}
