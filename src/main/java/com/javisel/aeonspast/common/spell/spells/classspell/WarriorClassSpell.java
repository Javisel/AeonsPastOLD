package com.javisel.aeonspast.common.spell.spells.classspell;

import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.registration.ClassRegistration;
import com.javisel.aeonspast.common.registration.ResourceRegistration;
import com.javisel.aeonspast.common.spell.ClassSpell;
import com.javisel.aeonspast.common.spell.ICustomUseRequirement;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class WarriorClassSpell extends ClassSpell implements ICustomUseRequirement {


    public WarriorClassSpell() {
        super(2, 300, 10, 25);
        setSpellResource(ResourceRegistration.MANA);
    }


    @Override
    public void cast(LivingEntity entity, SpellStack stack) {








        Level level = entity.getLevel();




        entity.heal(entity.getMaxHealth() * 0.25f);


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
