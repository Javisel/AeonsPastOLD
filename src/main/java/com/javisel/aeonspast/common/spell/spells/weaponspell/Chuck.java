package com.javisel.aeonspast.common.spell.spells.weaponspell;

import com.javisel.aeonspast.common.entities.spell.AxeEntity;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.registration.EntityTypeRegistration;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Chuck extends WeaponSpell {

    public Chuck() {
        super(1, 1, SpellRank.WEAPON_SPELL);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {



        Level level = entity.level;

        if (!level.isClientSide) {

            ItemStack weapon = entity.getMainHandItem().copy();

            ServerLevel serverLevel = (ServerLevel) level;

            AxeEntity axeEntity =  new AxeEntity(entity,serverLevel,weapon.copy());
            Utilities.getProjectileData(axeEntity).setShooterItem(weapon);
             axeEntity.shootFromRotation(entity,entity.getXRot(),entity.getYRot(),0,1.5f,0f);
            axeEntity.setXRot(entity.getXRot());
            axeEntity.setYRot(entity.getYRot());
            axeEntity.setThrowPower(2f);

            level.addFreshEntity(axeEntity);
          //  entity.setItemInHand(InteractionHand.MAIN_HAND,ItemStack.EMPTY);
        }


    }
}
