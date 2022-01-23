package com.javisel.aeonspast.common.enums;

import com.javisel.aeonspast.utilities.StringKeys;

public enum TrinketEnums {

    EMBLEM(StringKeys.EMBLEM_IDENTIFIER, 0),
    TRINKET(StringKeys.TRINKET_IDENTIFIER, 1),
    AMULET(StringKeys.AMULET_IDENTIFIER, 2),
    RELIC(StringKeys.RELIC_IDENTIFIER, 3);


    private final String identifier;
    private final int id;

    TrinketEnums(String identifier, int id) {

        this.identifier = identifier;
        this.id = id;

    }


    public static TrinketEnums getEnumFromString(String name) {


        switch (name) {

            case StringKeys.EMBLEM_IDENTIFIER:

                return EMBLEM;
            case StringKeys.AMULET_IDENTIFIER:

                return AMULET;
            case StringKeys.RELIC_IDENTIFIER:

                return RELIC;
            case StringKeys.TRINKET_IDENTIFIER:

                return TRINKET;
            default:
                return null;

        }


    }

    public static TrinketEnums getEmblemFromInt(int input) {


        switch (input) {

            case 0:

                return EMBLEM;
            case 1:

                return TRINKET;

            case 2:

                return AMULET;
            case 3:

                return RELIC;
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
