package com.javisel.aeonspast.common.items;

public enum ItemType {

    MISCELLANEOUS("aeonspast:miscellaneous", 0),
    BLOCK("aeonspast:block", 1),
    GENERIC_ITEM("aeonspast:generic_item", 2),
    SWORD("aeonspast:sword", 3),
    AXE("aeonspast:axe", 4),
    SPEAR("aeonspast:spear", 5),
    DAGGER("aeonspast:dagger", 6),
    BOW("aeonspast:bow", 7),
    CROSSBOW("aeonspast:crossbow", 8),


    ;


    private final String unlocalizedName;
    private final String descriptionKey;
    private final int id;

    ItemType(String unlocalizedName, int id) {

        this.unlocalizedName = unlocalizedName;
        this.id = id;
        this.descriptionKey = unlocalizedName + ".desc";


    }
}
