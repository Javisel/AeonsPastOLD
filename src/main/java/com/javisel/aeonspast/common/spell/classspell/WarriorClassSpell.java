package com.javisel.aeonspast.common.spell.classspell;

import com.javisel.aeonspast.GameEventHandler;
import com.javisel.aeonspast.common.attributes.AttributeContainer;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.playerclasses.ClassData;
import com.javisel.aeonspast.common.registration.ClassRegistration;
import com.javisel.aeonspast.common.registration.ResourceRegistration;
import com.javisel.aeonspast.common.spell.ClassSpell;
import com.javisel.aeonspast.common.spell.ICustomUseRequirement;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Map;

import static com.javisel.aeonspast.GameEventHandler.WEAPON_STATISTICS_LOADER;

public class WarriorClassSpell extends ClassSpell implements ICustomUseRequirement {


    public WarriorClassSpell() {
        super(2, 300, 10, 25);
        setSpellResource(ResourceRegistration.MANA);
    }


    @Override
    public void cast(LivingEntity entity, SpellStack stack) {








        Level level = entity.getLevel();


        if (level.isClientSide) {


            System.out.println("CLIENT READ!");
        }

        if (!level.isClientSide) {


            System.out.println("SERVER READ!");
        }


        entity.heal(entity.getMaxHealth() * 0.25f);
        entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1000, 0));


        entity.getLevel().playLocalSound(entity.getX(),entity.getY(),entity.getZ(), SoundEvents.FIRE_EXTINGUISH, SoundSource.NEUTRAL,1,1,false);


        //TODO spawn particles


        if (entity instanceof Player) {

            Player player = (Player) entity;

            IPlayerData playerData = Utilities.getPlayerData(player);
            int levelmod = playerData.getOrCreatePlayerClass(ClassRegistration.WARRIOR.get()).getLevel();

            player.heal((player.getMaxHealth() / 10) * levelmod);


        }


    }


    @Override
    public boolean canMeetRequirement(LivingEntity entity, SpellStack stack) {
        return entity.getHealth() < entity.getMaxHealth();
    }
}
