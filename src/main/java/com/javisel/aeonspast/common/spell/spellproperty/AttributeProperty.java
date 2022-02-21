package com.javisel.aeonspast.common.spell.spellproperty;

import net.minecraft.world.entity.ai.attributes.Attribute;

public class AttributeProperty<T extends Attribute> extends SpellProperty {


    protected AttributeProperty(Attribute attribute) {
        super(attribute);
    }


}
