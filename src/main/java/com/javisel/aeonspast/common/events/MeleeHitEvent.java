package com.javisel.aeonspast.common.events;

import com.javisel.aeonspast.common.combat.APDamageSource;
import com.javisel.aeonspast.common.combat.APEntityDamageSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class MeleeHitEvent extends LivingDamageEvent {


    private final LivingEntity attacker;
    private ItemStack weapon;


    public MeleeHitEvent(LivingEntity victim, APEntityDamageSource source, LivingEntity attacker, ItemStack weaponIn) {
        super(victim, source, (float) source.getInstance().getAmount());
        this.weapon = weaponIn;
        this.attacker = attacker;
    }


    public ItemStack getWeapon() {
        return weapon;
    }


    public void setWeapon(ItemStack weapon) {
        this.weapon = weapon;
    }
}
