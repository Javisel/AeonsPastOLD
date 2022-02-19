package com.javisel.aeonspast.common.spell.spellproperty;

import com.google.gson.JsonObject;
import com.javisel.aeonspast.common.entities.entitytraits.EntityTrait;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class SpellProperty   extends net.minecraftforge.registries.ForgeRegistryEntry<SpellProperty >  {


        public final Object valueType;

    protected SpellProperty(Object valueType) {
        this.valueType = valueType;
    }


    public CompoundTag fromJson(JsonObject object) {


        return  new CompoundTag();
    }



    public Object getValue() {

        return  valueType;
    }

}
