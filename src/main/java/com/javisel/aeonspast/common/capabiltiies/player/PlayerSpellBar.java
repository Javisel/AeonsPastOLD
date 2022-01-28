package com.javisel.aeonspast.common.capabiltiies.player;

import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.utilities.StringKeys;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class PlayerSpellBar {

    private static final int MAX_BAR_SIZE = 4;


    private NonNullList<Spell> spells;


    public CompoundTag writeNBT() {

        CompoundTag tag = new CompoundTag();


        if (spells == null) {
            spells = NonNullList.withSize(4, Spell.getDefaultSpell());

        }

        for (int i = 0; i < 4; i++) {


            String key = Integer.toString(i);

            String input = spells.get(i) == Spell.getDefaultSpell() ? StringKeys.EMPTY : spells.get(i).getRegistryName().toString();
            tag.putString(key, input);


        }


        return tag;

    }


    public void readNBT(CompoundTag tag) {

        if (spells == null) {
            spells = NonNullList.withSize(4, Spell.getDefaultSpell());

        }
        if (tag.isEmpty()) {

            return;
        }


        for (String key : tag.getAllKeys()) {


            Integer in = Integer.parseInt(key);

            Spell spelladd = tag.getString(key) == StringKeys.EMPTY ? Spell.getDefaultSpell() : Spell.getSpellByResourceLocation(new ResourceLocation(tag.getString(key)));

            spells.set(in, spelladd);

        }


    }

    public NonNullList<Spell> getSpellList() {
        if (spells == null) {
            spells = NonNullList.withSize(4, Spell.getDefaultSpell());

        }

        return spells;
    }

}
