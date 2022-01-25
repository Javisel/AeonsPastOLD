package com.javisel.aeonspast.common.combat;

import com.javisel.aeonspast.common.combat.damagetypes.APDamageSubType;
import net.minecraft.world.damagesource.DamageSource;

public class APDamageInstance {

    protected DamageSource vanillaDamageSource;
    protected APDamageSubType damageType;
    protected float amount;
    protected   boolean doesProcSpellEffects = false;
    protected   boolean doesProcWeaponHitEffects = false;
    protected   boolean doesProcTrinketEffects = false;
    protected   boolean doesProcInventoryItemEffects = false;
    protected   boolean isCritical = false;
    protected   boolean isSpecial = false;
    protected   boolean isArea = false;
    protected   float procPower = 1;


    public APDamageInstance(String p_19333_, APDamageSubType damageTypes, float amount, boolean doesProcSpellEffects, boolean doesProcWeaponHitEffects, boolean doesProcTrinketEffects, boolean doesProcInventoryItemEffects, boolean isCritical, boolean isSpecial, boolean isArea, int procPower) {
       this.amount=amount;
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
    
    public APDamageInstance() {
        
        
    }

    public APDamageInstance(APDamageSubType damageType, float amount) {
        this.damageType=damageType;
        this.amount=amount;


    }

    public APDamageSubType getDamageType() {
        return damageType;
    }
    
    
   public static class Builder {
        
        
        APDamageInstance instance;
        
        public Builder() {
            
            instance = new APDamageInstance();
            
            
        }
        
        
        public APDamageInstance isCritical() {
            
            instance.isCritical=true;
            return  instance;
        }
        
        
        
        
        
        
   } 
    
    
    
    
    
    
}
