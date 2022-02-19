package com.javisel.aeonspast.common.capabiltiies.entity;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.effects.ComplexEffect;
import com.javisel.aeonspast.common.effects.ComplexEffectInstance;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.StringKeys;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.javisel.aeonspast.utilities.StringKeys.*;

public class EntityData implements IEntityData {


     int ticks = 0;
    int level = 1;


    //TODO move spell and resource stuff out of EntityData into PlayerData!

    HashMap<MobEffect,ArrayList<ComplexEffectInstance>> mobEffectArrayListHashMap = new HashMap<>();

    @Override
    public CompoundTag writeNBT() {
        CompoundTag tag = new CompoundTag();
         tag.putInt(StringKeys.LEVEL, level);






        CompoundTag effects = new CompoundTag();


        for (Map.Entry<MobEffect, ArrayList<ComplexEffectInstance>> entry : mobEffectArrayListHashMap.entrySet()) {

            CompoundTag effect = new CompoundTag();

            int i = 0;
            for (ComplexEffectInstance instance : entry.getValue()){

                effect.put("entry_"+i, instance.toNBT());

                i++;
            }
        effects.put(entry.getKey().getRegistryName().toString(), effect);

        }


        tag.put(EFFECTS,effects);


        return tag;
    }

    @Override
    public void readNBT(CompoundTag tag) {



        level = tag.getInt(StringKeys.LEVEL);


        if (tag.contains(EFFECTS)) {

            CompoundTag effects = tag.getCompound(EFFECTS);


            for (String key : effects.getAllKeys()) {

                ResourceLocation entryLocation = new ResourceLocation(key);



                CompoundTag effect = effects.getCompound(key);
                ArrayList<ComplexEffectInstance> effectInstances = new ArrayList<>();

                for (String entry : effect.getAllKeys()) {


                    effectInstances.add(ComplexEffectInstance.fromNBT(effect.getCompound(entry)));


                }

                mobEffectArrayListHashMap.put(ForgeRegistries.MOB_EFFECTS.getValue(entryLocation),effectInstances);








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
    public int getLevel() {
        return level;
    }





    @Override
    public HashMap<MobEffect, ArrayList<ComplexEffectInstance>> getMobEffectArrayListHashMap() {
        return mobEffectArrayListHashMap;
    }
}

