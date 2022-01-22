package com.javisel.aeonspast.common.spell;

import com.javisel.aeonspast.ModBusEventHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryManager;

import static com.javisel.aeonspast.utilities.StringKeys.*;

public final class SpellStack {

    protected int charges = 1;
    protected int cooldownOrChargeRemaining = 0;
    protected int currentMaxCooldownOrCharge = 0;
    protected int maxCharges = 1;
    protected boolean isCoolingdown=false;
    protected Spell spell;
    protected  CompoundTag spellData;
    protected SpellState spellState;
    public SpellStack(Spell spell) {

         cooldownOrChargeRemaining =0;
        this.spell=spell;
        spellState =SpellState.OFF;
    }


    private SpellStack(CompoundTag tag) {

        charges = tag.getInt(CHARGES);
        cooldownOrChargeRemaining = tag.getInt(COOLDOWN);
         currentMaxCooldownOrCharge=tag.getInt(MAX_COOLDOWN);
         maxCharges=tag.getInt(MAX_CHARGES);

         spellState = SpellState.BY_ID[tag.getInt(SPELL_STATE)];

        if (tag.contains(SPELL_DATA)) {
            spellData = tag.getCompound(SPELL_DATA);

        }

        ResourceLocation resourceLocation = new ResourceLocation(tag.getString(SPELL));
        spell = (Spell) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.SPELL_REGISTRY_NAME).getValue(resourceLocation);

    }


    public static SpellStack readNBT(CompoundTag tag) {


        return new SpellStack(tag);

    }

    public  CompoundTag toNBT() {


       CompoundTag tag = new CompoundTag();

       tag.putInt(CHARGES,charges);
        tag.putInt(COOLDOWN, cooldownOrChargeRemaining);
         tag.putInt(MAX_COOLDOWN,currentMaxCooldownOrCharge);
         tag.putInt(MAX_CHARGES,maxCharges);
         tag.putInt(SPELL_STATE,spellState.getId());
        if (spellData != null) {
            tag.put(SPELL_DATA, spellData);
        }
        tag.putString(SPELL, spell.getRegistryName().toString());



return tag;
    }


    public int getCharges() {
        return charges;
    }

    public int getCooldownOrChargeRemaining() {
        return cooldownOrChargeRemaining;
    }

    public Spell getSpell() {
        return spell;
    }

    public CompoundTag getSpellData() {
        return spellData;
    }

    public int getCurrentMaxCooldownOrCharge() {
        return currentMaxCooldownOrCharge;
    }


    public int getMaxCharges() {
        return maxCharges;
    }



    public void cycleState() {
        int find;

        if (spellState.id ==SpellState.BY_ID.length) {
            find = 0;
        } else{

            find = spellState.getId() +1;
        }




        spellState = SpellState.BY_ID[find];


    }

    public void setSpellState(SpellState state) {


        this.spellState=state;
    }




    @OnlyIn(Dist.CLIENT)

    public boolean isComplexModel() {


        return false;
    }


}
