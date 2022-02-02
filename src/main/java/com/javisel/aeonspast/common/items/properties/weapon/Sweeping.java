package com.javisel.aeonspast.common.items.properties.weapon;

import com.javisel.aeonspast.common.combat.APDamageSource;
import com.javisel.aeonspast.common.combat.APEntityDamageSource;
import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.damagetypes.APDamageSubType;
import com.javisel.aeonspast.common.items.properties.WeaponProperty;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

public class Sweeping extends WeaponProperty {


    @Override
    public void onHitEntity(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance, ItemStack stack) {
        super.onHitEntity(attacker, victim, damageInstance, stack);

        double sweepScaling = 0.5f + (0.10f * EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SWEEPING_EDGE,stack));

        double bonusPhysicalDamage = damageInstance.amount * sweepScaling;


        for(LivingEntity livingentity : attacker.level.getEntitiesOfClass(LivingEntity.class,  victim.getBoundingBox().inflate(1,0.25,1))) {
            if (livingentity != attacker && livingentity != victim && !attacker.isAlliedTo(livingentity) && (!(livingentity instanceof ArmorStand) || !((ArmorStand)livingentity).isMarker()) && attacker.distanceToSqr(livingentity) < 9.0D) {


                DamageInstance procDamage = DamageInstance.genericProcDamage(APDamageSubType.PHYSICAL,bonusPhysicalDamage);

                APEntityDamageSource entityDamageSource = new APEntityDamageSource(attacker instanceof Player ? "player" : "mob",procDamage,attacker);
                livingentity.hurt(entityDamageSource, (float) bonusPhysicalDamage);





            }
        }


    }
}
