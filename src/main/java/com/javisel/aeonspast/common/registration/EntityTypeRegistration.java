package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.entities.spell.AxeEntity;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypeRegistration {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, AeonsPast.MODID);
    public static  final RegistryObject<EntityType<AxeEntity>> THROWN_AXE = ENTITY_TYPES.register("thrown_axe",() ->EntityType.Builder.<AxeEntity>of(AxeEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(16).updateInterval(10).build(AeonsPast.MODID + ":thrown_axe"));









}
