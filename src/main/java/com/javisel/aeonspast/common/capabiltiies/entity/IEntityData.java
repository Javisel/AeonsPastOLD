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


    SpellStack getOrCreateSpellStack(Spell spell);



    CompoundTag writeNBT();

    void readNBT(CompoundTag tag);

    int getTicks();

    void tick();

    int getLevel();

    SpellStack getSpellStackRaw(Spell spell);

    Float getOrCreateResource(Resource resource);

    Float getResourceAmountRaw(Resource resource);

    HashMap<Resource, Float> getResourceMap();

    ArrayList<Spell> getActiveSpells();

    void addActiveSpell(Spell spell);

    void removeActiveSpell(Spell spell);

    void removeSpellStack(Spell spell);

    HashMap<MobEffect, ArrayList<ComplexEffectInstance>> getMobEffectArrayListHashMap();
}
