package com.javisel.aeonspast.common.spell.spells;

import com.javisel.aeonspast.common.registration.ResourceRegistration;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;

public class LightOfGod extends Spell {
    public LightOfGod() {
        super(2, 20, SpellRank.RANK_5);
        setSpellResource(ResourceRegistration.MANA);

    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {


        if (!entity.getLevel().isClientSide) {




            entity.getLevel().setBlock(entity.getOnPos().above(), Blocks.BEACON.defaultBlockState(), 0);
            entity.setPos(entity.getPosition(0).add(0,1,0));

        }
    }
}
