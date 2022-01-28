package com.javisel.aeonspast.common.spell.spells;

import com.javisel.aeonspast.common.registration.ResourceRegistration;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.common.spell.SpellState;
import com.javisel.aeonspast.common.spell.ToggleSpell;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class AssaultGuard extends ToggleSpell {


    private static final String ASSAULT_GUARD_BUFF = "916d2955-8318-45e3-a230-3e74f9af4695";

    public AssaultGuard() {
        super(20, 5, SpellRank.SKILL_BASIC);
        setSpellResource(ResourceRegistration.MANA);

    }


    @Override
    public SpellState toggleState(LivingEntity entity, SpellStack stack) {
        SpellState state = super.toggleState(entity, stack);

        AttributeModifier assaultGuardBuff;

        if (state == SpellState.OFF) {


            Utilities.removeAttributeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE, ASSAULT_GUARD_BUFF);

            assaultGuardBuff = new AttributeModifier(UUID.fromString("916d2955-8318-45e3-a230-3e74f9af4695"), "assaultguardspellbuff", 20, AttributeModifier.Operation.ADDITION);

            entity.getAttribute(Attributes.ARMOR).addPermanentModifier(assaultGuardBuff);


        }


        if (state == SpellState.ON) {


            Utilities.removeAttributeModifierIfPresent(entity, Attributes.ARMOR, ASSAULT_GUARD_BUFF);

            assaultGuardBuff = new AttributeModifier(UUID.fromString("916d2955-8318-45e3-a230-3e74f9af4695"), "assaultguardspellbuff", 30, AttributeModifier.Operation.ADDITION);

            entity.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(assaultGuardBuff);


        }


        return state;
    }
}
