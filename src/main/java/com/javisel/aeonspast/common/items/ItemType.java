package com.javisel.aeonspast.common.items;

public enum ItemType {

    MISCELLANEOUS("aeonspast:miscellaneous", 0),
    BLOCK("aeonspast:block", 1),
    GENERIC_ITEM("aeonspast:generic_item", 2);





    private final String unlocalizedName;
    private final String descriptionKey;
    private final int id;

    ItemType(String unlocalizedName, int id) {

        this.unlocalizedName = unlocalizedName;
        this.id = id;
        this.descriptionKey = unlocalizedName + ".desc";


    }
}
