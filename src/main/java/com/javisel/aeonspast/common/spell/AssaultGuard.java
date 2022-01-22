package com.javisel.aeonspast.common.spell;

import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.utilities.APUtilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class AssaultGuard extends ToggleSpell {


    private static final String ASSAULT_GUARD_BUFF = "916d2955-8318-45e3-a230-3e74f9af4695";
    public AssaultGuard() {
        super(20, 5, SpellRank.SKILL_BASIC);
    }


    @Override
    public SpellState toggleState(LivingEntity entity, SpellStack stack) {
        SpellState state = super.toggleState(entity, stack);

        AttributeModifier assaultGuardBuff;

        if (state == SpellState.OFF) {


            APUtilities.removeAttributeModifierIfPresent(entity, Attributes.ATTACK_DAMAGE,ASSAULT_GUARD_BUFF );

            assaultGuardBuff = new AttributeModifier(UUID.fromString("916d2955-8318-45e3-a230-3e74f9af4695"),"assaultguardspellbuff",20, AttributeModifier.Operation.ADDITION);

            entity.getAttribute(Attributes.ARMOR).addPermanentModifier(assaultGuardBuff);




        }


        if (state == SpellState.ON) {


            APUtilities.removeAttributeModifierIfPresent(entity, Attributes.ARMOR,ASSAULT_GUARD_BUFF );

            assaultGuardBuff = new AttributeModifier(UUID.fromString("916d2955-8318-45e3-a230-3e74f9af4695"),"assaultguardspellbuff",30, AttributeModifier.Operation.ADDITION);

            entity.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(assaultGuardBuff);




        }






        return  state;
    }
}
