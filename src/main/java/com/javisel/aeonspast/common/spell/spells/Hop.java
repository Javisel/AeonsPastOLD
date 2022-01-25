package com.javisel.aeonspast.common.spell.spells;

import com.javisel.aeonspast.common.events.DirectHitEvent;
import com.javisel.aeonspast.common.registration.ResourceRegistration;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.Event;

public class Hop extends Spell {

    private static int HIT_COOLDOWN_REDUCTION = 20;

    public Hop() {
        super(2, 15*20, 10, 10, SpellRank.SKILL_BASIC);
        setSpellResource(ResourceRegistration.MANA);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {


        if (entity.getLevel().isClientSide) {



           Vec3 getmotion =  entity.getDeltaMovement() ;


           entity.move(MoverType.SELF,getmotion.multiply(2,2,2));

        }




    }


    @Override
    public void onEventTrigger(LivingEntity entity, SpellStack stack, Event event) {
        super.onEventTrigger(entity, stack, event);


        if (event instanceof LivingDamageEvent) {


            LivingDamageEvent damageEvent = (LivingDamageEvent) event;

             if (damageEvent.getSource().getEntity() !=null) {


                 if (damageEvent.getSource().getDirectEntity()  != null && damageEvent.getSource().getDirectEntity()== entity) {

                    if (stack.isCoolingDown()) {

                        super.addToCooldown(HIT_COOLDOWN_REDUCTION * -1, stack, entity);
                        return;
                    }

                    if (stack.isRecharging()) {
                        super.addToCharge(HIT_COOLDOWN_REDUCTION * -1, entity, stack);

                        return;

                    }

                }

                 if (damageEvent.getSource().isProjectile()) {


                     if (damageEvent.getSource().getEntity()  != null && damageEvent.getSource().getEntity()== entity) {

                         if (stack.isCoolingDown()) {

                             super.addToCooldown(HIT_COOLDOWN_REDUCTION * -1, stack, entity);
                             return;
                         }

                         if (stack.isRecharging()) {
                             super.addToCharge(HIT_COOLDOWN_REDUCTION * -1, entity, stack);

                             return;

                         }

                     }




                 }









            }
        }




    }


}
