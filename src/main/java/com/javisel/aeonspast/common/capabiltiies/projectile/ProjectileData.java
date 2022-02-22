package com.javisel.aeonspast.common.capabiltiies.projectile;

import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.utilities.StringKeys;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import static com.javisel.aeonspast.utilities.StringKeys.*;

public class ProjectileData implements IProjectileData {



    private float damageAmount = 0;
    private String damageType = "";
    ItemStack shooterItem = ItemStack.EMPTY;



    @Override
    public void readNBT(CompoundTag tag) {

        damageAmount=tag.getFloat(BASE_DAMAGE);
        damageType=tag.getString(DAMAGE_TYPE);
        if (tag.contains(SHOOTER_ITEM)) {
           shooterItem = ItemStack.of(tag.getCompound(SHOOTER_ITEM));
        }
    }

    @Override
    public CompoundTag writeNBT() {

        CompoundTag tag = new CompoundTag();
        CompoundTag itemTag = new CompoundTag();
        shooterItem.save(itemTag);
        tag.put(SHOOTER_ITEM,itemTag);

        tag.putFloat(StringKeys.BASE_DAMAGE,damageAmount);

        tag.putString(DAMAGE_TYPE,damageType);

        return null;
    }

    public float getDamageAmount() {
        return damageAmount;
    }

    public void setDamageAmount(float damageAmount) {
        this.damageAmount = damageAmount;
    }

    public DamageTypeEnum getDamageType() {
        return DamageTypeEnum.valueOf(damageType);
    }

    public void setDamageType(DamageTypeEnum damageType) {
        this.damageType = damageType.toString();
    }


    @Override
    public ItemStack getShooterItem() {
        return shooterItem;
    }

    @Override
    public void setShooterItem(ItemStack shooterItem) {
        this.shooterItem = shooterItem;
    }
}
