package com.javisel.aeonspast.common.combat;

import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.utilities.StringKeys;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;

import static com.javisel.aeonspast.utilities.StringKeys.*;

public class DamageInstance {


    public boolean doesProcSpellEffects = false;
    public boolean doesProcWeaponHitEffects = false;
    public boolean doesProcTrinketEffects = true;
    public boolean doesProcInventoryItemEffects = true;
    public boolean doesProcStatusEffect = false;
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
    public DamageTypes damage_type;
    public double preMitigationsAmount;
    public double postMitigationsAmount = 0;
    public boolean cancel = false;
    public boolean isMagic = false;
    public DamageInstance(DamageTypes damageTypes, double amount, boolean doesProcSpellEffects, boolean doesProcWeaponHitEffects, boolean doesProcTrinketEffects, boolean doesProcInventoryItemEffects, boolean isCritical, boolean isSpecial, boolean isArea, int procPower) {
        this.preMitigationsAmount = amount;
        this.damage_type = damageTypes;
        isMagic=damage_type.isMagical();
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

    public DamageInstance(DamageTypes damageType, double amount) {
        this.damage_type = damageType;
        isMagic=damage_type.isMagical();

        this.preMitigationsAmount = amount;
        this.doesProcWeaponHitEffects=false;
        this.doesProcInventoryItemEffects=false;
        this.doesProcSpellEffects=false;
        this.doesProcTrinketEffects=false;

    }

    public DamageInstance(DamageTypes damageType ) {
        this.damage_type = damageType;
        isMagic=damage_type.isMagical();

        this.doesProcWeaponHitEffects=false;
        this.doesProcInventoryItemEffects=false;
        this.doesProcSpellEffects=false;
        this.doesProcTrinketEffects=false;

    }
    //Damage applied by Weapons.

    public DamageInstance(ItemStack device, DamageTypes subType, double amount, boolean isArea, boolean isCritical, boolean isMelee) {



        this.damage_type=subType;
        this.preMitigationsAmount =amount;
        doesProcWeaponHitEffects = true;
        this.canCritical=true;
        this.isArea = isArea;
        this.isCritical = isCritical;
        damageDevice = device;
       this.isMelee = isMelee;

    }


    /*
    public DamageInstance fromNBT(CompoundTag tag){




    }


     */




    private static final String CAN_CRIT = "can_crit";
    private static final String IS_SPECIAL = "is_special";
    private static final String IS_CRIT = "is_crit";
    private static final String IS_AREA = "is_area";
    private static final String IS_OVER_TIME = "is_over_time";
    private static final String IS_MITIGATED = "is_mitigated";
    private static final String IS_MELEE = "is_melee";
    private static final String PROC_POWER = "proc_power";
    private static final String CRIT_POWER = "crit_power";
    private static final String DAMAGE_DEVICE = "damage_device";

    public CompoundTag toNBT() {

        CompoundTag tag = new CompoundTag();

        tag.putBoolean(SPELL,doesProcSpellEffects);
        tag.putBoolean(WEAPON,doesProcWeaponHitEffects);
        tag.putBoolean(TRINKET,doesProcTrinketEffects);
        tag.putBoolean(INVENTORY,doesProcInventoryItemEffects);
        tag.putBoolean(CAN_CRIT,canCritical);
        tag.putBoolean(IS_SPECIAL,isSpecial);
        tag.putBoolean(IS_CRIT,isCritical);
        tag.putBoolean(IS_AREA,isArea);
        tag.putBoolean(IS_OVER_TIME,isOverTime);
        tag.putBoolean(IS_MITIGATED,isMitigated);
        tag.putBoolean(IS_MELEE,isMelee);
        tag.putDouble(PROC_POWER,procPower);
        tag.putDouble(CRIT_POWER,critPower);




        if (damageDevice instanceof Spell) {

            tag.putString(DAMAGE_DEVICE, ((Spell) damageDevice).getRegistryName().toString());

        }

        if (damageDevice instanceof ItemStack) {

            tag.put(DAMAGE_DEVICE,  ((ItemStack) damageDevice).serializeNBT());

        }


        tag.putString(StringKeys.DAMAGE_TYPE,damage_type.getUnlocalizedName());

        tag.putString(StringKeys.DAMAGE_TYPE,damage_type.getUnlocalizedName());



 return  tag;
    }








    public DamageTypes getDamage_type() {
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
