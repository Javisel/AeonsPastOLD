package com.javisel.aeonspast.common.playerclasses;

import com.javisel.aeonspast.common.attributes.APAttributeContainer;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.ArrayList;

public class ClassStatistics {

    public static final String BASE_STATISTICS = "7284a361-5491-45a7-8b40-0480a894079d";
    private final APAttributeContainer MAX_HEALTH;
    private final APAttributeContainer MOVEMENT_SPEED;
    private final APAttributeContainer HEALTH_REGENERATION;
    private final APAttributeContainer ARMOR;
    private final APAttributeContainer MAGIC_RESIST;
    private final APAttributeContainer MAX_RESOURCE;
    private final APAttributeContainer PHYSICAL_POWER;
    private final APAttributeContainer MAGIC_POWER;
    private final float resourceRegen;
    private final float maxResource;





    private final ArrayList<APAttributeContainer> attributeContainers;

    public ClassStatistics(double movementSpeed, double baseHealth, double healthRegeneration, double armor, double magicRes, double maxResource, double resourceRegenRate, double physicalPower, double magicPower) {

        MAX_HEALTH = new APAttributeContainer(Attributes.MAX_HEALTH, new AttributeModifier(BASE_STATISTICS, baseHealth, AttributeModifier.Operation.ADDITION));
        MOVEMENT_SPEED = new APAttributeContainer(Attributes.MOVEMENT_SPEED, new AttributeModifier(BASE_STATISTICS, movementSpeed, AttributeModifier.Operation.ADDITION));
        HEALTH_REGENERATION = new APAttributeContainer(AttributeRegistration.HEALTH_REGENERATION, new AttributeModifier(BASE_STATISTICS, healthRegeneration, AttributeModifier.Operation.ADDITION));
        ARMOR = new APAttributeContainer(Attributes.ARMOR, new AttributeModifier(BASE_STATISTICS, armor, AttributeModifier.Operation.ADDITION));
        MAGIC_RESIST = new APAttributeContainer(AttributeRegistration.MAGIC_RESISTANCE, new AttributeModifier(BASE_STATISTICS, magicRes, AttributeModifier.Operation.ADDITION));
        MAX_RESOURCE = new APAttributeContainer(AttributeRegistration.MAXIMUM_MANA, new AttributeModifier(BASE_STATISTICS, maxResource, AttributeModifier.Operation.ADDITION));
         PHYSICAL_POWER = new APAttributeContainer(AttributeRegistration.PHYSICAL_POWER, new AttributeModifier(BASE_STATISTICS, physicalPower, AttributeModifier.Operation.ADDITION));
        MAGIC_POWER = new APAttributeContainer(AttributeRegistration.MAGIC_POWER, new AttributeModifier(BASE_STATISTICS, magicPower, AttributeModifier.Operation.ADDITION));


        attributeContainers = new ArrayList<>();
        attributeContainers.add(MAX_HEALTH);
        attributeContainers.add(MOVEMENT_SPEED);
        attributeContainers.add(HEALTH_REGENERATION);
        attributeContainers.add(ARMOR);
        attributeContainers.add(MAGIC_RESIST);
        attributeContainers.add(MAX_RESOURCE);

        this.maxResource= (float) maxResource;
        this.resourceRegen= (float) resourceRegenRate;

    }


    public APAttributeContainer getMAX_HEALTH() {
        return MAX_HEALTH;
    }

    public APAttributeContainer getMOVEMENT_SPEED() {
        return MOVEMENT_SPEED;
    }

    public APAttributeContainer getHEALTH_REGENERATION() {
        return HEALTH_REGENERATION;
    }

    public APAttributeContainer getARMOR() {
        return ARMOR;
    }

    public APAttributeContainer getMAGIC_RESIST() {
        return MAGIC_RESIST;
    }



    public ArrayList<APAttributeContainer> getAttributeContainers() {
        return attributeContainers;
    }


    public APAttributeContainer getMAX_RESOURCE() {
        return MAX_RESOURCE;
    }

    public APAttributeContainer getPHYSICAL_POWER() {
        return PHYSICAL_POWER;
    }

    public APAttributeContainer getMAGIC_POWER() {
        return MAGIC_POWER;
    }

    public float getResourceRegen() {
        return resourceRegen;
    }

    public float getMaxResource() {
        return maxResource;
    }
}