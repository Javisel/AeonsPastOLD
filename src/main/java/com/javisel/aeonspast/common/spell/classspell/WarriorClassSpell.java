package com.javisel.aeonspast.common.spell.classspell;

import com.javisel.aeonspast.common.capabiltiies.IPlayerData;
import com.javisel.aeonspast.common.registration.ClassRegistration;
import com.javisel.aeonspast.common.registration.ResourceRegistration;
import com.javisel.aeonspast.common.spell.ClassSpell;
import com.javisel.aeonspast.common.spell.ICustomUseRequirement;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.APUtilities;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class WarriorClassSpell extends ClassSpell implements ICustomUseRequirement {


    public WarriorClassSpell() {
        super(10, 50, 100, 25);
        setSpellResource(ResourceRegistration.FOOD);
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
        entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE,1000,0));


        if (entity instanceof Player) {

            Player player = (Player) entity;

            IPlayerData playerData = APUtilities.getPlayerData(player);
            int levelmod = playerData.getOrCreatePlayerClass(ClassRegistration.WARRIOR.get()).getLevel();

            player.heal((player.getMaxHealth() / 10) * levelmod);


        }

    }


    @Override
    public boolean canMeetRequirement(LivingEntity entity, SpellStack stack) {
        return entity.getHealth() < entity.getMaxHealth();
    }
}
