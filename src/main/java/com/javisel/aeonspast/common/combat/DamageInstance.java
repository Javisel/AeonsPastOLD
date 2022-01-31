package com.javisel.aeonspast.common.combat;

import com.javisel.aeonspast.common.combat.damagetypes.APDamageSubType;
import com.javisel.aeonspast.common.spell.Spell;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;

public class DamageInstance {


    public boolean doesProcSpellEffects = false;
    public boolean doesProcWeaponHitEffects = false;
    public boolean doesProcTrinketEffects = false;
    public boolean doesProcInventoryItemEffects = false;
    public boolean isCritical = false;
    public boolean isSpecial = false;
    public boolean isArea = false;
    public boolean isOverTime = false;
    public boolean canCritical = false;
    public float procPower = 1;
    public Object damageDevice;
    protected DamageSource vanillaDamageSource;
    protected APDamageSubType damageType;
    protected float amount;

    public DamageInstance(APDamageSubType damageTypes, float amount, boolean doesProcSpellEffects, boolean doesProcWeaponHitEffects, boolean doesProcTrinketEffects, boolean doesProcInventoryItemEffects, boolean isCritical, boolean isSpecial, boolean isArea, int procPower) {
        this.amount = amount;
        this.damageType = damageTypes;
        this.doesProcSpellEffects = doesProcSpellEffects;
        this.doesProcWeaponHitEffects = doesProcWeaponHitEffects;
        this.doesProcTrinketEffects = doesProcTrinketEffects;
        this.doesProcInventoryItemEffects = doesProcInventoryItemEffects;
        this.isCritical = isCritical;
        this.isSpecial = isSpecial;
        this.isArea = isArea;
        this.procPower = procPower;
    }


    public DamageInstance(APDamageSubType damageType, float amount) {
        this.damageType = damageType;
        this.amount = amount;


    }

    //Damage applied by "On-Hit" effects to prevent Infinite Looping crashes.
    public static DamageInstance genericProcDamage(APDamageSubType damageType, float amount) {


        return new DamageInstance(damageType, amount);
    }

    public static DamageInstance genericSpellDamage(Spell device, APDamageSubType subType, float amount, boolean isArea) {


        DamageInstance damageInstance = new DamageInstance(subType, amount);
        damageInstance.doesProcSpellEffects = true;
        damageInstance.procPower = 1;
        damageInstance.isArea = isArea;
        damageInstance.damageDevice = device;
        return damageInstance;

    }

    public static DamageInstance genericWeaponDamage(ItemStack device, APDamageSubType subType, float amount, boolean isArea, boolean isCritical) {


        DamageInstance damageInstance = new DamageInstance(subType, amount);
        damageInstance.doesProcWeaponHitEffects = true;
        damageInstance.procPower = 1;
        damageInstance.isArea = isArea;
        damageInstance.isCritical = isCritical;
        damageInstance.damageDevice = device;
        return damageInstance;

    }

    public static DamageInstance penaltyDamage(  float amount) {


        DamageInstance damageInstance = new DamageInstance(APDamageSubType.PENALTY, amount);


return  damageInstance;
    }




    public APDamageSubType getDamageType() {
        return damageType;
    }

    public DamageSource getVanillaDamageSource() {
        return vanillaDamageSource;
    }

    public float getAmount() {
        return amount;
    }

    public boolean isDoesProcSpellEffects() {
        return doesProcSpellEffects;
    }

    public boolean isDoesProcWeaponHitEffects() {
        return doesProcWeaponHitEffects;
    }

    public boolean isDoesProcTrinketEffects() {
        return doesProcTrinketEffects;
    }

    public boolean isDoesProcInventoryItemEffects() {
        return doesProcInventoryItemEffects;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public boolean isSpecial() {
        return isSpecial;
    }

    public boolean isArea() {
        return isArea;
    }

    public float getProcPower() {
        return procPower;
    }


}
