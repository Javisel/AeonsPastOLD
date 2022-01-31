package com.javisel.aeonspast.common.combat;

import com.javisel.aeonspast.common.events.EventFactory;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.items.properties.WeaponProperty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;

public class CombatEngine {





    public void swingWeapon(LivingEntity attacker, ItemStack stack) {


       ArrayList<ItemProperty> properties = ItemEngine.getItemProperties(stack);

       if (EventFactory.onSwingItem(attacker,stack)) {


           if (!properties.isEmpty()) {


               for (ItemProperty property : properties) {


                   if (property instanceof WeaponProperty) {

                       WeaponProperty weaponProperty = (WeaponProperty) property;


                     weaponProperty.onSwingItem(attacker, stack);


                   }


               }


           }






       }





    }


    public boolean onMeleeHit(LivingEntity attacker, LivingEntity livingEntity, ItemStack stack) {


        return false;

    }


    public boolean onDamageSet(APDamageSource apDamagesource, LivingEntity victim) {



        return false;
    }

    public void doDamageCalculations(APDamageSource damageSource, LivingEntity victim) {



    }

    public void onDamagePostApplication(APDamageSource damageSource, LivingEntity livingEntity) {




    }




    public void onCrit(LivingEntity attacker, LivingEntity livingEntity,DamageInstance criticalInstance) {



    }




    public void fireWeapon(LivingEntity attacker, ItemStack stack) {



    }


    public void onProjectileHit(LivingEntity attacker, LivingEntity victim, Projectile projectile) {






    }









}
