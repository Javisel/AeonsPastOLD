package com.javisel.aeonspast.common.playerclasses;

import com.javisel.aeonspast.common.registration.ItemRegistration;
import com.javisel.aeonspast.common.registration.ResourceRegistration;

public class WarriorGameClass extends PlayerGameClass {


    public WarriorGameClass() {
        super(new ClassStatistics(0, 80, 1.5, 30, 15, 100, 2, 2, 2), ResourceRegistration.FOOD, ItemRegistration.WARRIOR_EMBLEM);
    }
}
