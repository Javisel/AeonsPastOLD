package com.javisel.aeonspast.common.playerclasses;

import com.javisel.aeonspast.common.registration.ItemRegistration;
import com.javisel.aeonspast.common.registration.ResourceRegistration;

public class WarriorGameClass extends PlayerGameClass {


    public WarriorGameClass() {
        super(new ClassStatistics(-0.003, 80, 1.5, 30, 15, 326, 2, 0, 0), ResourceRegistration.MANA, ItemRegistration.WARRIOR_EMBLEM);
    }


}
