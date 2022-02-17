package com.javisel.aeonspast.common.spell.spells.classspell;

import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.particles.WorldTextOptions;
import com.javisel.aeonspast.common.registration.ClassRegistration;
import com.javisel.aeonspast.common.registration.ResourceRegistration;
import com.javisel.aeonspast.common.spell.ClassSpell;
import com.javisel.aeonspast.common.spell.ICustomUseRequirement;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class WarriorClassSpell extends ClassSpell implements ICustomUseRequirement {


    public WarriorClassSpell() {
        super(2, 600, 10, 25);
     }


    @Override
    public void cast(LivingEntity entity, SpellStack stack) {


        Level level = entity.getLevel();




        entity.heal(entity.getMaxHealth() * 0.25f);


        entity.getLevel().playLocalSound(entity.getX(),entity.getY(),entity.getZ(), SoundEvents.FIRE_EXTINGUISH, SoundSource.NEUTRAL,1,1,false);

        if (!level.isClientSide) {

            ServerLevel serverLevel = (ServerLevel) level;




        }

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
