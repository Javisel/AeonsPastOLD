package com.javisel.aeonspast.common.events;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerLevelUpEvent extends PlayerEvent {

    private final int fromLevel;
    private final int toLevel;


    public PlayerLevelUpEvent(Player player, int fromLevel, int toLevel) {
        super(player);

        this.fromLevel = fromLevel;
        this.toLevel = toLevel;

    }
}
