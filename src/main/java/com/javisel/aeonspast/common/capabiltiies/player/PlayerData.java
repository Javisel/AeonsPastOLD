package com.javisel.aeonspast.common.capabiltiies.player;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.playerclasses.ClassInstance;
import com.javisel.aeonspast.common.playerclasses.PlayerGameClass;
import com.javisel.aeonspast.common.spell.Spell;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryManager;

import java.util.HashMap;

import static com.javisel.aeonspast.utilities.StringKeys.*;


public class PlayerData implements IPlayerData {


    HashMap<PlayerGameClass, ClassInstance> playerGameClasses = new HashMap<>();
    Spell activeWeaponSpell;


    PlayerSpellBar spellBar = new PlayerSpellBar();


    PlayerGameClass activeClass;

    @Override
    public HashMap<PlayerGameClass, ClassInstance> getClasses() {
        return playerGameClasses;
    }

    @Override
    public ClassInstance getOrCreatePlayerClass(PlayerGameClass gameClass) {

        if (playerGameClasses.containsKey(gameClass)) {


            return playerGameClasses.get(gameClass);
        }

        ClassInstance classInstance = new ClassInstance();
        playerGameClasses.put(gameClass, classInstance);

        return classInstance;

    }


    @Override
    public CompoundTag writeNBT() {

        CompoundTag tag = new CompoundTag();
        CompoundTag classes = new CompoundTag();
        CompoundTag activeSpellTag = new CompoundTag();
        CompoundTag spellBarTag = spellBar.writeNBT();

        if (activeClass != null) {
            tag.putString(ACTIVE_CLASS, activeClass.getRegistryName().toString());
        }
        if (!playerGameClasses.isEmpty()) {

            for (PlayerGameClass entry : playerGameClasses.keySet()) {


                classes.put(entry.getRegistryName().toString(), playerGameClasses.get(entry).toNBT());


            }


        }


        if (activeWeaponSpell != null) {

            tag.putString(WEAPON_SPELL, activeWeaponSpell.getRegistryName().toString());

        }

        tag.put(SPELLS, activeSpellTag);
        tag.put(SPELL_BAR, spellBarTag);
        tag.put(CLASSES, classes);


        return tag;
    }


    @Override
    public void readNBT(CompoundTag compoundTag) {

        CompoundTag classes = compoundTag.getCompound(CLASSES);
        CompoundTag activeSpellTag = compoundTag.getCompound(SPELLS);
        CompoundTag spellBarTag = compoundTag.getCompound(SPELL_BAR);
        playerGameClasses.clear();
        for (String key : classes.getAllKeys()) {

            ResourceLocation resourceLocation = new ResourceLocation(key);

            PlayerGameClass gameClass = (PlayerGameClass) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.CLASS_REGISTRY_NAME).getValue(resourceLocation);
            ClassInstance classInstance = new ClassInstance();
            classInstance.fromNBT(classes.getCompound(key));


            playerGameClasses.put(gameClass, classInstance);

        }

        if (compoundTag.contains(ACTIVE_CLASS)) {

            ResourceLocation resourceLocation = new ResourceLocation(compoundTag.getString(ACTIVE_CLASS));
            activeClass = (PlayerGameClass) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.CLASS_REGISTRY_NAME).getValue(resourceLocation);


        }


        if (compoundTag.contains(WEAPON_SPELL)) {


            String key = compoundTag.getString(WEAPON_SPELL);


            activeWeaponSpell = Spell.getSpellByResourceLocation(new ResourceLocation(key));

                if (activeWeaponSpell ==Spell.getDefaultSpell()) {
                    activeWeaponSpell=null;
                }
        }


        spellBar.readNBT(compoundTag.getCompound(SPELL_BAR));


    }


    @Override
    public PlayerGameClass getActiveClass() {
        return activeClass;
    }

    @Override
    public void setActiveGameClass(PlayerGameClass activeGameClass) {


        if (activeGameClass == null) {

            activeClass = null;
        }
        activeClass = activeGameClass;
    }

    @Override
    public ClassInstance getActiveClassInstance() {


        if (activeClass != null) {

            return playerGameClasses.get(activeClass);


        }
        return null;
    }


    @Override
    public PlayerSpellBar getSpellBar() {

        return spellBar;
    }

    @Override
    public Spell getActiveWeaponSpell() {
        return activeWeaponSpell;
    }

    @Override
    public void setActiveWeaponSpell(Spell spell) {


        this.activeWeaponSpell = spell;
    }
}
