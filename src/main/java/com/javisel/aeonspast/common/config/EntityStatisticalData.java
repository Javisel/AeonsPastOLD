package com.javisel.aeonspast.common.config;

import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.mob.IMobData;
import com.javisel.aeonspast.common.entities.entitytraits.EntityTrait;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Slime;

import java.util.List;
import java.util.UUID;

public class EntityStatisticalData {


    public static final UUID BASE_STAT_ID = UUID.fromString("c05b0654-01f1-43a4-a3a5-47b6fc08a479");
    public static final UUID LEVEL_STAT_ID = UUID.fromString("f591249f-7637-4fcb-98b7-064722ce3b2a");

    private final String BASE_STRING = "base_stats";
    private final String LEVEL_STRING = "level_stats";


    private final double attack_damage;
    private final double attack_damage_scaling;
    private final double attack_speed;
    private final double attack_speed_scaling;
    private final double physical_power;
    private final double physical_power_scaling;
    private final double magical_power;
    private final double magical_power_scaling;
    private final double max_health;
    private final double max_health_scaling;
    private final double health_regeneration;
    private final double health_regeneration_scaling;
    private final double armor;
    private final double armor_scaling;
    private final double magic_resist;
    private final double magic_resist_scaling;
    private final double movement_speed;
    private final double movement_speed_scaling;
    private final double base_experience;
    private final double experience_scaling;
    private final List<String> entity_traits;


    public EntityStatisticalData(double attack_damage, double attack_damage_scaling, double attack_speed, double attack_speed_scaling, double physical_power, double physical_power_scaling, double magical_power, double magical_power_scaling, double health, double health_scaling, double health_regeneration, double health_regeneration_scaling, double armor, double armor_scaling, double magic_resist, double magic_resist_scaling, double movement_speed, double movement_speed_scaling, double base_experience, double experience_scaling, List<String> entity_traits) {
        this.attack_damage = attack_damage;
        this.attack_damage_scaling = attack_damage_scaling;
        this.attack_speed = attack_speed;
        this.attack_speed_scaling = attack_speed_scaling;
        this.physical_power = physical_power;
        this.physical_power_scaling = physical_power_scaling;
        this.magical_power = magical_power;
        this.magical_power_scaling = magical_power_scaling;
        this.max_health = health;
        this.max_health_scaling = health_scaling;
        this.health_regeneration = health_regeneration;
        this.health_regeneration_scaling = health_regeneration_scaling;
        this.armor = armor;
        this.armor_scaling = armor_scaling;
        this.magic_resist = magic_resist;
        this.magic_resist_scaling = magic_resist_scaling;
        this.movement_speed = movement_speed;
        this.movement_speed_scaling = movement_speed_scaling;
        this.base_experience = base_experience;
        this.experience_scaling = experience_scaling;
        this.entity_traits = entity_traits;
    }

    public void loadtoEntity(LivingEntity entity) {


        if (entity.getAttribute(AttributeRegistration.WEAPON_POWER.get()).getModifier(BASE_STAT_ID) != null) {

            return;
        }

        addAttributeToEntity(entity, AttributeRegistration.WEAPON_POWER.get(), attack_damage, attack_damage_scaling);

        addAttributeToEntity(entity, Attributes.ATTACK_SPEED, 4 - attack_speed, 4 - attack_speed_scaling);
        addAttributeToEntity(entity, AttributeRegistration.PHYSICAL_POWER.get(), physical_power, physical_power_scaling);
        addAttributeToEntity(entity, AttributeRegistration.SPELL_POWER.get(), magical_power, magical_power_scaling);

        addAttributeToEntity(entity, Attributes.MAX_HEALTH, max_health, max_health_scaling);

        entity.heal(entity.getMaxHealth());
        addAttributeToEntity(entity, AttributeRegistration.HEALTH_REGENERATION.get(), health_regeneration, health_regeneration);
        addAttributeToEntity(entity, AttributeRegistration.ARMOR.get(), armor, armor_scaling);
        addAttributeToEntity(entity, AttributeRegistration.MAGIC_RESISTANCE.get(), magic_resist, magic_resist_scaling);
        addAttributeToEntity(entity, Attributes.MOVEMENT_SPEED, movement_speed, movement_speed_scaling);
        addAttributeToEntity(entity, AttributeRegistration.EXPERIENCE.get(), base_experience, experience_scaling);


        IMobData mobData = Utilities.getMobData((Mob) entity);

        IEntityData entityData = Utilities.getEntityData(entity);
        for (String key : entity_traits) {


            EntityTrait trait = EntityTrait.getTraitByLocation(new ResourceLocation(key));

            mobData.getEntityTraits().add(trait);


        }

        mobData.setExperienceReward((float) (base_experience + (experience_scaling * (1-entityData.getLevel()))));






    }


    public void addAttributeToEntity(LivingEntity entity, Attribute attribute, double baseValue, double scaleValue) {


        double appliedBase = baseValue;
        double appliedScale = scaleValue;
        if (entity instanceof Slime) {
            Slime slime = (Slime) entity;


             appliedBase =   baseValue * ((double) slime.getSize());
            appliedScale  =  scaleValue *  ((double) slime.getSize());

        }



        entity.getAttribute(attribute).addPermanentModifier(new AttributeModifier(BASE_STAT_ID, BASE_STRING, appliedBase, AttributeModifier.Operation.ADDITION));


        int level = Utilities.getEntityData(entity).getLevel();
        if (level != 1) {
            entity.getAttribute(attribute).addPermanentModifier(new AttributeModifier(LEVEL_STAT_ID, LEVEL_STRING, appliedScale * (level - 1), AttributeModifier.Operation.ADDITION));
        }


    }


}
