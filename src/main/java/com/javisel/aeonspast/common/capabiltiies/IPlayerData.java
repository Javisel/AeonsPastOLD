package com.javisel.aeonspast.common.capabiltiies;

import com.javisel.aeonspast.common.playerclasses.ClassInstance;
import com.javisel.aeonspast.common.playerclasses.PlayerGameClass;
import com.javisel.aeonspast.common.spell.Spell;
import net.minecraft.nbt.CompoundTag;

import java.util.ArrayList;
import java.util.HashMap;

public interface IPlayerData {

    HashMap<PlayerGameClass, ClassInstance> getClasses();

    PlayerGameClass getActiveClass();

    void setActiveGameClass(PlayerGameClass activeGameClass);

    ClassInstance getOrCreatePlayerClass(PlayerGameClass gameClass);

    void readNBT(CompoundTag compoundTag);

    CompoundTag writeNBT();


    ClassInstance getActiveClassInstance();

    ArrayList<Spell> getActiveSpells();

    void addActiveSpell(Spell spell);

    void removeActiveSpell(Spell spell);

    PlayerSpellBar getSpellBar();
}
