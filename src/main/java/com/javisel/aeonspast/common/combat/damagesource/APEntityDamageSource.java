package com.javisel.aeonspast.common.combat.damagesource;

import com.javisel.aeonspast.common.combat.DamageInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class APEntityDamageSource extends  APDamageSource {


     private Entity sourceEntity;


    public APEntityDamageSource(String id, DamageInstance instance, Entity sourceEntity) {
        super(sourceEntity instanceof Player ? "player" : "mob",instance );
         this.sourceEntity=sourceEntity;

    }



    @Nullable
    @Override
    public Entity getDirectEntity() {
        return sourceEntity;
    }




}
