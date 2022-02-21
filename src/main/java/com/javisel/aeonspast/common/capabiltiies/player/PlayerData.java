package com.javisel.aeonspast.common.capabiltiies.player;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.playerclasses.ClassInstance;
import com.javisel.aeonspast.common.playerclasses.PlayerGameClass;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.StringKeys;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryManager;

import java.util.ArrayList;
import java.util.HashMap;

import static com.javisel.aeonspast.utilities.StringKeys.*;


public class PlayerData implements IPlayerData {


    HashMap<PlayerGameClass, ClassInstance> playerGameClasses = new HashMap<>();
    Spell activeWeaponSpell;
    ArrayList<Spell> activeSpells;
    HashMap<Spell, SpellStack> spellStackHashMap;
    HashMap<Resource, Float> resourceMap;

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
        CompoundTag spellBarTag = spellBar.writeNBT();

        if (activeClass != null) {
            tag.putString(ACTIVE_CLASS, activeClass.getRegistryName().toString());
        }
        if (!playerGameClasses.isEmpty()) {

            for (PlayerGameClass entry : playerGameClasses.keySet()) {


                classes.put(entry.getRegistryName().toString(), playerGameClasses.get(entry).toNBT());


            }


        }

        if (spellStackHashMap != null) {
            CompoundTag spelldata = new CompoundTag();


            for (Spell spell : spellStackHashMap.keySet()) {


                spelldata.put(spell.getRegistryName().toString(), spellStackHashMap.get(spell).toNBT());


            }

            tag.put(StringKeys.SPELL_DATA, spelldata);

        }

        if (activeSpells != null) {
            CompoundTag activeSpellTag = new CompoundTag();
            int i = 0;
            for (Spell spell : activeSpells) {


                String input = spell == null ? EMPTY : spell.getRegistryName().toString();


                activeSpellTag.putString(String.valueOf(i), input);

                i++;

            }
            tag.put(SPELLS, activeSpellTag);
        }
        if (activeWeaponSpell != null) {

            tag.putString(WEAPON_SPELL, activeWeaponSpell.getRegistryName().toString());

        }
        if (resourceMap != null) {


            CompoundTag resourceTag = new CompoundTag();
            for (Resource resource : resourceMap.keySet()) {


                resourceTag.putFloat(resource.getRegistryName().toString(), resourceMap.get(resource));


            }


            tag.put(RESOURCE, resourceTag);
        }


        tag.put(SPELL_BAR, spellBarTag);
        tag.put(CLASSES, classes);


        return tag;
    }


    @Override
    public void readNBT(CompoundTag tag) {

        CompoundTag classes = tag.getCompound(CLASSES);

        playerGameClasses.clear();
        for (String key : classes.getAllKeys()) {

            ResourceLocation resourceLocation = new ResourceLocation(key);

            PlayerGameClass gameClass = (PlayerGameClass) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.CLASS_REGISTRY_NAME).getValue(resourceLocation);
            ClassInstance classInstance = new ClassInstance();
            classInstance.fromNBT(classes.getCompound(key));


            playerGameClasses.put(gameClass, classInstance);

        }

        if (tag.contains(ACTIVE_CLASS)) {

            ResourceLocation resourceLocation = new ResourceLocation(tag.getString(ACTIVE_CLASS));
            activeClass = (PlayerGameClass) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.CLASS_REGISTRY_NAME).getValue(resourceLocation);


        }


        if (tag.contains(WEAPON_SPELL)) {


            String key = tag.getString(WEAPON_SPELL);


            activeWeaponSpell = Spell.getSpellByResourceLocation(new ResourceLocation(key));

            if (activeWeaponSpell == Spell.getDefaultSpell()) {
                activeWeaponSpell = null;
            }
        }
        if (tag.contains(StringKeys.SPELL_DATA)) {

            CompoundTag spelldata = tag.getCompound(StringKeys.SPELL_DATA);

            if (spellStackHashMap == null) {


                spellStackHashMap = new HashMap<>();
            }
            for (String entry : spelldata.getAllKeys()) {


                ResourceLocation resourceLocation = new ResourceLocation(entry);

                SpellStack spellStack = SpellStack.readNBT(spelldata.getCompound(entry));


                Spell spell = (Spell) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.SPELL_REGISTRY_NAME).getValue(resourceLocation);


                spellStackHashMap.put(spell, spellStack);

            }


        }

        if (tag.contains(RESOURCE)) {

            CompoundTag resourceTag = tag.getCompound(RESOURCE);

            if (resourceMap == null) {
                resourceMap = new HashMap<>();
            }
            for (String entry : resourceTag.getAllKeys()) {


                Resource resource = (Resource) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.RESOURCE_REGISTRY_NAME).getValue(new ResourceLocation(entry));


                resourceMap.put(resource, resourceTag.getFloat(entry));


            }


        }


        if (tag.contains(SPELLS)) {
            activeSpells = new ArrayList<>(4);

            CompoundTag activeSpellTag = tag.getCompound(SPELLS);
            if (!activeSpellTag.isEmpty()) {

                for (String key : activeSpellTag.getAllKeys()) {
                    int stuff = Integer.parseInt(key);


                    String spell = activeSpellTag.getString(key);

                    Spell thespell = Spell.getDefaultSpell();
                    if (!spell.equalsIgnoreCase(EMPTY)) {

                        thespell = Spell.getSpellByResourceLocation(new ResourceLocation(spell));

                    }

                    addActiveSpell(thespell);
                }

            }

        }

        spellBar.readNBT(tag.getCompound(SPELL_BAR));


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


    @Override
    public void addActiveSpell(Spell spell) {


        if (activeSpells == null) {
            activeSpells = new ArrayList<>();
        }
        for (Spell index : activeSpells) {


            if (index == spell) {
                return;
            }

        }

        activeSpells.add(spell);


    }

    @Override
    public void removeSpellStack(Spell spell) {


        spellStackHashMap.remove(spell);


    }

    @Override
    public void removeActiveSpell(Spell spell) {


        activeSpells.removeIf(index -> index == spell);


    }

    @Override
    public ArrayList<Spell> getActiveSpells() {


        return activeSpells;
    }

    @Override
    public Float getResourceAmountRaw(Resource resource) {


        if (resourceMap == null) {
            spellStackHashMap = new HashMap<>();


        }


        if (resourceMap.containsKey(resource)) {

            return resourceMap.get(resource);
        }

        return null;


    }


    @Override
    public Float getOrCreateResource(Resource resource) {


        if (resourceMap == null) {

            resourceMap = new HashMap<>();
        }

        if (!resourceMap.containsKey(resource)) {

            resourceMap.put(resource, (float) resource.getResourceMaxAttribute().get().getDefaultValue());
        }

        return resourceMap.get(resource);
    }

    @Override
    public HashMap<Resource, Float> getResourceMap() {

        if (resourceMap == null) {

            resourceMap = new HashMap<>();
        }

        return resourceMap;
    }

    @Override
    public SpellStack getSpellStack(Spell spell) {


        if (spellStackHashMap == null) {
            spellStackHashMap = new HashMap<>();


        }


        if (spellStackHashMap.containsKey(spell)) {

            return spellStackHashMap.get(spell);
        }

        return null;

    }

    @Override
    public void setSpellstack(Spell spell, SpellStack stack) {


        if (spellStackHashMap == null) {
            spellStackHashMap = new HashMap<>();


        }

        spellStackHashMap.put(spell,stack);
    }
}
