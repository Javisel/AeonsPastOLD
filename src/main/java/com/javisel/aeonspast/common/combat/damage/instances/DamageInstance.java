package com.javisel.aeonspast.common.combat.damage.instances;

import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static com.javisel.aeonspast.utilities.StringKeys.*;

public class DamageInstance {





    private Object damageDevice;
     private  double baseAmount;
    private double mitigatedAmount = 0;
    private final DamageTypeEnum damageTypeEnum;


    ArrayList<DamageModifier> modifiers = new ArrayList<>();

    public ArrayList<DamageFlags> flags = new ArrayList<>();




    private DamageInstance(DamageTypeEnum type, double amount) {

        this.baseAmount =amount;
        this.damageTypeEnum=type;

    }

    private DamageInstance(DamageTypeEnum type, double amount, DamageFlags...flags) {

        this.baseAmount =amount;
        this.damageTypeEnum=type;
        this.flags.addAll(List.of(flags));

    }

    public DamageInstance(DamageTypeEnum type) {
        this.damageTypeEnum=type;
    }


    public static DamageInstance getGenericProcInstance(DamageTypeEnum damageTypeEnum, double baseAmount) {

        return new DamageInstance(damageTypeEnum,baseAmount);


    }

    public static DamageInstance genericWeaponDamage(DamageTypeEnum damageTypeEnum, double baseAmount, ItemStack weapon, boolean isMelee) {



            DamageInstance instance  =new DamageInstance(damageTypeEnum,baseAmount);

            instance.damageDevice=weapon;
            if (isMelee) {
                instance.flags.add(DamageFlags.IS_MELEE);

            }
            instance.setProcWeaponEffects();
        instance.setProcTrinketEffects();
        instance.setProcWeaponEffects();
        instance.setProcInventoryEffects();
        instance.setCanCritical();
        instance.setCanStatus();
            return  instance;

    }





    public CompoundTag toNBT() {

        CompoundTag tag = new CompoundTag();
        tag.putDouble(ORIGINAL_AMOUNT, baseAmount);
        tag.putString(DAMAGE_TYPE,damageTypeEnum.toString());
        if (isMitigated()) {

            tag.putDouble(MITIGATED_AMOUNT,mitigatedAmount);
        }

        if (damageDevice !=null) {

            CompoundTag devicetag = null;
            if (damageDevice instanceof SpellStack) {

                SpellStack stack = (SpellStack) damageDevice;

                devicetag = new CompoundTag();
                devicetag.putString(DAMAGE_DEVICE_TYPE,SPELL);
                devicetag.put(DAMAGE_DEVICE,stack.toNBT());


            }

            if (damageDevice instanceof ItemStack) {

                ItemStack stack = (ItemStack) damageDevice;

                devicetag = new CompoundTag();

                devicetag.putString(DAMAGE_DEVICE_TYPE,ITEM);

                CompoundTag saved = new CompoundTag();
                devicetag.put(DAMAGE_DEVICE,stack.save(saved));


            }

            if (devicetag !=null) {
                tag.put(DAMAGE_DEVICE,devicetag);
            }


        }

        CompoundTag flags = new CompoundTag();

        for (DamageFlags key : this.flags) {

            flags.putString(key.toString(),"");

        }


        tag.put(DAMAGE_FLAGS,flags);

        CompoundTag mods = new CompoundTag();

        for (DamageModifier modifier : modifiers) {

            mods.put(modifier.id.toString(),modifier.toCompoundTag());

        }
        tag.put(DAMAGE_MODIFIERS,mods);



        return tag;
    }


    public static DamageInstance fromNBT(CompoundTag tag) {

        DamageInstance instance = new DamageInstance(DamageTypeEnum.valueOf(tag.getString(DAMAGE_TYPE)),tag.getDouble(ORIGINAL_AMOUNT)   );

        CompoundTag flagtag = tag.getCompound(DAMAGE_FLAGS);

        for (String key : flagtag.getAllKeys()){


            instance.flags.add( DamageFlags.valueOf(key));

        }
        if (instance.isMitigated()) {

            instance.mitigatedAmount=tag.getDouble(MITIGATED_AMOUNT);


        }


        if (tag.contains(DAMAGE_DEVICE)) {

            CompoundTag deviceInfo = tag.getCompound(DAMAGE_DEVICE);
            String type = deviceInfo.getString(DAMAGE_DEVICE_TYPE);
            CompoundTag deviceData = deviceInfo.getCompound(DAMAGE_DEVICE);

            if (type.equalsIgnoreCase(SPELL)) {

                instance.damageDevice=SpellStack.readNBT(deviceData);


            }

            if (type.equalsIgnoreCase(ITEM)) {
                instance.damageDevice=ItemStack.of(deviceData);

            }



        }


        CompoundTag mods  = tag.getCompound(DAMAGE_MODIFIERS);


        for (String key : mods.getAllKeys()) {


            DamageModifier modifier = DamageModifier.fromNBT(mods.getCompound(key));

            instance.modifiers.add(modifier);


        }



        return  instance;

    }


