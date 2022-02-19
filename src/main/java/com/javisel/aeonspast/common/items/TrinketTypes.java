package com.javisel.aeonspast.common.items;

import com.javisel.aeonspast.utilities.StringKeys;

public enum TrinketTypes {

    EMBLEM(StringKeys.EMBLEM_IDENTIFIER, 0),
    TRINKET(StringKeys.TRINKET_IDENTIFIER, 1),
    ACTIVE(StringKeys.ACTIVE_SLOT_IDENTIFIERS, 2),
    ULTIMATE(StringKeys.ULTIMATE_SLOT_IDENTIFIER, 3);


    private final String identifier;
    private final int id;

    TrinketTypes(String identifier, int id) {

        this.identifier = identifier;
        this.id = id;

    }


    public static TrinketTypes getEnumFromString(String name) {


        return switch (name) {
            case StringKeys.EMBLEM_IDENTIFIER -> EMBLEM;
            case StringKeys.ACTIVE_SLOT_IDENTIFIERS -> ACTIVE;
            case StringKeys.ULTIMATE_SLOT_IDENTIFIER -> ULTIMATE;
            case StringKeys.TRINKET_IDENTIFIER -> TRINKET;
            default -> null;
        };


    }

    public static TrinketTypes getEmblemFromInt(int input) {


        switch (input) {

            case 0:

                return EMBLEM;
            case 1:

                return TRINKET;

            case 2:

                return ACTIVE;
            case 3:

                return ULTIMATE;
            default:
                return null;

        }


    }


    public String getIdentifier() {
        return identifier;
    }


    public int getId() {
        return id;
    }
}
