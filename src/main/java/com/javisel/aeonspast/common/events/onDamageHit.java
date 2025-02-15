package com.javisel.aeonspast.common.events;

import com.javisel.aeonspast.common.combat.damage.sources.APDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class onDamageHit extends LivingDamageEvent {


    private final LivingEntity attacker;


    public onDamageHit(LivingEntity victim, APDamageSource source, LivingEntity attacker) {
        super(victim, source, (float) source.instance.getPreMitigatedValue());
        this.attacker = attacker;
    }


}
