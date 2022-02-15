package com.javisel.aeonspast.common.combat;


import com.javisel.aeonspast.common.registration.EffectRegistration;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.RegistryObject;

public enum DamageTypeEnum {

    VOID("aeonspast:void", 0, null, 0x0000000),
    ENDER("aeonspast:ender", 1, null, 0xB200FF),
    ARCANE("aeonspast:arcane", 2, null, 0x4169e1),
    IMPACT("aeonspast:impact", 3, null, 0xFFA500),
    PUNCTURE("aeonspast:puncture", 4, null, 0xFFA500),
    SLASH("aeonspast:slash",5, null, 0x0FFA500),
    FIRE("aeonspast:fire",6, null, 0xF73718),
    COLD("aeonspast:cold",7, null, 0xd6ecef),
    ELECTRIC("aeonspast:electric",8, null, 0xffff33),
    RADIANT("eaonspast:radiant",9, null, 0xffd700),
    POISON("aeonspast:poison",10, null, 0x80b692),
    WITHER("aeonspast:wither",11, null, 0x301934),
    ENVIRONMENTAL("aeonspast:environmental",12, null, 0xFFFFFF),
    BLEED("aeonspast:bleed",13, EffectRegistration.BLEED, 0x660000),
    TRUE("aeonspast:true",14, null, 0xFFFFFF);

    private final String unlocalizedName;
    private final int id;
    private final RegistryObject<MobEffect> statusEffect;
    private final int color;
    DamageTypeEnum(String name, int id, RegistryObject<MobEffect> statusEffect, int color) {


        this.unlocalizedName = name;
        this.id = id;

         this.statusEffect = statusEffect;
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


        return  this == VOID || this == TRUE;
    }


    public MobEffect getStatusEffect() {
        return statusEffect.get();
    }

    public int getColor() {
        return color;
    }
}

