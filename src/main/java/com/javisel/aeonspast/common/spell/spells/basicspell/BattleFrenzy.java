package com.javisel.aeonspast.common.spell.spells.basicspell;

import com.javisel.aeonspast.common.registration.ResourceRegistration;
import com.javisel.aeonspast.common.spell.ICustomUseRequirement;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class BattleFrenzy extends Spell implements ICustomUseRequirement {

    public BattleFrenzy() {
        super(100, 100, SpellRank.SKILL_BASIC);
        setSpellResource(ResourceRegistration.MANA);

    }


    @Override
    public boolean canMeetRequirement(LivingEntity entity, SpellStack stack) {


        return true;
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {
        Level level = entity.getLevel();

        if (level.isClientSide) {

            level.playSound(null, entity, SoundEvents.GOAT_SCREAMING_HURT, SoundSource.NEUTRAL, 5, 5);


        }

        if (!level.isClientSide) {


            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 60, 0));

        }
    }
}
