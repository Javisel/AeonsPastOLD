package com.javisel.aeonspast.common.capabiltiies;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.playerclasses.ClassInstance;
import com.javisel.aeonspast.common.playerclasses.PlayerGameClass;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.utilities.StringKeys;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.RegistryManager;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.javisel.aeonspast.utilities.StringKeys.*;


public class APPlayerData implements IPlayerData{


     HashMap<PlayerGameClass, ClassInstance> playerGameClasses = new HashMap<>();
     ArrayList<Spell> activeSpells = new ArrayList<>(4);
     ArrayList<Spell> spellBar = new ArrayList<>(4);
     
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
        CompoundTag spells = new CompoundTag();
        CompoundTag spellBar = new CompoundTag();
        
        if (activeClass!=null) {
            tag.putString(ACTIVE_CLASS, activeClass.getRegistryName().toString());
        }
        if (!playerGameClasses.isEmpty()) {

            for (PlayerGameClass entry : playerGameClasses.keySet()) {


                classes.put(entry.getRegistryName().toString(),playerGameClasses.get(entry).toNBT());




            }


        }
        
        int i = 0;
        for (Spell spell : activeSpells) {

            if (spell == null) {
                spells.putString( String.valueOf(i), EMPTY);
            }
            else {
                spells.putString(String.valueOf(i), spell.getRegistryName().toString());

            }
            i++;
            
            
        }
        
  
        for (Spell spell : this.spellBar) {


            if (spell == null) {
                spells.putString( String.valueOf(i), EMPTY);
            }
             else {
                spells.putString(String.valueOf(i), spell.getRegistryName().toString());

            }

            i++;


        }

 
        tag.put(SPELLS,spells);
        tag.put(SPELL_BAR,spellBar);
        tag.put(CLASSES,classes);





        return tag;
    }


    @Override
    public void readNBT(CompoundTag compoundTag) {

        CompoundTag classes = compoundTag.getCompound(CLASSES);

        playerGameClasses.clear();
        for (String key : classes.getAllKeys()) {

            ResourceLocation resourceLocation = new ResourceLocation(key);

            PlayerGameClass gameClass = (PlayerGameClass) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.CLASS_REGISTRY_NAME).getValue(resourceLocation);
            ClassInstance classInstance = new ClassInstance();
            classInstance.fromNBT(classes.getCompound(key));



           playerGameClasses.put( gameClass,classInstance);

        }

        if (compoundTag.contains(ACTIVE_CLASS)) {

            ResourceLocation resourceLocation = new ResourceLocation(compoundTag.getString(ACTIVE_CLASS));
            activeClass = (PlayerGameClass) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.CLASS_REGISTRY_NAME).getValue(resourceLocation);


        }
        
        spellBar.clear();
        
        spellBar.ensureCapacity(4);
        CompoundTag spelltag = compoundTag.getCompound(SPELLS);


        for (String key : spelltag.getAllKeys()) {


            System.out.println("Key: " + key);

            int numslot =  Integer.valueOf(key);



            activeSpells.set(numslot,Spell.getSpellByResourceLocation( new ResourceLocation(spelltag.getString(key))        ));




        }
        CompoundTag spellbar = compoundTag.getCompound(SPELLS);

        activeSpells.clear();
        activeSpells.ensureCapacity(4);

        for (String key : spellbar.getAllKeys()) {


            int numslot =  Integer.valueOf(key);



            activeSpells.set(numslot,Spell.getSpellByResourceLocation( new ResourceLocation(spelltag.getString(key))        ));




        }


        for (int i = 0; i <activeSpells.size(); i++) {


            System.out.println("Spell: " + i + activeSpells.get(i).getRegistryName().toString());




        }


        for (int i = 0; i <spellBar.size(); i++) {


            System.out.println("Spell: " + i + spellBar.get(i).getRegistryName().toString());




        }

    }


    @Override
    public PlayerGameClass getActiveClass() {
        return activeClass;
    }

    @Override
    public void setActiveGameClass(PlayerGameClass activeGameClass) {


        if (activeGameClass==null) {

            activeClass=null;
        }
        activeClass=activeGameClass;
    }

    public ClassInstance getActiveClassInstance() {


        if (activeClass != null) {

            return  playerGameClasses.get(activeClass);


        }
        return null;
    }


    @Override
    public ArrayList<Spell> getActiveSpells() {
        return activeSpells;
    }

    @Override
    public ArrayList<Spell> getSpellBar() {
        return spellBar;
    }





}
