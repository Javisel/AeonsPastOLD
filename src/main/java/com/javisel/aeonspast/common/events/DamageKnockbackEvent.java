package com.javisel.aeonspast.common.events;

import com.javisel.aeonspast.common.combat.DamageInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;

public class DamageKnockbackEvent extends LivingKnockBackEvent {
    




    private DamageInstance instance;
    public DamageKnockbackEvent(DamageInstance instance,LivingEntity target, float strength, double ratioX, double ratioZ) {
        super(target, strength, ratioX, ratioZ);

        this.instance=instance;

        if (!instance.doesKnockback) {

            setCanceled(true);
        }

    }
}
