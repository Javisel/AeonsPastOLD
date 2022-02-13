package com.javisel.aeonspast.common.entities.spell;

import com.javisel.aeonspast.common.combat.CombatEngine;
import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.damagesource.APEntityDamageSource;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public abstract class WeaponEntity extends ThrowableItemProjectile {


    private static final EntityDataAccessor<Float> THROW_POWER = SynchedEntityData.defineId(WeaponEntity.class, EntityDataSerializers.FLOAT);
    public WeaponEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected Item getDefaultItem() {
        return super.getItem().getItem();
    }


    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);


        LivingEntity thrower = (LivingEntity) super.getOwner();

        DamageInstance instance = CombatEngine.calculateRangedDamage(thrower,getItem(),  entityData.get(THROW_POWER).floatValue());
        APEntityDamageSource apEntityDamageSource = new APEntityDamageSource("itemthrown",  instance,thrower  );
        hitResult.getEntity().hurt(apEntityDamageSource, (float) instance.preMitigationsAmount);







    }
}
