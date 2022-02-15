package com.javisel.aeonspast.common.combat.damagesource;

import com.javisel.aeonspast.common.combat.DamageInstance;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

public class APEntityDamageSource extends  APDamageSource {


     private Entity directEntity;
     private Entity sourceEntity;


     //Melee Instances
    public APEntityDamageSource(String id, DamageInstance instance, Entity sourceEntity) {
        super(id,instance );
         this.directEntity =sourceEntity;
        this.sourceEntity=directEntity;
    }

    //Ranged Instances
    public APEntityDamageSource(String id, DamageInstance instance, Entity directEntity, Entity sourceEntity) {
        super(id,instance );
        this.directEntity =directEntity;
        this.sourceEntity=sourceEntity;
    }

    @Nullable
    @Override
    public Entity getDirectEntity() {
        return directEntity;
    }


    @Nullable
    @Override
    public Entity getEntity() {
        return sourceEntity;
    }
}
