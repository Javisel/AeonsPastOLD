package com.javisel.aeonspast.common.effects;

import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.registries.RegistryObject;

public interface DamageStatusDebuff {


      DamageTypeEnum getDamageType() ;

      void applyFromDamage(LivingEntity source,LivingEntity target, DamageInstance damageInstance) ;




}
