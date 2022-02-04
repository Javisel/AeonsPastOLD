package com.javisel.aeonspast.common.combat;

import com.javisel.aeonspast.common.events.EventFactory;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;

public class CombatInstance {

    private final LivingEntity attacker;
    private final LivingEntity victim;
    public APDamageSource source;


    //CombatInstance involving a Weapon.
    public CombatInstance(LivingEntity attacker, LivingEntity victim, ItemStack weapon) {
        this.attacker = attacker;
        this.victim = victim;
        DamageInstance instance = DamageEngine.calculateWeaponDamage(attacker, weapon, true);

        source = new APDirectEntityDamageSource(attacker instanceof Player ? "player" : "mob", instance, attacker);


        if (onPreHit()) {
            onHit();
        }
    }


    public boolean onPreHit() {




        if (EventFactory.onDamagePreHit(attacker, victim, source.instance)) {

            return false;

        }


        if (source.instance.damageDevice instanceof ItemStack) {

            ItemStack weapon = (ItemStack) source.instance.damageDevice;


            for (ItemProperty property : ItemEngine.getItemProperties(weapon)) {


                if (!property.onPreHitEntityInHand(attacker, victim, source.instance, weapon)) {

                    return false;
                }


            }

        }

        ArrayList<ItemStack> attackerItems = ItemEngine.getAllAppicableItems(attacker);


        for (ItemStack attackerStack : attackerItems) {


            for (ItemProperty property : ItemEngine.getItemProperties(attackerStack)) {
                if (!property.onPreHitEntity(attacker, victim, source.instance)) {

                    return false;
                }


            }


        }


        ArrayList<ItemStack> victimItems = ItemEngine.getAllAppicableItems(victim);


        for (ItemStack victimStack : victimItems) {


            for (ItemProperty property : ItemEngine.getItemProperties(victimStack)) {
                if (!property.onPreHitEntity(attacker, victim, source.instance)) {

                    return false;
                }


            }


        }

        return true;

    }


    public boolean onHit() {



        if (EventFactory.onDamageHit(attacker, victim, source)) {

            return false;

        }
        victim.hurt(source, (float) source.getInstance().getPreMitigationsAmount());


        if (source.instance.damageDevice instanceof ItemStack) {

            ItemStack weapon = (ItemStack) source.instance.damageDevice;


            for (ItemProperty property : ItemEngine.getItemProperties(weapon)) {



                if (!property.onHitEntityInHand(attacker, victim, source.instance, weapon)) {

                    return false;
                }




            }

        }

        ArrayList<ItemStack> attackerItems = ItemEngine.getAllAppicableItems(attacker);


        for (ItemStack attackerStack : attackerItems) {


            for (ItemProperty property : ItemEngine.getItemProperties(attackerStack)) {
                if (!property.onHitEntity(attacker, victim, source.instance)) {
                    return false;
                }


            }


        }


        ArrayList<ItemStack> victimItems = ItemEngine.getAllAppicableItems(victim);


        for (ItemStack victimStack : victimItems) {


            for (ItemProperty property : ItemEngine.getItemProperties(victimStack)) {
                if (!property.onHitEntity(attacker, victim, source.instance)) {

                    return false;
                }


            }


        }

        return true;


    }


}
