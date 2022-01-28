package com.javisel.aeonspast.common.playerclasses;

import com.javisel.aeonspast.GameEventHandler;
import com.javisel.aeonspast.common.registration.ItemRegistration;
import com.javisel.aeonspast.common.registration.ResourceRegistration;

import java.util.ArrayList;

public class WarriorGameClass extends PlayerGameClass {


    public WarriorGameClass() {
        super(ResourceRegistration.MANA, ItemRegistration.WARRIOR_EMBLEM);
    }


}