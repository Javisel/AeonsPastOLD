package com.javisel.aeonspast.common.capabiltiies.player;

import com.javisel.aeonspast.common.playerclasses.ClassInstance;
import com.javisel.aeonspast.common.playerclasses.PlayerGameClass;
import com.javisel.aeonspast.common.spell.Spell;
import net.minecraft.nbt.CompoundTag;

import java.util.HashMap;

public interface IPlayerData {

    HashMap<PlayerGameClass, ClassInstance> getClasses();





    PlayerGameClass getActiveClass();

    void setActiveGameClass(PlayerGameClass activeGameClass);

    ClassInstance getOrCreatePlayerClass(PlayerGameClass gameClass);

    void readNBT(CompoundTag compoundTag);

    CompoundTag writeNBT();


    ClassInstance getActiveClassInstance();


    PlayerSpellBar getSpellBar();

    Spell getActiveWeaponSpell();

    void setActiveWeaponSpell(Spell spell);
}
