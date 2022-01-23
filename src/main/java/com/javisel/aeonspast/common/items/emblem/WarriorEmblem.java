package com.javisel.aeonspast.common.items.emblem;

import com.javisel.aeonspast.common.registration.ClassRegistration;
import com.javisel.aeonspast.common.registration.SpellRegistration;

public class WarriorEmblem extends BasicEmblem {

    public WarriorEmblem() {
        super(new Properties().stacksTo(1), ClassRegistration.WARRIOR, SpellRegistration.WARRIOR_CLASS_SPELL);
    }


}
