package com.javisel.aeonspast.common.items.weapons;

import com.javisel.aeonspast.common.config.StatisticPair;
import com.javisel.aeonspast.common.items.itemproperties.APItemProperties;
import com.javisel.aeonspast.common.items.itemproperties.APItemRarity;
import com.javisel.aeonspast.common.spell.Spell;

import java.util.ArrayList;
import java.util.List;

public class WeaponGenerationStats {

    public final StatisticPair BASE_DAMAGE;
    public final StatisticPair ATTACK_SPEED;
    public final StatisticPair CRITICAL_DAMAGE;
    public final StatisticPair DURABILITY;
    public final StatisticPair ENCHANTABILITY;
    public final StatisticPair RANGE;
    public final APItemRarity RARITY;
    public final List<APItemProperties> properties;
    public final List<Spell> spells;


    public WeaponGenerationStats(StatisticPair BASE_DAMAGE, StatisticPair ATTACK_SPEED, StatisticPair CRITICAL_DAMAGE, StatisticPair DURABILITY, StatisticPair ENCHANTABILITY, StatisticPair RANGE, APItemRarity RARITY, ArrayList<APItemProperties> properties, ArrayList<Spell> spells) {
        this.BASE_DAMAGE = BASE_DAMAGE;
        this.ATTACK_SPEED = ATTACK_SPEED;
        this.CRITICAL_DAMAGE = CRITICAL_DAMAGE;
        this.DURABILITY = DURABILITY;
        this.ENCHANTABILITY = ENCHANTABILITY;
        this.RANGE = RANGE;
        this.RARITY = RARITY;
        this.properties = properties;
        this.spells = spells;
    }







}
