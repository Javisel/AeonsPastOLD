package com.javisel.aeonspast.common.combat;

import com.javisel.aeonspast.common.combat.damagetypes.APDamageSubType;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class DamageEngine {


    private static double getDamagePostMitigations(double armor, double damage) {

        double mitigations = 1;
        if (armor < 0) {


            mitigations =  (2 - (armor / (100 + armor)));
        } else if (armor == 0) {
            return damage;
        } else if (armor > 0) {


            mitigations =   (armor / (100 + armor));
        }


        return mitigations * damage;


    }


    public static DamageInstance calculateWeaponDamage(LivingEntity attacker, ItemStack weapon, boolean isMelee) {


        double baseDamage = attacker.getAttributeValue(AttributeRegistration.WEAPON_POWER.get());
        double physicalPower = attacker.getAttributeValue(AttributeRegistration.PHYSICAL_POWER.get());
        double rangeBonus = isMelee ? attacker.getAttributeValue(AttributeRegistration.MELEE_POWER.get()) : attacker.getAttributeValue(AttributeRegistration.RANGED_POWER.get());

        double total = (baseDamage + physicalPower) * (1 + rangeBonus);

        total *= attacker.getAttributeValue(AttributeRegistration.DAMAGE_OUTPUT.get());


        return DamageInstance.genericWeaponDamage(weapon, APDamageSubType.PHYSICAL, total, false, false);


    }

    public static double getMitigatedDamage(LivingEntity victim, DamageInstance instance) {

        double baseamount = instance.getAmount();
        double armor = 0;
        double  damageMod = 0;
        damageMod+= victim.getAttributeValue(AttributeRegistration.DAMAGE_INTAKE.get());
        if (instance.getDamageType()==APDamageSubType.PHYSICAL) {


            armor = victim.getAttribute(AttributeRegistration.ARMOR.get()).getValue();
            armor *= 1 +victim.getAttribute(AttributeRegistration.ARMOR_TOUGHNESS.get()).getValue();


            damageMod-= victim.getAttributeValue(AttributeRegistration.PHYSICAL_MITIGATIONS.get());



        }

        if (instance.getDamageType()==APDamageSubType.MAGIC) {


            armor = victim.getAttribute(AttributeRegistration.MAGIC_RESISTANCE.get()).getValue();

            damageMod-= victim.getAttributeValue(AttributeRegistration.MAGICAL_MITIGATIONS.get());

        }


        baseamount=getDamagePostMitigations(armor,baseamount);

        baseamount*=damageMod;





return  baseamount;



    }


    public static double getCriticalDamageBonus(LivingEntity entity, DamageInstance instance) {

        double critDamage = entity.getAttributeValue(AttributeRegistration.CRITICAL_DAMAGE.get());

         double base =instance.getAmount();

         base*=critDamage;

         base*=instance.critPower;




        return base;
    }






}
