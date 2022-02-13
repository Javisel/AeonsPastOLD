package com.javisel.aeonspast.common.combat;


import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.RegistryObject;

public enum DamageTypes {

    VOID("aeonspast:void", 0, null),
    ENDER("aeonspast:ender", 1, null),
    ARCANE("aeonspast:arcane", 2, null),
    IMPACT("aeonspast:impact", 3, null),
    PUNCTURE("aeonspast:puncture", 4, null),
    SLASH("aeonspast:slash",5, null),
    FIRE("aeonspast:fire",6, null),
    COLD("aeonspast:cold",7, null),
    ELECTRIC("aeonspast:electric",8, null),
    RADIANT("eaonspast:radiant",9, null),
    POISON("aeonspast:poison",10, null),
    WITHER("aeonspast:wither",11, null),
    ENVIRONMENTAL("aeonspast:environmental",12, null),
    BLEED("aeonspast:bleed",13, null),
    TRUE("aeonspast:true",14, null);

    private final String unlocalizedName;
    private final int id;
    private final RegistryObject<MobEffect> statusEffect;
    DamageTypes(String name, int id, RegistryObject<MobEffect> statusEffect) {


        this.unlocalizedName = name;
        this.id = id;

        this.statusEffect = statusEffect;
    }




    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public int getId() {
        return id;
    }

    public boolean isMagical() {


        return  this != SLASH && this !=PUNCTURE && this !=IMPACT && this !=BLEED;
    }
    public boolean isAbsolute() {


        return  this == VOID || this == TRUE;
    }

}
