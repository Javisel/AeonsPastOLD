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



        return  CombatEngine.cycleAllPreHitEffects(attacker,victim,source);

    }


    public boolean onHit() {





        CombatEngine.cycleAllHitEffects(attacker,victim,source);





        return true;


    }


}
