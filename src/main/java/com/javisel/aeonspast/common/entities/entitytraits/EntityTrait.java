package com.javisel.aeonspast.common.entities.entitytraits;


import com.javisel.aeonspast.ModBusEventHandler;
import com.javisel.aeonspast.common.combat.DamageInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.registries.RegistryManager;

public class EntityTrait extends net.minecraftforge.registries.ForgeRegistryEntry<EntityTrait> {


    public static EntityTrait getTraitByLocation(ResourceLocation resourceLocation) {


        return resourceLocation == null ? null : (EntityTrait) RegistryManager.ACTIVE.getRegistry(ModBusEventHandler.ENTITY_TRAIT_REGISTRY_NAME).getValue(resourceLocation);
    }

    public void onEntitySummoned(LivingEntity entity) {


    }

    public void onEntityDealDamage(LivingEntity attacker, LivingEntity victim, DamageInstance instance) {


    }

    public boolean onEntityTakeDamage(LivingEntity attacker, LivingEntity victim, DamageInstance instance) {


        return true;

    }


}