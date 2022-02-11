package com.javisel.aeonspast.common.playerclasses;

import com.javisel.aeonspast.common.attributes.AttributeContainer;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClassData {

    public static final String BASE_STATISTICS = "7284a361-5491-45a7-8b40-0480a894079d";
    public static final String LEVEL_BONUS_STATISTICS = "b26e7562-dba6-438d-9f13-207dd29c96e0";
    public static final String BASE_ID = "base_id";
    public static final String LEVEL_ID = "level_id";

    private final double max_health;
    private final double max_health_scaling;
    private final double health_regeneration;
    private final double health_regeneration_scaling;
    private final double armor;
    private final double armor_scaling;
    private final double magic_resist;
    private final double magic_resist_scaling;
    private final double max_resource;
    private final double max_resource_scaling;
    private final double resource_regeneration;
    private final double resource_regeneration_scaling;
    private final double movement_speed;
    private final double movement_speed_scaling;

    private final List<String> spells = new ArrayList<>();
    private final List<String> weapon_types = new ArrayList<>();

    public ClassData(double max_health, double max_health_scaling, double health_regeneration, double health_regeneration_scaling, double armor, double armor_scaling, double magic_resist, double magic_resist_scaling, double max_resource, double max_resource_scaling, double resource_regeneration, double resource_regeneration_scaling, double movement_speed, double movement_speed_scaling, List<String> spells, List<String> weapon_types) {
        this.max_health = max_health;
        this.max_health_scaling = max_health_scaling;
        this.health_regeneration = health_regeneration;
        this.health_regeneration_scaling = health_regeneration_scaling;
        this.armor = armor;
        this.armor_scaling = armor_scaling;
        this.magic_resist = magic_resist;
        this.magic_resist_scaling = magic_resist_scaling;
        this.max_resource = max_resource;
        this.max_resource_scaling = max_resource_scaling;
        this.resource_regeneration = resource_regeneration;
        this.resource_regeneration_scaling = resource_regeneration_scaling;
        this.movement_speed = movement_speed;
        this.movement_speed_scaling = movement_speed_scaling;


        this.spells.addAll(spells);
        this.weapon_types.addAll(weapon_types);


    }

    public static String getBaseStatistics() {
        return BASE_STATISTICS;
    }

    public static String getLevelBonusStatistics() {
        return LEVEL_BONUS_STATISTICS;
    }

    public ArrayList<AttributeContainer> getAttributeModifiers(Resource resource) {

        ArrayList<AttributeContainer> attributeContainers = new ArrayList<>();

        attributeContainers.add(AttributeContainer.withUUID(Attributes.MAX_HEALTH, UUID.fromString(BASE_STATISTICS), max_health, AttributeModifier.Operation.ADDITION));
        attributeContainers.add(AttributeContainer.withUUID(Attributes.MAX_HEALTH, UUID.fromString(LEVEL_BONUS_STATISTICS), max_health_scaling, AttributeModifier.Operation.ADDITION));
        attributeContainers.add(AttributeContainer.withUUID(AttributeRegistration.HEALTH_REGENERATION.get(), UUID.fromString(BASE_STATISTICS), health_regeneration, AttributeModifier.Operation.ADDITION));
        attributeContainers.add(AttributeContainer.withUUID(AttributeRegistration.HEALTH_REGENERATION.get(), UUID.fromString(LEVEL_BONUS_STATISTICS), health_regeneration_scaling, AttributeModifier.Operation.ADDITION));
        attributeContainers.add(AttributeContainer.withUUID(Attributes.ARMOR, UUID.fromString(BASE_STATISTICS), armor, AttributeModifier.Operation.ADDITION));
        attributeContainers.add(AttributeContainer.withUUID(Attributes.ARMOR, UUID.fromString(LEVEL_BONUS_STATISTICS), armor_scaling, AttributeModifier.Operation.ADDITION));
        attributeContainers.add(AttributeContainer.withUUID(AttributeRegistration.MAGIC_RESISTANCE.get(), UUID.fromString(BASE_STATISTICS), magic_resist, AttributeModifier.Operation.ADDITION));
        attributeContainers.add(AttributeContainer.withUUID(AttributeRegistration.MAGIC_RESISTANCE.get(), UUID.fromString(LEVEL_BONUS_STATISTICS), magic_resist_scaling, AttributeModifier.Operation.ADDITION));


        if (resource != null) {


            attributeContainers.add(AttributeContainer.withUUID(resource.getResourceMaxAttribute().get(), UUID.fromString(BASE_STATISTICS), max_resource, AttributeModifier.Operation.ADDITION));

            attributeContainers.add(AttributeContainer.withUUID(resource.getResourceMaxAttribute().get(), UUID.fromString(LEVEL_BONUS_STATISTICS), max_resource_scaling, AttributeModifier.Operation.ADDITION));


            attributeContainers.add(AttributeContainer.withUUID(resource.getResourceRegenAttribute().get(), UUID.fromString(BASE_STATISTICS), resource_regeneration, AttributeModifier.Operation.ADDITION));
            attributeContainers.add(AttributeContainer.withUUID(resource.getResourceRegenAttribute().get(), UUID.fromString(LEVEL_BONUS_STATISTICS), resource_regeneration_scaling, AttributeModifier.Operation.ADDITION));

        }


        attributeContainers.add(AttributeContainer.withUUID(Attributes.MOVEMENT_SPEED, UUID.fromString(BASE_STATISTICS), movement_speed, AttributeModifier.Operation.ADDITION));
        attributeContainers.add(AttributeContainer.withUUID(Attributes.MOVEMENT_SPEED, UUID.fromString(LEVEL_BONUS_STATISTICS), movement_speed_scaling, AttributeModifier.Operation.ADDITION));


        return attributeContainers;
    }


    public ArrayList<AttributeContainer> getLevelModifiers(Resource resource, int level) {

        ArrayList<AttributeContainer> attributeContainers = new ArrayList<>();

         attributeContainers.add(AttributeContainer.withUUID(Attributes.MAX_HEALTH, UUID.fromString(LEVEL_BONUS_STATISTICS), max_health_scaling * (level-1), AttributeModifier.Operation.ADDITION));
         attributeContainers.add(AttributeContainer.withUUID(AttributeRegistration.HEALTH_REGENERATION.get(), UUID.fromString(LEVEL_BONUS_STATISTICS), health_regeneration_scaling* (level-1), AttributeModifier.Operation.ADDITION));
         attributeContainers.add(AttributeContainer.withUUID(Attributes.ARMOR, UUID.fromString(LEVEL_BONUS_STATISTICS), armor_scaling* (level-1), AttributeModifier.Operation.ADDITION));
         attributeContainers.add(AttributeContainer.withUUID(AttributeRegistration.MAGIC_RESISTANCE.get(), UUID.fromString(LEVEL_BONUS_STATISTICS), magic_resist_scaling* (level-1), AttributeModifier.Operation.ADDITION));


        if (resource != null) {



            attributeContainers.add(AttributeContainer.withUUID(resource.getResourceMaxAttribute().get(), UUID.fromString(LEVEL_BONUS_STATISTICS), max_resource_scaling* (level-1), AttributeModifier.Operation.ADDITION));


             attributeContainers.add(AttributeContainer.withUUID(resource.getResourceRegenAttribute().get(), UUID.fromString(LEVEL_BONUS_STATISTICS), resource_regeneration_scaling* (level-1), AttributeModifier.Operation.ADDITION));

        }


         attributeContainers.add(AttributeContainer.withUUID(Attributes.MOVEMENT_SPEED, UUID.fromString(LEVEL_BONUS_STATISTICS), movement_speed_scaling* (level-1), AttributeModifier.Operation.ADDITION));


        return attributeContainers;
    }





    public void addAttributeToEntity(Player entity, Attribute attribute, double baseValue, double scaleValue) {


        double appliedBase = baseValue;
        double appliedScale = scaleValue;




        entity.getAttribute(attribute).removeModifier(UUID.fromString(BASE_STATISTICS));
        entity.getAttribute(attribute).addPermanentModifier(new AttributeModifier(UUID.fromString(BASE_STATISTICS), BASE_ID, appliedBase, AttributeModifier.Operation.ADDITION));
        int level = Utilities.getEntityData(entity).getLevel();
        entity.getAttribute(attribute).removeModifier(UUID.fromString(LEVEL_BONUS_STATISTICS));

        entity.getAttribute(attribute).addPermanentModifier(new AttributeModifier(UUID.fromString(LEVEL_BONUS_STATISTICS), LEVEL_ID, appliedScale * (level - 1), AttributeModifier.Operation.ADDITION));



    }











    public double getMax_health() {
        return max_health;
    }

    public double getMax_health_scaling() {
        return max_health_scaling;
    }

    public double getHealth_regeneration() {
        return health_regeneration;
    }

    public double getHealth_regeneration_scaling() {
        return health_regeneration_scaling;
    }

    public double getArmor() {
        return armor;
    }

    public double getArmor_scaling() {
        return armor_scaling;
    }

    public double getMagic_resist() {
        return magic_resist;
    }

    public double getMagic_resist_scaling() {
        return magic_resist_scaling;
    }

    public double getMax_resource() {
        return max_resource;
    }

    public double getMax_resource_scaling() {
        return max_resource_scaling;
    }

    public double getResource_regeneration() {
        return resource_regeneration;
    }

    public double getResource_regeneration_scaling() {
        return resource_regeneration_scaling;
    }

    public double getMovement_speed() {
        return movement_speed;
    }

    public double getMovement_speed_scaling() {
        return movement_speed_scaling;
    }

    public List<String> getSpells() {
        return spells;
    }

    public List<String> getWeapon_types() {
        return weapon_types;
    }


    public boolean hasSpell(Spell spell) {
        ResourceLocation location = spell.getRegistryName();

        for (String resource : spells) {

            if (resource.equals(location.toString())) {

                return true;
            }


        }

        return false;
    }


    public boolean hasWeaponType(String weaponType) {
        ResourceLocation location = new ResourceLocation(weaponType);

        for (String resource : weapon_types) {

            if (resource.equals(location.toString())) {

                return true;
            }


        }

        return false;
    }

}

