package com.javisel.aeonspast.common.items.emblem;

import com.javisel.aeonspast.common.registration.ClassRegistration;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

public class WarriorEmblem extends BasicEmblem {

    public WarriorEmblem() {
        super(new Properties().stacksTo(1), ClassRegistration.WARRIOR);
    }


    /*

    Warrior Passive-  Healing Each Second

     */


    @Override
    public boolean attemptCast(LivingEntity caster, ItemStack stack) {
        if (super.attemptCast(caster, stack)) {

            caster.heal(caster.getMaxHealth() * 0.15f);


            Level world = caster.level;
            world.playSound(null, caster, SoundEvents.FIRE_EXTINGUISH, SoundSource.NEUTRAL, 0.5f, 1.0f);

            return true;
        }


        return false;

    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {


    }
}
