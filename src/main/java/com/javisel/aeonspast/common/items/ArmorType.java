package com.javisel.aeonspast.common.items;

import net.minecraft.world.entity.EquipmentSlot;

public enum  ArmorType {


    HELMET("aeonspast:helmet",0, EquipmentSlot.HEAD),
    CHESTPLATE("aeonspast:chestplate",1, EquipmentSlot.CHEST),
        LEGGINGS("aeonspast:leggings",2, EquipmentSlot.LEGS),
        BOOTS("aeonspast:boots",3, EquipmentSlot.FEET);




private final String unlocalizedName;
private final String descriptionKey;
private final int id;
private final EquipmentSlot equipmentSlot;


        ArmorType(String unlocalizedName, int id, EquipmentSlot equipmentSlot) {

        this.unlocalizedName = unlocalizedName;
        this.id = id;
        this.descriptionKey = unlocalizedName + ".desc";


            this.equipmentSlot = equipmentSlot;
        }

    public EquipmentSlot getEquipmentSlot() {
        return equipmentSlot;
    }
}
