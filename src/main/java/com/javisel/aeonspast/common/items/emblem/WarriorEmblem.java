package com.javisel.aeonspast.common.items.emblem;

import com.javisel.aeonspast.common.registration.ClassRegistration;
import com.javisel.aeonspast.common.registration.SpellRegistration;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

public class WarriorEmblem extends BasicEmblem {

    public WarriorEmblem() {
        super(new Properties().stacksTo(1), ClassRegistration.WARRIOR, SpellRegistration.WARRIOR_CLASS_SPELL);
    }



}
