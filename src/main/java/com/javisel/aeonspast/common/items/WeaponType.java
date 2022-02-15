package com.javisel.aeonspast.common.items;

import com.javisel.aeonspast.common.combat.DamageTypeEnum;

public enum  WeaponType {
    UNARMED("aeonspast:unarmed", DamageTypeEnum.IMPACT, 0),
    SWORD("aeonspast:sword", DamageTypeEnum.SLASH, 1),
    AXE("aeonspast:axe", DamageTypeEnum.SLASH, 2),
    SPEAR("aeonspast:spear", DamageTypeEnum.PUNCTURE, 3),
    DAGGER("aeonspast:dagger", DamageTypeEnum.PUNCTURE, 4),
    BOW("aeonspast:bow", DamageTypeEnum.IMPACT, 5),
    CROSSBOW("aeonspast:crossbow", DamageTypeEnum.IMPACT, 6);


    private final String unlocalizedName;
    private final String descriptionKey;
    private final DamageTypeEnum damageType;
    private final int id;

    WeaponType(String unlocalizedName, DamageTypeEnum damageType, int id) {

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

    public DamageTypeEnum getDamageType() {
        return damageType;
    }

    public int getId() {
        return id;
    }
}
