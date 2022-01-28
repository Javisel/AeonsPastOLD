package com.javisel.aeonspast.common.items.weapons;

import com.google.common.collect.Multimap;
import com.javisel.aeonspast.common.combat.damagetypes.APDamageSubType;
import com.javisel.aeonspast.common.config.StatisticPair;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.ForgeRegistries;
import org.antlr.v4.runtime.misc.MultiMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.javisel.aeonspast.utilities.StringKeys.*;

public class WeaponData {


    private static final String WEAPON_MOD_ID = "d9604102-763b-477c-a3b7-a4ea62647d61";
    private final String BASE_STATS = "base_stats";




    private final StatisticPair attack_damage;
    private final StatisticPair attack_speed;
    private final  StatisticPair critical_chance;
    private  final StatisticPair critical_damage;
    private final StatisticPair durability;
    private final StatisticPair enchantability;
    private final StatisticPair range;
    private final SecondaryData secondaryData;


    private final List<String> properties = new ArrayList<>();
    private final List<String> spells = new ArrayList<>();


    public WeaponData(StatisticPair attack_damage, StatisticPair attack_speed, StatisticPair critical_chance, StatisticPair critical_damage, StatisticPair durability, StatisticPair enchantability, StatisticPair range, SecondaryData secondaryData, List<String> properties, List<String>  spells ) {
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
        this.critical_chance = critical_chance;
        this.critical_damage = critical_damage;
        this.durability = durability;
        this.enchantability = enchantability;
        this.range = range;
        this.secondaryData = secondaryData;



        for (String property : properties) {

            this.properties.add(property);


        }
        for (String spell : spells) {

            this.spells.add(spell);


        }





    }

    public CompoundTag toNBT() {

        CompoundTag tag = new CompoundTag();


        return tag;


    }

    public void loadToWeapon(ItemStack stack) {


        if (stack.getAttributeModifiers(EquipmentSlot.MAINHAND).containsKey(WEAPON_MOD_ID)) {
            return;
        }



        Random random = new Random();

        System.out.println("Loading Stats!");

        UUID id = UUID.fromString(WEAPON_MOD_ID);
        stack.addAttributeModifier(AttributeRegistration.WEAPON_POWER.get(), new AttributeModifier(id,BASE_STATS,attack_damage.roll(0,random), AttributeModifier.Operation.ADDITION),EquipmentSlot.MAINHAND);
        stack.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier(id,BASE_STATS,4-attack_speed.roll(0,random), AttributeModifier.Operation.ADDITION),EquipmentSlot.MAINHAND);
        stack.addAttributeModifier(AttributeRegistration.CRITICAL_CHANCE.get(), new AttributeModifier(id,BASE_STATS,critical_chance.roll(0,random), AttributeModifier.Operation.ADDITION),EquipmentSlot.MAINHAND);
        stack.addAttributeModifier(AttributeRegistration.CRITICAL_DAMAGE.get(), new AttributeModifier(id,BASE_STATS,critical_damage.roll(0,random), AttributeModifier.Operation.ADDITION),EquipmentSlot.MAINHAND);
        stack.addAttributeModifier(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(id,BASE_STATS,range.roll(0,random), AttributeModifier.Operation.ADDITION),EquipmentSlot.MAINHAND);







    }





    private class SecondaryData  {

        final StatisticPair damage;
        final StatisticPair speed;
        final StatisticPair range;

        public SecondaryData(StatisticPair damage, StatisticPair speed, StatisticPair range) {
            this.damage = damage;
            this.speed = speed;
            this.range = range;
        }
    }


    public StatisticPair getAttack_damage() {
        return attack_damage;
    }

    public StatisticPair getAttack_speed() {
        return attack_speed;
    }

    public StatisticPair getCritical_chance() {
        return critical_chance;
    }

    public StatisticPair getCritical_damage() {
        return critical_damage;
    }

    public StatisticPair getDurability() {
        return durability;
    }

    public StatisticPair getEnchantability() {
        return enchantability;
    }

    public StatisticPair getRange() {
        return range;
    }

    public SecondaryData getSecondaryData() {
        return secondaryData;
    }

    public List<String> getProperties() {
        return properties;
    }

    public List<String> getSpells() {
        return spells;
    }
}
