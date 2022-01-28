package com.javisel.aeonspast.common.spell.spells;

import com.javisel.aeonspast.common.registration.ResourceRegistration;
import com.javisel.aeonspast.common.spell.ICustomUseRequirement;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class StormStrike extends Spell implements ICustomUseRequirement {

    public StormStrike() {
        super(1200, 100, SpellRank.SKILL_ULTIMATE);
        setSpellResource(ResourceRegistration.MANA);

    }


    @Override
    public void cast(LivingEntity entity, SpellStack stack) {


        if (entity instanceof Player) {


            Player player = (Player) entity;


            Level level = entity.level;

            if (!level.isClientSide) {


                LivingEntity entity1 = player.getLastHurtMob();

                LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(level);
                lightningBolt.setCause((ServerPlayer) player);
                lightningBolt.setPos(entity1.getPosition(0));
                level.addFreshEntity(lightningBolt);

            }


        }


    }

    @Override
    public boolean canMeetRequirement(LivingEntity entity, SpellStack stack) {


        LivingEntity lasthit = entity.getLastHurtMob();

        if (lasthit != null) {

            return !lasthit.isDeadOrDying();
        }


        return false;
    }
}
