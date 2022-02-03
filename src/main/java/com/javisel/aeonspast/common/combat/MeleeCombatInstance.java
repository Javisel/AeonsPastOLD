package com.javisel.aeonspast.common.combat;

import com.javisel.aeonspast.common.combat.APEntityDamageSource;
import com.javisel.aeonspast.common.combat.CombatEngine;
import com.javisel.aeonspast.common.combat.DamageEngine;
import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.events.EventFactory;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;

public class MeleeCombatInstance {
    
    private DamageInstance instance;
    private LivingEntity attacker;
    private LivingEntity victim;
    private ItemStack weapon;

    public MeleeCombatInstance(LivingEntity attacker, LivingEntity victim, ItemStack weapon) {
        this.attacker = attacker;
        this.victim = victim;
        this.weapon = weapon;
        attemptCombat();
    }
    
    public void attemptCombat() {
        

         if (onMeleePreHit()) {
             onMeleeHit();
            
        }
        else {
         }
        
    }
    
    
    


    public boolean onMeleePreHit() {

        boolean result = true;

        if (!EventFactory.onMeleePreHit(attacker, victim, weapon)) {

            instance = DamageEngine.calculateWeaponDamage(attacker, weapon, true);


            ArrayList<ItemStack> attackerItems = ItemEngine.getAllAppicableItems(attacker);
            ArrayList<ItemStack> victimItems = ItemEngine.getAllAppicableItems(victim);


            attackerItems.add(weapon);
            for (ItemStack attackerStack : attackerItems) {


                if (ItemEngine.isItemInitialized(attackerStack)) {


                    for (ItemProperty property : ItemEngine.getItemProperties(attackerStack)) {


                        result = property.onPreHitEntity(attacker, victim, instance, weapon);


                    }

                }


            }


            for (ItemStack victimStack : victimItems) {


                if (ItemEngine.isItemInitialized(victimStack)) {


                    for (ItemProperty property : ItemEngine.getItemProperties(victimStack)) {


                        result = property.onPreHitEntity(attacker, victim, instance, weapon);


                    }

                }


            }


        } else {
            result = false;
        }


        return result;

    }
    public void onMeleeHit() {


        SoundEvent event = SoundEvents.PLAYER_ATTACK_STRONG;


        if (CombatEngine.attemptCriticalHit(attacker)) {

            CombatEngine.onCrit(attacker,victim,instance);
            event = SoundEvents.PLAYER_ATTACK_CRIT;

        }


        Level level = victim.getLevel();





        double postMigitationsAmount = DamageEngine.getMitigatedDamage(victim,instance);
        APEntityDamageSource damageSource = new APEntityDamageSource(attacker instanceof Player ? "player" : "mob", instance,attacker);




        System.out.println("Pre: " + victim.getHealth() + "/" + victim.getMaxHealth());
        victim.hurt(damageSource, (float) damageSource.getInstance().getAmount());
        System.out.println("Post: " + victim.getHealth() + "/" + victim.getMaxHealth());




        if (postMigitationsAmount / victim.getMaxHealth() <=0.20) {
            event=SoundEvents.PLAYER_ATTACK_WEAK;
        }


       SoundSource soundSource;

        if (attacker instanceof Player) {
            soundSource=SoundSource.PLAYERS;
        }

        else {
           soundSource= SoundSource.HOSTILE;
        }
        level.playSound(null,victim,event,  soundSource,1,1);

         instance.amount=postMigitationsAmount;
        instance.hasBeenMitigated=true;



        EventFactory.onMeleeHit(attacker, victim,  damageSource,  weapon) ;

             DamageInstance weaponInstance = DamageEngine.calculateWeaponDamage(attacker, weapon, true);


            ArrayList<ItemStack> attackerItems = ItemEngine.getAllAppicableItems(attacker);
            ArrayList<ItemStack> victimItems = ItemEngine.getAllAppicableItems(victim);




            //TODO item breaking


             for (ItemStack attackerStack : attackerItems) {


                if (ItemEngine.isItemInitialized(attackerStack)) {


                    for (ItemProperty property : ItemEngine.getItemProperties(attackerStack)) {


                        property.onHitEntity(attacker, victim, weaponInstance, weapon);


                    }

                }


            }


            for (ItemStack victimStack : victimItems) {


                if (ItemEngine.isItemInitialized(victimStack)) {


                    for (ItemProperty property : ItemEngine.getItemProperties(victimStack)) {


                        property.onHitEntity(attacker, victim, weaponInstance, weapon);


                    }

                }


            }







        CombatEngine.applyLifesteal(attacker,victim,instance);


    }









}
