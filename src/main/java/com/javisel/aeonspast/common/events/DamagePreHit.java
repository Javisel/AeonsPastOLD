package com.javisel.aeonspast.common.events;

import com.javisel.aeonspast.common.combat.DamageInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;

public class DamagePreHit extends LivingEvent {


    public final LivingEntity attacker;
    public final DamageInstance instance;

    public DamagePreHit(LivingEntity attacker, LivingEntity entity, DamageInstance instance) {
        super(entity);
        this.attacker = attacker;

        this.instance=instance;
    }




}
