package com.javisel.aeonspast.common.events;

import jdk.jfr.Event;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.awt.*;
import java.awt.event.ItemEvent;

public class SwingItemEvent extends LivingEvent {


    ItemStack stack;


    public SwingItemEvent(LivingEntity entity, ItemStack stack) {
        super(entity);
        this.stack=stack;
    }


    /**
     * Constructs an {@code ItemEvent} object.
     * <p> This method throws an
     * {@code IllegalArgumentException} if {@code source}
     * is {@code null}.
     *
     * @param source      The {@code ItemSelectable} object
     *                    that originated the event
     * @param id          The integer that identifies the event type.
     *                    For information on allowable values, see
     *                    the class description for {@link ItemEvent}
     * @param item        An object -- the item affected by the event
     * @param stateChange An integer that indicates whether the item was
     *                    selected or deselected.
     *                    For information on allowable values, see
     *                    the class description for {@link ItemEvent}
     * @throws IllegalArgumentException if {@code source} is null
     * @see #getItemSelectable()
     * @see #getID()
     * @see #getStateChange()
     */






}
