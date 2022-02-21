package com.javisel.aeonspast.common.spell;

import com.javisel.aeonspast.AeonsPast;
import net.minecraft.world.entity.LivingEntity;
import org.apache.logging.log4j.Level;

public class NoneSpell extends Spell {


    public NoneSpell() {
        super(0, 0, SpellRank.EMPTY);
    }


    @Override
    public boolean canCast(LivingEntity caster, SpellStack stack) {


        return false;
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {

        AeonsPast.LOGGER.log(Level.ERROR, "Should not be able to cast the default none spell");
    }
}
