package com.javisel.aeonspast.common.events;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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


}
