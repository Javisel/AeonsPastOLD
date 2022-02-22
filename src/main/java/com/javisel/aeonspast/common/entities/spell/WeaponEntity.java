package com.javisel.aeonspast.common.entities.spell;

import com.javisel.aeonspast.common.combat.CombatEngine;
import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.damagesource.APEntityDamageSource;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.registration.EntityTypeRegistration;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public abstract class WeaponEntity extends ThrowableItemProjectile {


    private static final EntityDataAccessor<Float> THROW_POWER = SynchedEntityData.defineId(WeaponEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(WeaponEntity.class, EntityDataSerializers.FLOAT);




    public WeaponEntity(EntityType  entityType, Level level) {
        super((EntityType<? extends ThrowableItemProjectile>)entityType, level);
    }

    public WeaponEntity(LivingEntity owner, ItemStack stack, EntityType<AxeEntity> axeEntityEntityType, Level level) {
        super(EntityTypeRegistration.THROWN_AXE.get(),owner,level);
        setItem(stack);
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

            this.getEntityData().define(THROW_POWER,1f);
        this.getEntityData().define(DAMAGE,0f);


    }

    @Override
    protected Item getDefaultItem() {
        return Items.DIAMOND_SWORD;
    }


    public void setThrowPower(float power) {

        entityData.set(THROW_POWER,power);

    }



    public void setDamage(float damage) {

        entityData.set(DAMAGE,damage);

    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);



         LivingEntity thrower = (LivingEntity) super.getOwner();

        DamageInstance instance = CombatEngine.calculateThrownDamage(thrower, this, getItem(), entityData.get(THROW_POWER));
        instance.preMitigationsAmount+=this.entityData.get(DAMAGE);

        instance.damage_type= ItemEngine.getItemDamageType(getItem());
        APEntityDamageSource apEntityDamageSource = new APEntityDamageSource("itemthrown", instance, thrower);
        hitResult.getEntity().hurt(apEntityDamageSource, (float) instance.preMitigationsAmount);
        ItemEntity itemEntity  =new ItemEntity(super.level,super.xo,super.yo,super.zo,this.getItem());
        itemEntity.setDefaultPickUpDelay();
        if (this.getOwner()!=null) {

            itemEntity.setOwner(this.getOwner().getUUID());
            itemEntity.setThrower(this.getOwner().getUUID());

        }


        level.addFreshEntity(itemEntity);
        this.remove(RemovalReason.DISCARDED);


    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        super.onHitBlock(p_37258_);

        ItemEntity itemEntity  =new ItemEntity(super.level,super.xo,super.yo,super.zo,this.getItem());
        itemEntity.setDefaultPickUpDelay();
        if (this.getOwner()!=null) {

            itemEntity.setOwner(this.getOwner().getUUID());
            itemEntity.setThrower(this.getOwner().getUUID());

        }


        level.addFreshEntity(itemEntity);
        this.remove(RemovalReason.DISCARDED);





    }

    @Override
    protected void updateRotation() {
        super.updateRotation();
    }
}
