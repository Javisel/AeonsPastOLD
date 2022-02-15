package com.javisel.aeonspast.common.capabiltiies.entity;

import com.javisel.aeonspast.common.effects.ComplexEffectInstance;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffect;

import java.util.ArrayList;
import java.util.HashMap;

public interface IEntityData {





    CompoundTag writeNBT();

    void readNBT(CompoundTag tag);

    int getTicks();

    void tick();

    int getLevel();




    HashMap<MobEffect, ArrayList<ComplexEffectInstance>> getMobEffectArrayListHashMap();
}
