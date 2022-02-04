package com.javisel.aeonspast.common.combat;

import com.javisel.aeonspast.common.combat.damagetypes.APDamageSubType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;

public class DamageInstance {


    public boolean doesProcSpellEffects = false;
    public boolean doesProcWeaponHitEffects = false;
    public boolean doesProcTrinketEffects = true;
    public boolean doesProcInventoryItemEffects = true;
    public boolean isCritical = false;
    public boolean isSpecial = false;
    public boolean isArea = false;
    public boolean isOverTime = false;
    public boolean canCritical = false;
    public boolean isMitigated = false;
    public boolean isMelee = false;
    public double procPower = 1;
    public double critPower = 1;
    public Object damageDevice;
    public DamageSource vanillaDamageSource;
    public APDamageSubType damage_type;
    public double preMitigationsAmount;
    public double postMitigationsAmount = 0;
    public boolean cancel = false;
    public DamageInstance(APDamageSubType damageTypes, double amount, boolean doesProcSpellEffects, boolean doesProcWeaponHitEffects, boolean doesProcTrinketEffects, boolean doesProcInventoryItemEffects, boolean isCritical, boolean isSpecial, boolean isArea, int procPower) {
        this.preMitigationsAmount = amount;
        this.damage_type = damageTypes;
        this.doesProcSpellEffects = doesProcSpellEffects;
        this.doesProcWeaponHitEffects = doesProcWeaponHitEffects;
        this.doesProcTrinketEffects = doesProcTrinketEffects;
        this.doesProcInventoryItemEffects = doesProcInventoryItemEffects;
        this.isCritical = isCritical;
        this.isSpecial = isSpecial;
        this.isArea = isArea;
        this.procPower = procPower;
    }

    //Damage applied by "On-Hit" effects to prevent Infinite Looping crashes.

    public DamageInstance(APDamageSubType damageType, double amount) {
        this.damage_type = damageType;
        this.preMitigationsAmount = amount;
        this.doesProcWeaponHitEffects=false;
        this.doesProcInventoryItemEffects=false;
        this.doesProcSpellEffects=false;
        this.doesProcTrinketEffects=false;

    }


    //Damage applied by Weapons.

    public DamageInstance(ItemStack device, APDamageSubType subType, double amount, boolean isArea, boolean isCritical, boolean isMelee) {



        this.damage_type=subType;
        this.preMitigationsAmount =amount;
        doesProcWeaponHitEffects = true;
        procPower = 1;
        this.isArea = isArea;
        this.isCritical = isCritical;
        damageDevice = device;
       this.isMelee = isMelee;

    }




    public APDamageSubType getDamage_type() {
        return damage_type;
    }

    public DamageSource getVanillaDamageSource() {
        return vanillaDamageSource;
    }

    public double getPreMitigationsAmount() {
        return preMitigationsAmount;
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

    public double getProcPower() {
        return procPower;
    }

    public void setMitigateDamage(float amount) {

        postMitigationsAmount = amount;
        isMitigated = true;
    }

}
