package com.javisel.aeonspast.common.spell.spells;

import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;

public class LightOfGod extends Spell {
    public LightOfGod() {
        super(2, 1000, SpellRank.RANK_5);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {


        if (!entity.getLevel().isClientSide) {


            entity.getLevel().setBlock(entity.getOnPos().above().above(), Blocks.BEACON.defaultBlockState(), 0);


        }
    }
}
