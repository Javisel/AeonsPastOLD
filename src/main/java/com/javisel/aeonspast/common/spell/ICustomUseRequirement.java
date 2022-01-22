package com.javisel.aeonspast.common.spell;

import net.minecraft.world.entity.LivingEntity;

public interface ICustomUseRequirement {



    boolean canMeetRequirement(LivingEntity entity, SpellStack stack);


}
