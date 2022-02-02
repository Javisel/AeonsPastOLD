package com.javisel.aeonspast.common.events;

import com.javisel.aeonspast.common.combat.APEntityDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import oshi.util.platform.unix.openbsd.FstatUtil;

public class EventFactory {


    public static float onLifesteal(LivingEntity attacker, LivingEntity victim, float amount) {
        LifestealEvent lifestealEvent = new LifestealEvent(attacker, victim, amount);
        return (MinecraftForge.EVENT_BUS.post(lifestealEvent) ? 0 : lifestealEvent.getAmount());
    }

    public static PlayerLevelUpEvent onPlayerLevelUp(Player player, int fromLevel, int toLevel) {

        PlayerLevelUpEvent playerLevelUpEvent = new PlayerLevelUpEvent(player, fromLevel, toLevel);


        MinecraftForge.EVENT_BUS.post(playerLevelUpEvent);


        return playerLevelUpEvent;


    }


    public static boolean onSwingItem(LivingEntity attacker,  ItemStack stack) {

        SwingItemEvent swingItemEvent = new SwingItemEvent(attacker,stack);

       return   MinecraftForge.EVENT_BUS.post(swingItemEvent);


    }


    public static  boolean onMeleePreHit(LivingEntity attacker, LivingEntity victim, ItemStack weapon) {


        MeleePreHitEvent preHitEvent = new MeleePreHitEvent(attacker,victim,weapon);

        return  MinecraftForge.EVENT_BUS.post(preHitEvent);

    }

    public static boolean onMeleeHit(LivingEntity attacker, LivingEntity victim, APEntityDamageSource source, ItemStack weapon) {

        MeleeHitEvent directHitEvent = new MeleeHitEvent(victim, source, attacker, weapon);

      return  MinecraftForge.EVENT_BUS.post(directHitEvent);


    }


}
