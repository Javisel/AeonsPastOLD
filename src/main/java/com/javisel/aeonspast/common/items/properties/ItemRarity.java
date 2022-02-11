package com.javisel.aeonspast.common.items.properties;

import net.minecraft.ChatFormatting;
import org.apache.logging.log4j.core.appender.routing.PurgePolicy;

public enum  ItemRarity  {


    COMMON( 0, "aeonspast:common", ChatFormatting.GRAY),
    RARE( 0, "aeonspast:rare", ChatFormatting.DARK_BLUE),
    EPIC( 0, "aeonspast:epic", ChatFormatting.BLUE),
    FABLED( 0, "aeonspast:fabled", ChatFormatting.DARK_AQUA),
    LEGENDARY( 0, "aeonspast:legendary", ChatFormatting.AQUA),
    MYTHIC( 0, "aeonspast:mythic", ChatFormatting.GOLD);



    private final String unlocalizedName;

    private final int id;
    private final ChatFormatting chatFormat;

    ItemRarity(int id, String unlocalizedName, ChatFormatting chatFormat) {
        this.id = id;
        this.unlocalizedName=unlocalizedName;
        this.chatFormat = chatFormat;
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
