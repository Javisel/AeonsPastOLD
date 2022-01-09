package com.javisel.aeonspast.common.items.itemproperties;


import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public enum EnumTiers implements Tier {
    ENDER(4, 2031, 9.0F, 4.0F, 15, null), REDKAMA(4, 100, 4, 4, 15, null);

    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Ingredient repairIngredient;

    EnumTiers(int level, int uses, float speed, float damage, int enchantmentValue, Ingredient repairIngredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }

}

