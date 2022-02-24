package com.javisel.aeonspast.common.spell.spells.basicspell;

import com.javisel.aeonspast.common.effects.ComplexEffect;
import com.javisel.aeonspast.common.effects.ComplexEffectInstance;
import com.javisel.aeonspast.common.registration.EffectRegistration;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

public class Taunt  extends Spell {

    private  static  final UUID PHYSICAL_POWER_BUFF = UUID.fromString("1abf6ae8-059a-4e5f-a253-d0a82811ca5d");
    public Taunt( ) {
        super(1, 1, SpellRank.SKILL_BASIC);
    }



    @Override
    public void cast(LivingEntity entity, SpellStack stack) {

        Level level = entity.getLevel();

      List<Entity> entityList =   level.getEntities(entity,entity.getBoundingBox().inflate(15), Entity::isAlive);


      double power = 0;
      for (Entity target : entityList) {


          if (target instanceof Monster) {


              if (!target.isAlliedTo(entity)) {

                  Monster monster = (Monster) target;

                  monster.setTarget(entity);

                  power+=5;



                  ComplexEffectInstance instance = new ComplexEffectInstance(UUID.randomUUID(), entity.getUUID(), power, 90);

                  ComplexEffect effect = (ComplexEffect) EffectRegistration.TAUNT_DEBUFF.get();
                  effect.addnewComplexInstance(instance, monster);
              }


          }


      }

      if (power > 0) {


          System.out.println("Power: " + power);
          ComplexEffectInstance instance = new ComplexEffectInstance(PHYSICAL_POWER_BUFF, entity.getUUID(), power, 140);

          ComplexEffect effect = (ComplexEffect) EffectRegistration.PHYSICAL_POWER_BUFF.get();
          effect.addnewComplexInstance(instance, entity);


      }


    }
}
