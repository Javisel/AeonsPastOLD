package com.javisel.aeonspast.common.capabiltiies.entity;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.StringKeys;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryManager;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.HashMap;

import static com.javisel.aeonspast.utilities.StringKeys.*;

public class EntityData implements IEntityData {


     int ticks = 0;
    int level = 1;

    HashMap<Spell, SpellStack> spellStackHashMap;
    HashMap<Resource, Float> resourceMap;
    ArrayList<Spell> activeSpells;


    @Override
    public CompoundTag writeNBT() {
        CompoundTag tag = new CompoundTag();
         tag.putInt(StringKeys.LEVEL, level);


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

        if (spellStackHashMap != null) {
            CompoundTag spelldata = new CompoundTag();


            for (Spell spell : spellStackHashMap.keySet()) {


                spelldata.put(spell.getRegistryName().toString(), spellStackHashMap.get(spell).toNBT());


            }

            tag.put(StringKeys.SPELL_DATA, spelldata);

        }


        if (resourceMap != null) {


            CompoundTag resourceTag = new CompoundTag();
            for (Resource resource : resourceMap.keySet()) {


                resourceTag.putFloat(resource.getRegistryName().toString(), resourceMap.get(resource));


            }


            tag.put(RESOURCE, resourceTag);
        }


        return tag;
    }

    @Override
    public void readNBT(CompoundTag tag) {



        level = tag.getInt(StringKeys.LEVEL);


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


    }

    @Override
    public int getTicks() {


        return ticks;
    }

    @Override
    public void tick() {

        if (ticks == 20) {

            ticks = 0;
            return;

        }

        ticks++;

    }

    @Override
    public SpellStack getOrCreateSpellStack(Spell spell) {


        if (spellStackHashMap == null) {
            spellStackHashMap = new HashMap<>();


        }


        if (!spellStackHashMap.containsKey(spell)) {

            SpellStack spellStack = new SpellStack(spell);

            spellStackHashMap.put(spell, spellStack);
        }


        return spellStackHashMap.get(spell);
    }


    @Override
    public SpellStack getSpellStackRaw(Spell spell) {


        if (spellStackHashMap == null) {
            spellStackHashMap = new HashMap<>();


        }


        if (spellStackHashMap.containsKey(spell)) {

            return spellStackHashMap.get(spell);
        }

        return null;

    }


    @Override
    public HashMap<Resource, Float> getResourceMap() {

        return resourceMap;
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
    public int getLevel() {
        return level;
    }


    @Override
    public ArrayList<Spell> getActiveSpells() {


        return activeSpells;
    }


    @Override
    public void addActiveSpell(Spell spell) {


        if (activeSpells==null) {
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
    public void removeActiveSpell(Spell spell) {


        activeSpells.removeIf(index -> index == spell);


    }

    @Override
    public void removeSpellStack(Spell spell) {


        spellStackHashMap.remove(spell);


    }







}

