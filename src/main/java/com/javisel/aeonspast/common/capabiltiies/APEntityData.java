package com.javisel.aeonspast.common.capabiltiies;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.StringKeys;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryManager;

import java.util.HashMap;

public class APEntityData implements IEntityData {


    CompoundTag storedData = new CompoundTag();
    float mana = 0;
    int ticks = 0;
    float experience = 0;
    int level = 0;

    HashMap<Spell, SpellStack> spellStackHashMap;


    @Override
    public float getMana() {
        return mana;
    }

    @Override
    public void setMana(float mana) {

        this.mana = mana;
    }

    @Override
    public CompoundTag getStoredData() {
        return storedData;
    }

    @Override
    public CompoundTag writeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.put(StringKeys.STORED_DATA, storedData);
        tag.putFloat(StringKeys.MANA, mana);
        tag.putFloat(StringKeys.EXPERIENCE,experience);
        tag.putInt(StringKeys.LEVEL,level);



        if (spellStackHashMap !=null) {
            CompoundTag spelldata = new CompoundTag();


            for (Spell spell : spellStackHashMap.keySet()) {


                spelldata.put(spell.getRegistryName().toString(),spellStackHashMap.get(spell).toNBT());


            }

            tag.put(StringKeys.SPELL_DATA,spelldata);

        }



        return tag;
    }

    @Override
    public void readNBT(CompoundTag tag) {


        if (tag.contains(StringKeys.STORED_DATA)) {
            storedData = tag.getCompound(StringKeys.STORED_DATA);
        }
        mana=tag.getFloat(StringKeys.MANA);
        experience=tag.getFloat(StringKeys.EXPERIENCE);
        level=tag.getInt(StringKeys.LEVEL);


        if (tag.contains(StringKeys.SPELL_DATA)) {

            CompoundTag spelldata = tag.getCompound(StringKeys.SPELL_DATA);

            if (spellStackHashMap== null) {


                spellStackHashMap = new HashMap<>();
            }
            for (String entry : spelldata.getAllKeys()) {


                ResourceLocation resourceLocation = new ResourceLocation(entry);

                SpellStack spellStack = SpellStack.readNBT(spelldata.getCompound(entry));


                Spell spell = (Spell) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.SPELL_REGISTRY_NAME).getValue(resourceLocation);



                spellStackHashMap.put(spell,spellStack);

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

            spellStackHashMap.put(spell,spellStack);
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


}
