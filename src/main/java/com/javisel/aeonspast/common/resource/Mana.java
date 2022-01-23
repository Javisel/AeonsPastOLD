package com.javisel.aeonspast.common.resource;

import com.javisel.aeonspast.common.registration.AttributeRegistration;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.registries.RegistryObject;

public class Mana extends Resource {
    protected Mana(RegistryObject<Attribute> resourceMaxAttribute, RegistryObject<Attribute> resourceRegenAttribute) {
        super(AttributeRegistration.MAXIMUM_MANA, AttributeRegistration.MANA_REGENERATION);
    }





}
