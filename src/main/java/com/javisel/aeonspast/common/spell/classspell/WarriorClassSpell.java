package com.javisel.aeonspast.common.spell.classspell;

import com.javisel.aeonspast.common.capabiltiies.IPlayerData;
import com.javisel.aeonspast.common.registration.ClassRegistration;
import com.javisel.aeonspast.common.spell.ClassSpell;
import com.javisel.aeonspast.common.spell.ICustomUseRequirement;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.APUtilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WarriorClassSpell extends ClassSpell implements ICustomUseRequirement {





    public WarriorClassSpell() {
        super(2,600,2, 25);
    }


    @Override
    public void cast(LivingEntity entity, SpellStack stack) {


        Level level = entity.getLevel();


            entity.heal(entity.getMaxHealth() * 0.025f);


            if (entity instanceof Player) {

                Player player = (Player) entity;

                IPlayerData playerData = APUtilities.getPlayerData(player);
              int levelmod =  playerData.getOrCreatePlayerClass(ClassRegistration.WARRIOR.get()).getLevel();

              player.heal((player.getMaxHealth() / 100) * levelmod   );




            }

        }


    @Override
    public boolean canMeetRequirement(LivingEntity entity, SpellStack stack) {
        return entity.getHealth() < entity.getMaxHealth();
    }
}