    public boolean isMitigated() {

        return  flags.contains(DamageFlags.IS_MITIGATED);
    }

    public void setMitigateDamage(float amount) {

        mitigatedAmount=amount;
        flags.add(DamageFlags.IS_MITIGATED);
    }

    public boolean doesDeviceMatch(Object damageDevice) {


        if (this.damageDevice==null) {
            return  false;
        }



        if (!this.damageDevice.getClass().getTypeName().equalsIgnoreCase( damageDevice.getClass().getTypeName())) {
             return  false;
        }


        if (damageDevice instanceof SpellStack) {

         SpellStack stack = (SpellStack) damageDevice;
         SpellStack saved = (SpellStack) this.damageDevice;


        return stack.getSpellInstanceId() !=saved.getSpellInstanceId();





        }

        if (damageDevice instanceof ItemStack) {

            ItemStack stack = (ItemStack) damageDevice;
            ItemStack saved = (ItemStack) this.damageDevice;


            ItemEngine.doStacksMatch(stack,saved);




        }

        return  false;
    }


    public boolean doesProcWeaponHitEffects() {

        return  flags.contains(DamageFlags.CAN_PROC_WEAPON_EFFECTS);
    }


    public Object getDamageDevice() {
        return damageDevice;
    }


    public double getBaseAmount() {
        return baseAmount;
    }

    public double getMitigatedAmount() {
        return mitigatedAmount;
    }

    public DamageTypeEnum getDamageType() {
        return damageTypeEnum;
    }

    public ArrayList<DamageFlags> getFlags() {
        return flags;
    }


    public void setDamageDevice(Object damageDevice) {
        this.damageDevice = damageDevice;
    }

    public void setBaseAmount(double baseAmount) {
        this.baseAmount = baseAmount;
    }

    public void setMitigatedAmount(double mitigatedAmount) {
        this.mitigatedAmount = mitigatedAmount;
    }

    public void setFlags(ArrayList<DamageFlags> flags) {
        this.flags = flags;
    }

    public boolean isCritical() {

        return  flags.contains(DamageFlags.CRITICAL_STRIKE);
    }


    public void addCritical(DamageModifier criticalInstance){


        modifiers.add(criticalInstance);
        flags.add(DamageFlags.CRITICAL_STRIKE);
    }

    public int getCriticalInstanceCount() {


        int result = 0;


        for (DamageFlags flag : flags) {

            if (flag.equals(DamageFlags.CRITICAL_STRIKE)) {
                result++;
            }

        }


        return  result;


    }



    public boolean isStatus() {


        return  flags.contains(DamageFlags.IS_STATUS);
    }


    public void setStatus(){

        flags.add(DamageFlags.IS_STATUS);
    }



    public double getPreMitigatedValue() {


        double result;

        double flats = 0;
        double base_multipliers = 0;
        double total_multipliers = 1;

        for(DamageModifier modifier : modifiers) {



            switch (modifier.operation) {


                case ADDITON -> {
                    flats+=modifier.amount;
                }
                case MULTIPLE_BASE -> {

                    base_multipliers+=baseAmount* modifier.amount;
                }
                case MULTIPLE_TOTAL -> {

                    total_multipliers+=  modifier.amount;
                }
            }








        }





        result = (baseAmount + flats + base_multipliers) * total_multipliers;






        return  result;
    }


    public void addModifier(DamageModifier modifier) {


         modifiers.add(modifier);

    }

public boolean isArea() {

        return  flags.contains(DamageFlags.IS_AREA);
}


public  boolean isMagical() {


        return  damageTypeEnum.isMagical();
}

public  boolean canCritical() {


        return  flags.contains(DamageFlags.CAN_CRITICAL);
}

public boolean isCancelled() {

        return  flags.contains(DamageFlags.CANCELED);
}

public void cancel() {

        flags.add(DamageFlags.CANCELED);
}


public boolean isMelee() {

        return  flags.contains(DamageFlags.IS_MELEE);
}



public void setProcWeaponEffects() {

        flags.add(DamageFlags.CAN_PROC_WEAPON_EFFECTS);
}


public void setProcSpellEffects() {
        
        
        flags.add(DamageFlags.CAN_PROC_SPELL_EFFECTS);
}

public void setProcTrinketEffects() {
        
        flags.add(DamageFlags.CAN_PROC_TRINKET_EFFECTS);
}

    public void setProcArmorEffects() {

        flags.add(DamageFlags.CAN_PROC_ARMOR_ITEM_EFFECTS);
    }

    public void setProcInventoryEffects() {

        flags.add(DamageFlags.CAN_PROC_INVENTORY_ITEM_EFFECTS);
    }
    
   

    public void setCanCritical() {

        flags.add(DamageFlags.CAN_CRITICAL);
    }
    public void setCanStatus() {

        flags.add(DamageFlags.CAN_STATUS);
    }

    
 

}
