package com.javisel.aeonspast.common.combat;


import com.javisel.aeonspast.common.registration.EffectRegistration;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.RegistryObject;

public enum DamageTypeEnum {

    VOID("aeonspast:void", 0 , 0x0000000),
    ENDER("aeonspast:ender", 1 , 0xB200FF),
    ARCANE("aeonspast:arcane", 2 , 0x4169e1),
    IMPACT("aeonspast:impact", 3,   0xFFA500),
    PUNCTURE("aeonspast:puncture", 4,  0xFFA500),
    SLASH("aeonspast:slash",5,  0x0FFA500),
    FIRE("aeonspast:fire",6 , 0xF73718),
    COLD("aeonspast:cold",7 , 0xd6ecef),
    ELECTRIC("aeonspast:electric",8 , 0xffff33),
    RADIANT("eaonspast:radiant",9 , 0xFFD700),
    POISON("aeonspast:poison",10 , 0x80b692),
    WITHER("aeonspast:wither",11 , 0x301934),
    ENVIRONMENTAL("aeonspast:environmental",12 , 0xFFFFFF),
    BLEED("aeonspast:bleed",13 , 0x660000),
    TRUE("aeonspast:true",14,   0xFFFFFF);

    private final String unlocalizedName;
    private final int id;
     private final int color;
    DamageTypeEnum(String name, int id,   int color) {


        this.unlocalizedName = name;
        this.id = id;

         this.color = color;
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


        return  this == VOID || this == TRUE || this ==BLEED;
    }


    

    public int getColor() {
        return color;
    }
}

