package com.javisel.aeonspast.common.combat;

import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import com.javisel.aeonspast.common.combat.damagesource.APEntityDamageSource;
import com.javisel.aeonspast.common.events.EventFactory;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;

public class CombatInstance {

    public final LivingEntity attacker;
    public final LivingEntity victim;
    public APDamageSource source;


    //CombatInstance involving a Melee Weapon.
    public CombatInstance(LivingEntity attacker, LivingEntity victim, ItemStack weapon) {
        this.attacker = attacker;
        this.victim = victim;
        DamageInstance instance = CombatEngine.calculateWeaponDamage(attacker, weapon, true);

        source = new APEntityDamageSource(attacker instanceof Player ? "player" : "mob", instance, attacker);


    }

    //CombatInstance involving a  Ranged Weapon.
    public CombatInstance(LivingEntity attacker, LivingEntity victim) {
        this.attacker = attacker;
        this.victim = victim;
        DamageInstance instance = CombatEngine.calculateWeaponDamage(attacker, attacker.getMainHandItem(), false);

        source = new APEntityDamageSource(attacker instanceof Player ? "player" : "mob", instance, attacker);


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


                //TODO When the owner is hit

                if (!property.onOwnerPreHit(attacker, victim, source.instance)) {

                    return false;
                }


            }


        }

        return true;

    }


    public boolean onHit() {


        if (source.getInstance().canCritical && CombatEngine.attemptCriticalHit(attacker)) {


            CombatEngine.onCrit(attacker, victim, source.instance);
        }


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


                if (!property.onOwnerHit(attacker, victim, source.instance)) {

                    return false;
                }


            }


        }

        return true;


    }


}
