package com.javisel.aeonspast.common.items;

import com.javisel.aeonspast.common.combat.DamageTypes;

public enum  WeaponType {
    UNARMED("aeonspast:unarmed", DamageTypes.IMPACT, 0),
    SWORD("aeonspast:sword", DamageTypes.SLASH, 1),
    AXE("aeonspast:axe", DamageTypes.SLASH, 2),
    SPEAR("aeonspast:spear", DamageTypes.PUNCTURE, 3),
    DAGGER("aeonspast:dagger", DamageTypes.PUNCTURE, 4),
    BOW("aeonspast:bow", DamageTypes.IMPACT, 5),
    CROSSBOW("aeonspast:crossbow", DamageTypes.IMPACT, 6);


    private final String unlocalizedName;
    private final String descriptionKey;
    private final DamageTypes damageType;
    private final int id;

    WeaponType(String unlocalizedName, DamageTypes damageType, int id) {

        this.unlocalizedName = unlocalizedName;
        this.damageType = damageType;
        this.id = id;
        this.descriptionKey = unlocalizedName + ".desc";


    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public String getDescriptionKey() {
        return descriptionKey;
    }

    public DamageTypes getDamageType() {
        return damageType;
    }

    public int getId() {
        return id;
    }
}
