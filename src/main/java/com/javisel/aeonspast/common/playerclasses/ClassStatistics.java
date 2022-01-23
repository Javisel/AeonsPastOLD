package com.javisel.aeonspast.common.playerclasses;

import com.javisel.aeonspast.common.attributes.APAttributeContainer;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.ArrayList;

public class ClassStatistics {

    private final APAttributeContainer MAX_HEALTH;
    private final APAttributeContainer MOVEMENT_SPEED;
    private final APAttributeContainer HEALTH_REGENERATION;
    private final APAttributeContainer ARMOR;
    private final APAttributeContainer MAGIC_RESIST;
    private final APAttributeContainer MAX_RESOURCE;
    private final APAttributeContainer PHYSICAL_POWER;
    private final APAttributeContainer MAGIC_POWER;
    private final APAttributeContainer RESOURCE_REGENERATION_RATE;





    private final ArrayList<APAttributeContainer> attributeContainers;

    public ClassStatistics(double movementSpeed, double baseHealth, double healthRegeneration, double armor, double magicRes, double maxResource, double resourceRegenRate, double physicalPower, double magicPower) {

        MAX_HEALTH = new APAttributeContainer(Attributes.MAX_HEALTH, new AttributeModifier("62b2a5fe-ce17-417b-ad5c-85629c1b2efd", baseHealth, AttributeModifier.Operation.ADDITION));
        MOVEMENT_SPEED = new APAttributeContainer(Attributes.MOVEMENT_SPEED, new AttributeModifier("6be4d98be-8968-419a-9a31-5ca0683a62c1", movementSpeed, AttributeModifier.Operation.ADDITION));
        HEALTH_REGENERATION = new APAttributeContainer(AttributeRegistration.HEALTH_REGENERATION, new AttributeModifier("62b2a5fe-ce17-417b-ad5c-85629c1b2efd", healthRegeneration, AttributeModifier.Operation.ADDITION));
        ARMOR = new APAttributeContainer(Attributes.ARMOR, new AttributeModifier("62b2a5fe-ce17-417b-ad5c-85629c1b2efd", armor, AttributeModifier.Operation.ADDITION));
        MAGIC_RESIST = new APAttributeContainer(AttributeRegistration.MAGIC_RESISTANCE, new AttributeModifier("62b2a5fe-ce17-417b-ad5c-85629c1b2efd", magicRes, AttributeModifier.Operation.ADDITION));
        MAX_RESOURCE = new APAttributeContainer(AttributeRegistration.MAXIMUM_MANA, new AttributeModifier("62b2a5fe-ce17-417b-ad5c-85629c1b2efd", maxResource, AttributeModifier.Operation.ADDITION));
        RESOURCE_REGENERATION_RATE = new APAttributeContainer(AttributeRegistration.MANA_REGENERATION, new AttributeModifier("62b2a5fe-ce17-417b-ad5c-85629c1b2efd", resourceRegenRate, AttributeModifier.Operation.ADDITION));
        PHYSICAL_POWER = new APAttributeContainer(AttributeRegistration.PHYSICAL_POWER, new AttributeModifier("62b2a5fe-ce17-417b-ad5c-85629c1b2efd", physicalPower, AttributeModifier.Operation.ADDITION));
        MAGIC_POWER = new APAttributeContainer(AttributeRegistration.MAGIC_POWER, new AttributeModifier("62b2a5fe-ce17-417b-ad5c-85629c1b2efd", magicPower, AttributeModifier.Operation.ADDITION));


        attributeContainers = new ArrayList<>();
        attributeContainers.add(MAX_HEALTH);
        attributeContainers.add(MOVEMENT_SPEED);
        attributeContainers.add(HEALTH_REGENERATION);
        attributeContainers.add(ARMOR);
        attributeContainers.add(MAGIC_RESIST);
        attributeContainers.add(MAX_RESOURCE);
        attributeContainers.add(RESOURCE_REGENERATION_RATE);

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

    public APAttributeContainer getMAX_RESOURCE() {
        return MAX_RESOURCE;
    }

    public ArrayList<APAttributeContainer> getAttributeContainers() {
        return attributeContainers;
    }


}