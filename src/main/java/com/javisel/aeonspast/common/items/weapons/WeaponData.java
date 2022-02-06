package com.javisel.aeonspast.common.items.weapons;

import com.javisel.aeonspast.common.config.StatisticPair;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.items.ItemType;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.javisel.aeonspast.utilities.StringKeys.SPELL;

public class WeaponData {


    public static final String WEAPON_MOD_ID = "4703e862-a7ae-4697-aeea-f58ac8697e10";

    public static final String BASE_STATS = "base_stats";

    public static final double ATTACK_SPEED_OFFSET = -1;

    private final ItemType itemType;
    private final StatisticPair attack_damage;
    private final StatisticPair attack_speed;
    private final StatisticPair critical_chance;
    private final StatisticPair critical_damage;
    private final StatisticPair durability;
    private final StatisticPair enchantability;
    private final StatisticPair range;
    private final SecondaryData secondaryData;
    private final List<String> properties = new ArrayList<>();
    private final List<String> spells = new ArrayList<>();
    private StatisticPair dps;


    public WeaponData(ItemType item_type, StatisticPair attack_damage, StatisticPair attack_speed, StatisticPair critical_chance, StatisticPair critical_damage, StatisticPair durability, StatisticPair enchantability, StatisticPair range, SecondaryData secondaryData, List<String> properties, List<String> spells) {
        this.itemType = item_type;

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

    public void loadToWeapon(@Nullable LivingEntity entity, ItemStack stack) {


        if (stack.getAttributeModifiers(EquipmentSlot.MAINHAND).containsKey(WEAPON_MOD_ID)) {
            System.out.println("Already loaded!");
            return;
        }

        float luck = 0;

        if (entity != null) {

            luck = (float) entity.getAttributeValue(AttributeRegistration.FORTUNE.get());

        }


        Random random = new Random();


        UUID id = UUID.fromString(WEAPON_MOD_ID);
        stack.addAttributeModifier(AttributeRegistration.WEAPON_POWER.get(), new AttributeModifier(id, BASE_STATS, attack_damage.roll(luck, random), AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);

        stack.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier(id, BASE_STATS, (ATTACK_SPEED_OFFSET + attack_speed.roll(0, random)), AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);


        stack.addAttributeModifier(AttributeRegistration.CRITICAL_CHANCE.get(), new AttributeModifier(id, BASE_STATS, critical_chance.roll(luck, random), AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);


        stack.addAttributeModifier(AttributeRegistration.CRITICAL_DAMAGE.get(), new AttributeModifier(id, BASE_STATS, critical_damage.roll(luck, random), AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);


        stack.addAttributeModifier(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(id, BASE_STATS, -5 + range.roll(luck, random), AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);


        for (String property : properties) {


            ItemProperty.getPropertyByLocation(new ResourceLocation(property)).applyToItem(stack);


        }


        int i = spells.size();

        int choice = random.nextInt(i);


        ItemEngine.getAeonsPastTag(stack).putString(SPELL, spells.get(choice));


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

    public StatisticPair getDPS() {


        float low = getAttack_speed().getmin() * getAttack_damage().getmin();
        float high = getAttack_speed().getMax() * getAttack_damage().getMax();


        if (dps == null) {

            dps = new StatisticPair(low, high);
        }

        return dps;
    }

    private class SecondaryData {

        final StatisticPair damage;
        final StatisticPair speed;
        final StatisticPair range;

        public SecondaryData(StatisticPair damage, StatisticPair speed, StatisticPair range) {
            this.damage = damage;
            this.speed = speed;
            this.range = range;
        }
    }


}
