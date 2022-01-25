package com.javisel.aeonspast.common.events;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class DirectHitEvent extends LivingDamageEvent {




    private ItemStack weapon;
    private LivingEntity attacker;


    public DirectHitEvent(LivingEntity victim, DamageSource source, float amount, LivingEntity attacker, ItemStack weaponIn) {
        super(victim,source, amount);
        this.weapon=weaponIn;
        this.attacker=attacker;
    }


    public ItemStack getWeapon() {
        return weapon;
    }


    public void setWeapon(ItemStack weapon) {
        this.weapon = weapon;
    }
}
