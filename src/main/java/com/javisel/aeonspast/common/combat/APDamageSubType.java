package com.javisel.aeonspast.common.combat;


public enum APDamageSubType {

    PENALTY("aeonspast:penalty", 0),
    TRUE("aeonspast:true", 1),
    MAGIC("aeonspast:magic", 2),
    PHYSICAL("aeonspast:physical", 3);

    private final String unlocalizedName;
    private final int id;

    APDamageSubType(String name, int id) {


        this.unlocalizedName = name;
        this.id = id;

    }




    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public int getId() {
        return id;
    }


}
