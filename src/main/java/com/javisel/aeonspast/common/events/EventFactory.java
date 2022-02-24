package com.javisel.aeonspast.common.events;

import com.javisel.aeonspast.common.combat.damage.instances.DamageInstance;
import com.javisel.aeonspast.common.combat.damage.sources.APDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

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


    public static boolean onSwingItem(LivingEntity attacker, ItemStack stack) {

        SwingItemEvent swingItemEvent = new SwingItemEvent(attacker, stack);

        return MinecraftForge.EVENT_BUS.post(swingItemEvent);


    }


    public static boolean onDamagePreHit(LivingEntity attacker, LivingEntity victim, DamageInstance instance) {


        DamagePreHit preHitEvent = new DamagePreHit(attacker, victim, instance);

        return MinecraftForge.EVENT_BUS.post(preHitEvent);

    }

    public static boolean onDamageHit(LivingEntity attacker, LivingEntity victim, APDamageSource source) {

        onDamageHit directHitEvent = new onDamageHit(victim, source, attacker);

        return MinecraftForge.EVENT_BUS.post(directHitEvent);


    }
}
