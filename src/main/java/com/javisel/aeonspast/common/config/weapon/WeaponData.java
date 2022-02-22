package com.javisel.aeonspast.common.config.weapon;

import com.javisel.aeonspast.common.config.StatisticPair;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.items.weapons.WeaponType;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.items.properties.ItemRarity;
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

import static com.javisel.aeonspast.utilities.StringKeys.*;

public class WeaponData {

    public static final WeaponData UNARMED = new WeaponData(WeaponType.UNARMED, new StatisticPair(5, 5), new StatisticPair(1, 1), new StatisticPair(5, 5), new StatisticPair(2, 2), new StatisticPair(0, 0), new StatisticPair(4, 4), new ArrayList<>(), new ArrayList<>(), false, ItemRarity.COMMON, new StatisticPair(10, 10));

    public static final String WEAPON_MOD_ID = "4703e862-a7ae-4697-aeea-f58ac8697e10";

    public static final String BASE_STATS = "base_stats";

    public static final double ATTACK_SPEED_OFFSET = -1;

    private final WeaponType weapon_type;
    private final ItemRarity rarity;
    private final StatisticPair attack_damage;
    private final StatisticPair attack_speed;
    private final StatisticPair critical_chance;
    private final StatisticPair critical_damage;
    private final StatisticPair status_chance;

    private final StatisticPair durability;
    private final StatisticPair range;
    private final List<String> properties = new ArrayList<>();
    private final List<String> spells = new ArrayList<>();
    private final boolean is_ranged;
    private StatisticPair dps;


    public WeaponData(WeaponType weapon_type, StatisticPair attack_damage, StatisticPair attack_speed, StatisticPair critical_chance, StatisticPair critical_damage, StatisticPair durability, StatisticPair range, List<String> properties, List<String> spells, boolean is_ranged, ItemRarity item_rarity, StatisticPair status_chance) {
        this.weapon_type = weapon_type;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
        this.critical_chance = critical_chance;
        this.critical_damage = critical_damage;
        this.durability = durability;
        this.range = range;
        this.is_ranged = is_ranged;
        this.rarity = item_rarity;
        this.status_chance = status_chance;


        for (String property : properties) {

            this.properties.add(property);


        }
        for (String spell : spells) {

            this.spells.add(spell);


        }


    }

    public static WeaponData fromNBT(CompoundTag tag) {

        CompoundTag statTag = tag.getCompound(STATISTICS);
        StatisticPair weapon_power = StatisticPair.fromNBT(statTag.getCompound(WEAPON_POWER));
        StatisticPair attacks_speed = StatisticPair.fromNBT(statTag.getCompound(ATTACK_SPEED));
        StatisticPair crit_chance = StatisticPair.fromNBT(statTag.getCompound(CRITICAL_CHANCE));
        StatisticPair crit_dmg = StatisticPair.fromNBT(statTag.getCompound(CRITICAL_DAMAGE));
        StatisticPair durability = StatisticPair.fromNBT(statTag.getCompound(DURABILITY));
        StatisticPair range = StatisticPair.fromNBT(statTag.getCompound(RANGE));
        StatisticPair stat_chance = StatisticPair.fromNBT(statTag.getCompound(STATUS_CHANCE));

        ArrayList<String> props = new ArrayList<>(tag.getCompound(ITEM_PROPERTIES).getAllKeys());

        ArrayList<String> spells = new ArrayList<>(tag.getCompound(SPELL).getAllKeys());


        ItemRarity rarity = ItemRarity.valueOf(tag.getString(RARITY));


        WeaponType type = WeaponType.valueOf(tag.getString(WEAPON_TYPE));
        WeaponData weaponData = new WeaponData(type, weapon_power, attacks_speed, crit_chance, crit_dmg, durability, range, props, spells, tag.getBoolean(IS_RANGED), rarity, stat_chance);

        return weaponData;


    }

    public CompoundTag toNBT() {

        CompoundTag tag = new CompoundTag();

        CompoundTag stats = new CompoundTag();
        stats.put(WEAPON_POWER, attack_damage.toNBT());
        stats.put(ATTACK_SPEED, attack_speed.toNBT());
        stats.put(CRITICAL_CHANCE, critical_chance.toNBT());
        stats.put(CRITICAL_DAMAGE, critical_damage.toNBT());
        stats.put(DURABILITY, durability.toNBT());
        stats.put(RANGE, range.toNBT());


        tag.put(STATISTICS, stats);
        tag.putString(RARITY, rarity.toString());
        CompoundTag propertyTag = new CompoundTag();


        tag.putString(WEAPON_TYPE, weapon_type.toString());

        for (String property : properties) {


            propertyTag.putString(property, "");


        }
        tag.put(ITEM_PROPERTIES, propertyTag);


        CompoundTag spellTag = new CompoundTag();
        for (String spell : spells) {

            propertyTag.putString(spell, "");


        }
        tag.put(SPELL, spellTag);


        tag.putBoolean(IS_RANGED, is_ranged);


        return tag;

    }

    public void loadToWeapon(@Nullable LivingEntity entity, ItemStack stack) {


        if (stack.getAttributeModifiers(EquipmentSlot.MAINHAND).containsKey(WEAPON_MOD_ID)) {
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


        stack.addAttributeModifier(AttributeRegistration.CRITICAL_DAMAGE.get(), new AttributeModifier(id, BASE_STATS, -2 + critical_damage.roll(luck, random), AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);

        stack.addAttributeModifier(AttributeRegistration.STATUS_CHANCE.get(), new AttributeModifier(id, BASE_STATS, status_chance.roll(luck, random), AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);


        stack.addAttributeModifier(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(id, BASE_STATS, -5 + range.roll(luck, random), AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);


        for (String property : properties) {


            ItemProperty.getPropertyByLocation(new ResourceLocation(property)).applyToItem(stack);


        }


        int i = spells.size();

        int choice = random.nextInt(i);
        ItemEngine.getAeonsPastTag(stack).putString(WEAPON_TYPE,weapon_type.toString());
         ItemEngine.getAeonsPastTag(stack).putString(RARITY, rarity.name());

        ItemEngine.getAeonsPastTag(stack).putString(SPELL, spells.get(choice));
        ItemEngine.getAeonsPastTag(stack).putBoolean(IS_RANGED, is_ranged);
        ItemEngine.getAeonsPastTag(stack).putUUID(UNIQUE_ID, UUID.randomUUID());


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


    public StatisticPair getRange() {
        return range;
    }


    public List<String> getProperties() {
        return properties;
    }

    public List<String> getSpells() {
        return spells;
    }

    public WeaponType getWeapon_type() {
        return weapon_type;
    }


    public StatisticPair getDPS() {


        float low = getAttack_speed().getmin() * getAttack_damage().getmin();
        float high = getAttack_speed().getMax() * getAttack_damage().getMax();


        if (dps == null) {

            dps = new StatisticPair(low, high);
        }

        return dps;
    }


    public boolean isIs_ranged() {
        return is_ranged;
    }

    public StatisticPair getDps() {
        return dps;
    }

    public StatisticPair getStatus_chance() {
        return status_chance;
    }
}
