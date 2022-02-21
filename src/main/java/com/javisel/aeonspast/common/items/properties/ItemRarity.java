package com.javisel.aeonspast.common.items.properties;

import net.minecraft.ChatFormatting;

import java.util.Arrays;
import java.util.Comparator;

public enum ItemRarity {


    COMMON(0, "aeonspast:common", ChatFormatting.GRAY),
    RARE(1, "aeonspast:rare", ChatFormatting.DARK_BLUE),
    EPIC(2, "aeonspast:epic", ChatFormatting.BLUE),
    FABLED(3, "aeonspast:fabled", ChatFormatting.DARK_AQUA),
    LEGENDARY(4, "aeonspast:legendary", ChatFormatting.AQUA),
    MYTHIC(5, "aeonspast:mythic", ChatFormatting.GOLD),
    EMBLEM(7, "aeonspast:EMBLEM", ChatFormatting.GRAY);

    private static final ItemRarity[] ITEM_RARITIES = Arrays.stream(values()).sorted(Comparator.comparingInt(ItemRarity::getId)).toArray((p_41067_) -> {
        return new ItemRarity[p_41067_];
    });


    private final String unlocalizedName;

    private final int id;
    private final ChatFormatting chatFormat;

    ItemRarity(int id, String unlocalizedName, ChatFormatting chatFormat) {
        this.id = id;
        this.unlocalizedName = unlocalizedName;
        this.chatFormat = chatFormat;
    }

    public static ItemRarity getById(int id) {

        return ITEM_RARITIES[id];
    }

    public static ItemRarity byUnlocalizedName(String name) {


        for (ItemRarity rarity : ITEM_RARITIES) {

            if (rarity.unlocalizedName.equalsIgnoreCase(name)) {
                return rarity;
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

    public ChatFormatting getChatFormat() {
        return chatFormat;
    }


}
