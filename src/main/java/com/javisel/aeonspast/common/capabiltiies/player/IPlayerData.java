package com.javisel.aeonspast.common.capabiltiies.player;

import com.javisel.aeonspast.common.playerclasses.ClassInstance;
import com.javisel.aeonspast.common.playerclasses.PlayerGameClass;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;
import java.util.HashMap;

public interface IPlayerData {

    abstract HashMap<PlayerGameClass, ClassInstance> getClasses();


    abstract PlayerGameClass getActiveClass();

    abstract void setActiveGameClass(PlayerGameClass activeGameClass);

    abstract ClassInstance getOrCreatePlayerClass(PlayerGameClass gameClass);

    abstract void readNBT(CompoundTag tag);

    abstract CompoundTag writeNBT();


    abstract ClassInstance getActiveClassInstance();


    abstract PlayerSpellBar getSpellBar();

    abstract Spell getActiveWeaponSpell();

    abstract void setActiveWeaponSpell(Spell spell);

    void addActiveSpell(Spell spell);

    void removeSpellStack(Spell spell);

    void removeActiveSpell(Spell spell);

    ArrayList<Spell> getActiveSpells();

    Float getResourceAmountRaw(Resource resource);

    Float getOrCreateResource(Resource resource);

    HashMap<Resource, Float> getResourceMap();

    SpellStack getSpellStackRaw(Spell spell);

    SpellStack getOrCreateSpellStack(Spell spell);
}
