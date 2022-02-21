package com.javisel.aeonspast.common.spell;

import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.utilities.StringKeys;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryManager;

import java.util.UUID;

import static com.javisel.aeonspast.utilities.StringKeys.*;

public final class SpellStack {

    protected int charges = 1;
    protected int cooldown = 0;
    protected int chargeTime = 0;
    protected  final UUID spellInstanceId;

    protected Spell spell;
    protected CompoundTag spellData;
    protected SpellState spellState;

    public SpellStack(Spell spell) {

        cooldown = 0;
        this.spell = spell;
        spellState = SpellState.OFF;
        charges = spell.getDefaultMaxCharges();
        spellInstanceId = UUID.randomUUID();
    }


    private SpellStack(CompoundTag tag) {

        charges = tag.getInt(CHARGES);
        cooldown = tag.getInt(COOLDOWN);
        chargeTime = tag.getInt(CHARGE_TIMER);

        spellState = SpellState.BY_ID[tag.getInt(SPELL_STATE)];

        if (tag.contains(SPELL_DATA)) {
            spellData = tag.getCompound(SPELL_DATA);

        }

        ResourceLocation resourceLocation = new ResourceLocation(tag.getString(SPELL));
        spell = (Spell) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.SPELL_REGISTRY_NAME).getValue(resourceLocation);
        spellInstanceId=tag.getUUID(UNIQUE_ID);
    }


    public static SpellStack readNBT(CompoundTag tag) {


        return new SpellStack(tag);

    }

    public CompoundTag toNBT() {


        CompoundTag tag = new CompoundTag();

        tag.putInt(CHARGES, charges);
        tag.putInt(COOLDOWN, cooldown);
        tag.putInt(CHARGE_TIMER, chargeTime);
        tag.putInt(SPELL_STATE, spellState.getId());
        if (spellData != null) {
            tag.put(SPELL_DATA, spellData);
        }
        tag.putString(SPELL, spell.getRegistryName().toString());
        tag.putUUID(UNIQUE_ID,spellInstanceId);

        return tag;
    }


    public int getCharges() {
        return charges;
    }

    public int getCooldown() {
        return cooldown;
    }

    public Spell getSpell() {
        return spell;
    }

    public CompoundTag getSpellData() {
        return spellData;
    }

    public int getChargeTime() {
        return chargeTime;
    }


    public void cycleState() {
        int find;

        if (spellState.id == SpellState.BY_ID.length) {
            find = 0;
        } else {

            find = spellState.getId() + 1;
        }


        spellState = SpellState.BY_ID[find];


    }

    public boolean isCoolingDown() {


        return cooldown > 0;
    }

    public boolean isRecharging() {


        return chargeTime > 0;
    }

    public SpellState getSpellState() {

        return spellState;
    }

    public void setSpellState(SpellState state) {


        this.spellState = state;
    }

    public UUID getSpellInstanceId() {
        return spellInstanceId;
    }
}
