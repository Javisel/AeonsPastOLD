package com.javisel.aeonspast.common;

public enum Resources {

    NONE(0,"none"),
    MANA(1,"mana"),
    KI(2,"ki"),
    RAGE(3,"rage");


    private final int id;



    private final String unlocalizedName;

    Resources(int id, String unlocalizedName) {
        this.id = id;
        this.unlocalizedName = unlocalizedName;
    }
}
