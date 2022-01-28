package com.javisel.aeonspast.common.combat.damagetypes;

import java.util.Arrays;
import java.util.Comparator;

public enum APDamageSubType {

    PENALTY("aeonspast:penalty", 0),
    TRUE("aeonspast:true", 1),
    MAGIC("aeonspast:magic", 2),
    PHYSICAL("aeonspast:physical", 3);
    private static final APDamageSubType[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(APDamageSubType::getId)).toArray((p_41067_) -> {
        return new APDamageSubType[p_41067_];
    });

    private final String unlocalizedName;
    private final int id;

    APDamageSubType(String name, int id) {


        this.unlocalizedName = name;
        this.id = id;

    }

    public static APDamageSubType[] getById() {
        return BY_ID;
    }

    public static APDamageSubType getByString(String unlocalizedName) {


        for (APDamageSubType damageSubType : BY_ID) {


            if (damageSubType.unlocalizedName.equalsIgnoreCase(unlocalizedName)) {

                return damageSubType;
            }


        }

        return null;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public int getId() {
        return id;
    }


}
