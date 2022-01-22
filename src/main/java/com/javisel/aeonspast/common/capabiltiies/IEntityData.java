package com.javisel.aeonspast.common.capabiltiies;

import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;

public interface IEntityData {

    float getMana();


    SpellStack getOrCreateSpellStack(Spell spell);
    void setMana(float mana);

    CompoundTag getStoredData();

    CompoundTag writeNBT();

    void readNBT(CompoundTag tag);

    int getTicks();

    void tick();

    SpellStack getSpellStackRaw(Spell spell);
}
