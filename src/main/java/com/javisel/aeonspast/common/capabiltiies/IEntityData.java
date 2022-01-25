package com.javisel.aeonspast.common.capabiltiies;

import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;

public interface IEntityData {


    SpellStack getOrCreateSpellStack(Spell spell);

    CompoundTag getStoredData();

    CompoundTag writeNBT();

    void readNBT(CompoundTag tag);

    int getTicks();

    void tick();

    SpellStack getSpellStackRaw(Spell spell);

    public Float getOrCreateResource(Resource resource);
    public Float getResourceAmountRaw(Resource resource);
    HashMap<Resource, Float> getResourceMap();
}
