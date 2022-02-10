package com.javisel.aeonspast.common.items.properties.weapon;

import com.javisel.aeonspast.common.combat.damagesource.APEntityDamageSource;
import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypes;
import com.javisel.aeonspast.common.items.properties.WeaponProperty;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

public class Sweeping extends WeaponProperty {


    @Override
    public boolean onHitEntityInHand(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack) {
       if  (super.onHitEntityInHand(attacker, victim, damageInstance, stack)) {

           double sweepScaling = 0.05f + (0.10f * EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SWEEPING_EDGE, stack));

           double bonusPhysicalDamage = damageInstance.preMitigationsAmount * sweepScaling;

           double d0 = attacker.walkDist - attacker.walkDistO;

           boolean flag2 =  attacker.fallDistance > 0.0F && !attacker.isOnGround() && !attacker.onClimbable() && !attacker.isInWater() && !attacker.hasEffect(MobEffects.BLINDNESS) && !attacker.isPassenger();


           boolean canPerformAction = stack.canPerformAction(net.minecraftforge.common.ToolActions.SWORD_SWEEP);


           if ( flag2 && canPerformAction && d0 < attacker.getSpeed())
                for (LivingEntity livingentity : attacker.level.getEntitiesOfClass(LivingEntity.class, victim.getBoundingBox().inflate(1, 0.25, 1))) {
                   if (livingentity != attacker && livingentity != victim && !attacker.isAlliedTo(livingentity) && (!(livingentity instanceof ArmorStand) || !((ArmorStand) livingentity).isMarker()) && attacker.distanceToSqr(livingentity) < 9.0D) {


                       DamageInstance procDamage = new DamageInstance(DamageTypes.SLASH, bonusPhysicalDamage);

                       APEntityDamageSource entityDamageSource = new APEntityDamageSource(attacker instanceof Player ? "player" : "mob", procDamage, attacker);
                       livingentity.hurt(entityDamageSource, (float) bonusPhysicalDamage);


                   }
               }
           if (attacker instanceof Player) {

               Player player = (Player) attacker;

               player.sweepAttack();
           }
return  true;
       }

       return false;
    }
}
