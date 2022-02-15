package com.javisel.aeonspast.common.combat;

import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import com.javisel.aeonspast.common.combat.damagesource.APEntityDamageSource;
import com.javisel.aeonspast.common.effects.ComplexEffect;
import com.javisel.aeonspast.common.events.EventFactory;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

public class CombatInstance {

    public final LivingEntity attacker;
    public final LivingEntity victim;
    public APDamageSource source;


    //CombatInstance involving a Melee Weapon.
    public CombatInstance(LivingEntity attacker, LivingEntity victim  ) {
        this.attacker = attacker;
        this.victim = victim;
        DamageInstance instance = CombatEngine.calculateMeleeDamage(attacker, attacker.getMainHandItem());

        source = new APEntityDamageSource(attacker instanceof Player ? "player" : "mob", instance, attacker);


    }

    //CombatInstance involving a  Ranged Weapon.
    public CombatInstance(LivingEntity attacker, Projectile projectile, LivingEntity victim, float rangedPower) {
        this.attacker = attacker;
        this.victim = victim;
        DamageInstance instance = CombatEngine.calculateRangedDamage(attacker, attacker.getMainHandItem(), rangedPower );


        instance.procPower=rangedPower;
        source = new APEntityDamageSource(attacker instanceof Player ? "player" : "mob", instance, projectile,attacker);


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


        Collection<MobEffectInstance> effects = attacker.getActiveEffects();


        for (MobEffectInstance mobEffectInstance : effects) {

            if (mobEffectInstance.getEffect() instanceof ComplexEffect) {

                ComplexEffect effect = (ComplexEffect) mobEffectInstance.getEffect();

                effect.onpostHitEffect(attacker,victim,source);

            }


        }
        ArrayList<ItemStack> victimItems = ItemEngine.getAllAppicableItems(victim);


        for (ItemStack victimStack : victimItems) {


            for (ItemProperty property : ItemEngine.getItemProperties(victimStack)) {



                if (!property.onOwnerPreHit(attacker, victim, source.instance)) {

                    return false;
                }


            }


        }
        Collection<MobEffectInstance> victimActiveEffects = victim.getActiveEffects();


        for (MobEffectInstance mobEffectInstance : victimActiveEffects) {

            if (mobEffectInstance.getEffect() instanceof ComplexEffect) {

                ComplexEffect effect = (ComplexEffect) mobEffectInstance.getEffect();

                effect.onOwnerpostHitEffect(attacker,victim,source);

            }


        }
        return true;

    }


    public boolean onHit() {


        if (source.getInstance().canCritical && CombatEngine.attemptCriticalHit(attacker)) {


            CombatEngine.applyCrits(attacker, victim, source.instance);
        }


        if (EventFactory.onDamageHit(attacker, victim, source)) {

            return false;

        }
        victim.hurt(source, (float) source.getInstance().getPreMitigationsAmount());

         if (source.instance.damageDevice instanceof ItemStack) {

            ItemStack weapon = (ItemStack) source.instance.damageDevice;

            weapon.hurt(1, attacker.getRandom(), attacker instanceof ServerPlayer ? (ServerPlayer) attacker : null);


            if (attacker instanceof  Player) {
                Player player = (Player) attacker;
                weapon.hurtAndBreak(1, player, (p_150686_) -> {
                    p_150686_.broadcastBreakEvent(InteractionHand.MAIN_HAND);
                });

            }
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

        Collection<MobEffectInstance> attackerEffects = attacker.getActiveEffects();


        for (MobEffectInstance mobEffectInstance : attackerEffects) {

            if (mobEffectInstance.getEffect() instanceof ComplexEffect) {

                ComplexEffect effect = (ComplexEffect) mobEffectInstance.getEffect();

                effect.onHitEffect(attacker,victim,source);

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

        Collection<MobEffectInstance> victimActiveEffects = victim.getActiveEffects();


        for (MobEffectInstance mobEffectInstance : victimActiveEffects) {

            if (mobEffectInstance.getEffect() instanceof ComplexEffect) {

                ComplexEffect effect = (ComplexEffect) mobEffectInstance.getEffect();

                effect.onOwnerHitEffect(attacker,victim,source);

            }


        }
        return true;


    }


}
