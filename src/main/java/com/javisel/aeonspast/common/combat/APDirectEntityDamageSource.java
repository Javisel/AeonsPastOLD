package com.javisel.aeonspast.common.combat;

import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

public class APDirectEntityDamageSource extends  APDamageSource {


     private Entity sourceEntity;


    public APDirectEntityDamageSource(String id, DamageInstance instance, Entity sourceEntity) {
        super(sourceEntity instanceof Player ? "player" : "mob",instance );
         this.sourceEntity=sourceEntity;
    }



    @Nullable
    @Override
    public Entity getDirectEntity() {
        return sourceEntity;
    }




}
