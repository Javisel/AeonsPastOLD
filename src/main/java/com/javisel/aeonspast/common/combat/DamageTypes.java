package com.javisel.aeonspast.common.combat;


public enum DamageTypes {

    VOID("aeonspast:void", 0),
    ENDER("aeonspast:ender", 1),
    ARCANE("aeonspast:arcane", 2),
    IMPACT("aeonspast:impact", 3),
    PUNCTURE("aeonspast:puncture", 4),
    SLASH("aeonspast:slash",5),
    FIRE("aeonspast:fire",6),
    COLD("aeonspast:cold",7),
    ELECTRIC("aeonspast:electric",8),
    RADIANT("eaonspast:radiant",9),
    TOXIC("aeonspast:toxic",10),
    WITHER("aeonspast:wither",11),
    ENVIRONMENTAL("aeonspast:environmental",12);

    private final String unlocalizedName;
    private final int id;

    DamageTypes(String name, int id) {


        this.unlocalizedName = name;
        this.id = id;

    }




    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public int getId() {
        return id;
    }

    public boolean isMagical() {


        return  this != SLASH && this !=PUNCTURE && this !=IMPACT;
    }
    public boolean isAbsolute() {


        return  this == VOID;
    }

}
