package com.javisel.aeonspast.common.events;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;

public class MeleePreHitEvent extends LivingEvent {


    public final LivingEntity attacker;
    public final ItemStack weapon;

    public MeleePreHitEvent(LivingEntity attacker, LivingEntity entity, ItemStack weapon) {
        super(entity);
        this.attacker=attacker;

        this.weapon=weapon;
    }
}
