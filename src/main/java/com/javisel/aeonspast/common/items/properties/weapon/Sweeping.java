package com.javisel.aeonspast.common.items.properties.weapon;

import com.javisel.aeonspast.common.combat.CombatEngine;
import com.javisel.aeonspast.common.combat.damage.instances.DamageFlags;
import com.javisel.aeonspast.common.combat.damage.instances.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.combat.damage.sources.APEntityDamageSource;
import com.javisel.aeonspast.common.items.properties.WeaponProperty;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
        if (super.onHitEntityInHand(attacker, victim, damageInstance, stack)) {

            if (damageInstance.flags.contains(DamageFlags.SWEEPED)) {
                return true;
            }

            double sweepScaling = 0.10f + (0.10f * EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SWEEPING_EDGE, stack));

            double bonusPhysicalDamage = damageInstance.getPreMitigatedValue() * sweepScaling;

            double d0 = attacker.walkDist - attacker.walkDistO;

            boolean flag2 = attacker.fallDistance > 0.0F && !attacker.isOnGround() && !attacker.onClimbable() && !attacker.isInWater() && !attacker.hasEffect(MobEffects.BLINDNESS) && !attacker.isPassenger();


            boolean canPerformAction = stack.canPerformAction(net.minecraftforge.common.ToolActions.SWORD_SWEEP);


            if (!flag2 && canPerformAction && d0 < attacker.getSpeed())
                for (LivingEntity livingentity : attacker.level.getEntitiesOfClass(LivingEntity.class, victim.getBoundingBox().inflate(1, 0.25, 1))) {
                    if (livingentity != attacker && livingentity != victim && !attacker.isAlliedTo(livingentity) && (!(livingentity instanceof ArmorStand) || !((ArmorStand) livingentity).isMarker()) && attacker.distanceToSqr(livingentity) < 9.0D) {


                        DamageInstance procDamage =  DamageInstance.getGenericProcInstance(DamageTypeEnum.SLASH, bonusPhysicalDamage);

                        procDamage.setProcWeaponEffects();
                        procDamage.flags.add(DamageFlags.SWEEPED);


                        APEntityDamageSource entityDamageSource = new APEntityDamageSource(attacker instanceof Player ? "player" : "mob", procDamage, attacker);

                        CombatEngine.cycleAllHitEffects(attacker, livingentity, entityDamageSource);


                        livingentity.hurt(entityDamageSource, (float) bonusPhysicalDamage);


                    }


                    victim.getLevel().playLocalSound(victim.getX(), victim.getY(), victim.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.NEUTRAL, 1, 1, true);

                    if (attacker instanceof Player) {

                        Player player = (Player) attacker;

                        player.sweepAttack();
                    }
                }


            return true;
        }

        return false;
    }
}
