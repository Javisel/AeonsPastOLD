package com.javisel.aeonspast;


import com.javisel.aeonspast.common.capabiltiies.entity.EntityProvider;
import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.mob.IMobData;
import com.javisel.aeonspast.common.capabiltiies.mob.MobDataProvider;
import com.javisel.aeonspast.common.capabiltiies.player.PlayerProvider;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.combat.CombatEngine;
import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import com.javisel.aeonspast.common.combat.damagesource.APEntityDamageSource;
import com.javisel.aeonspast.common.combat.damagesource.VanillaAPDamageSourceMap;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.particles.WorldTextOptions;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.Random;


@Mod.EventBusSubscriber

public class GameEventHandler {


    @SubscribeEvent
    public static void playerAttack(AttackEntityEvent event) {

        event.setCanceled(true);
        Player player = event.getPlayer();

        if (!(event.getTarget() instanceof net.minecraft.world.entity.LivingEntity)) {
            return;
        }
        float f2 = player.getAttackStrengthScale(0.5F);
        float check = 1 * (0.2F + f2 * f2 * 0.8F);


        if (check < 1.0) {

            return;
        }

        net.minecraft.world.entity.LivingEntity victim = (net.minecraft.world.entity.LivingEntity) event.getTarget();


        DamageInstance instance = CombatEngine.calculateMeleeDamage(player, player.getMainHandItem());
        APEntityDamageSource entityDamageSource = new APEntityDamageSource("player", instance, player);
        instance.doesKnockback=true;

        if (CombatEngine.cycleAllPreHitEffects(player, victim, entityDamageSource)) {
            CombatEngine.cycleAllHitEffects(player, victim, entityDamageSource);

        }
    }


    @SubscribeEvent
    public static void attackEntityEvent(LivingAttackEvent event) {

        LivingEntity victim = event.getEntityLiving();
        Entity attacker = event.getSource().getEntity();
        Entity directEntity = event.getSource().getDirectEntity();

        net.minecraft.world.level.Level level = victim.getLevel();
        DamageSource source = event.getSource();

        if (level.isClientSide) {
            return;
        }



        if (!(event.getSource() instanceof APDamageSource)) {

            event.setCanceled(true);

            APDamageSource damageSource = null;


            if (source instanceof IndirectEntityDamageSource) {

                IndirectEntityDamageSource indirectSource = (IndirectEntityDamageSource) source;
                net.minecraft.world.entity.LivingEntity livingEntity = (net.minecraft.world.entity.LivingEntity) source.getEntity();

                if (indirectSource.getEntity() != null) {

                    float power = 1;
                    Projectile projectile = (Projectile) indirectSource.getDirectEntity();

                    if (indirectSource.getDirectEntity() != null && indirectSource.getDirectEntity() instanceof Projectile) {


                        power = (float) projectile.getDeltaMovement().length();

                        if (power <= 1.6f) {

                            power = 0.10f;

                        } else {

                            power = 3 / power;
                        }

                    }


                    DamageInstance instance = CombatEngine.calculateFiredRangedDamage(livingEntity,projectile , livingEntity.getMainHandItem(), power);


                    APEntityDamageSource entityDamageSource = new APEntityDamageSource("mob", instance, livingEntity);
                    if (CombatEngine.cycleAllPreHitEffects(livingEntity, victim, entityDamageSource)) {
                        CombatEngine.cycleAllHitEffects(livingEntity, victim, entityDamageSource);

                    }

                }


            } else if (source.getEntity() != null && source.getEntity() instanceof net.minecraft.world.entity.LivingEntity) {


                if (!(event.getSource().getEntity() instanceof Player)) {

                    net.minecraft.world.entity.LivingEntity livingEntity = (net.minecraft.world.entity.LivingEntity) source.getEntity();

                    DamageInstance instance = CombatEngine.calculateMeleeDamage(livingEntity, livingEntity.getMainHandItem());
                    APEntityDamageSource entityDamageSource = new APEntityDamageSource("mob", instance, livingEntity);
                    instance.doesKnockback=true;
                    if (CombatEngine.cycleAllPreHitEffects(livingEntity, victim, entityDamageSource)) {
                        CombatEngine.cycleAllHitEffects(livingEntity, victim, entityDamageSource);

                    }
                }
            } else {


                damageSource = VanillaAPDamageSourceMap.getNewDamageSource(source, event.getAmount(), victim);


            }


            if (damageSource != null) {

                victim.hurt(damageSource, (float) damageSource.instance.getPreMitigationsAmount());


            } else {


                AeonsPast.LOGGER.log(Level.TRACE, "Source: " + source.getMsgId() + " has no equivalent!");
            }
            return;

        } else {


            APDamageSource apsource = (APDamageSource) event.getSource();


            DamageInstance instance = apsource.getInstance();


            if (victim instanceof Player) {
                Player player = (Player) victim;



                if (player.isCreative() && instance.getDamage_type()!= DamageTypeEnum.VOID) {
                    event.setCanceled(true);
                    return;
                }
            }

            if (!instance.isMitigated) {


                double mitigate = CombatEngine.getMitigatedDamage(victim, instance);

                instance.setMitigateDamage((float) mitigate);

                instance.savedMotion=victim.getDeltaMovement();

                victim.hurt(source, (float) instance.postMitigationsAmount);
                event.setCanceled(true);

                return;

            } else {


                if (instance.cancel) {
                    victim.getLevel().playSound(null, victim, SoundEvents.PLAYER_ATTACK_NODAMAGE, SoundSource.NEUTRAL, 1, 1);


                    return;
                }


                if (!instance.doesKnockback ){

                    System.out.println("shouldn't knockback!");

                    if (instance.savedMotion!=null) {
                        System.out.println("There's a saved motion!");
                        victim.setDeltaMovement(0,0,0);
                    }
                }




                if (instance.postMitigationsAmount / victim.getMaxHealth() > 0.4) {

                    victim.getLevel().playSound(null, victim, SoundEvents.PLAYER_ATTACK_STRONG, SoundSource.NEUTRAL, 1, 1);


                } else {


                    victim.getLevel().playSound(null, victim, SoundEvents.PLAYER_ATTACK_WEAK, SoundSource.NEUTRAL, 1, 1);


                }

                if (instance.isCritical) {


                    victim.getLevel().playSound(null, victim, SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.NEUTRAL, 1, 1);

                }





                if (attacker!=null && attacker instanceof LivingEntity) {

                    LivingEntity livingAttacker = (LivingEntity) attacker;


                    CombatEngine.attemptStatusHit(livingAttacker,victim,instance);









                }




                if (attacker instanceof Player) {

                    Player player = (Player) attacker;

                    ServerLevel serverLevel = (ServerLevel) level;

                    WorldTextOptions textOptions = WorldTextOptions.getWorldNumberOptionByDamage(instance.damage_type, (float) instance.postMitigationsAmount, instance.isCritical);


                    Random random = serverLevel.getRandom();
                    double xpos = victim.getX();
                    double ypos = victim.getY() + victim.getBbHeight() + 0.1;
                    double zpos = victim.getZ();

                    double xd = 0;
                    double yd = 0;
                    double zd = 0;



                    serverLevel.sendParticles((ServerPlayer) player, textOptions, true, xpos, ypos, zpos, 1, xd, yd, zd, 0d);


                }


                CombatEngine.cycleAllPostHitEffects(attacker instanceof LivingEntity ? (LivingEntity) attacker : null, victim, apsource);


            }


        }


    }


    @SubscribeEvent
    public static void serversetup(ServerStartedEvent event) {

        event.getServer().overworld().getGameRules().getRule(GameRules.RULE_NATURAL_REGENERATION).set(false, event.getServer());

        //TODO Remove for Gravestones
        event.getServer().overworld().getGameRules().getRule(GameRules.RULE_KEEPINVENTORY).set(true, event.getServer());


    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {


        if (event.getObject() instanceof net.minecraft.world.entity.LivingEntity) {

            EntityProvider provider = new EntityProvider();
            event.addCapability(new ResourceLocation(AeonsPast.MODID, "entitydata"), provider);


            if (event.getObject() instanceof Player) {


                PlayerProvider playerProvider = new PlayerProvider();

                event.addCapability(new ResourceLocation(AeonsPast.MODID, "playerdata"), playerProvider);

            }

            if (event.getObject() instanceof Mob && !(event.getObject() instanceof Player)) {

                MobDataProvider mobDataProvider = new MobDataProvider();
                event.addCapability(new ResourceLocation(AeonsPast.MODID, "mobdata"), mobDataProvider);

            }


        }


    }


    @SubscribeEvent
    public static void awakenWeapon(PlayerInteractEvent.RightClickItem event) {


        ItemStack stack = event.getItemStack();
        if (!event.getItemStack().isEmpty() && event.getSide() == LogicalSide.SERVER) {


            if (!ItemEngine.isItemInitialized(event.getItemStack())) {


                ItemEngine.initializeItem(event.getPlayer(), stack);


            }


        }


    }


    @SubscribeEvent
    public static void stopDamage(LivingDamageEvent event) {


        if (!(event.getSource() instanceof APDamageSource)) {


            event.setAmount(0);
        }


    }


    @SubscribeEvent
    public static void adjustBaseStats(EntityJoinWorldEvent event) {


        if (!event.getEntity().level.isClientSide) {
            if (event.getEntity() instanceof LivingEntity) {

                LivingEntity entity = (LivingEntity) event.getEntity();

                if (entity.getAttributeBaseValue(Attributes.ATTACK_SPEED) != 1) {

                    entity.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(1);
                }


            }

        }


    }


    @SubscribeEvent
    public static void fireProjectile(EntityJoinWorldEvent event) {


        if (event.getEntity() instanceof Projectile) {

            Projectile projectile = (Projectile) event.getEntity();

            if (projectile.getOwner() != null) {

                Entity owner = projectile.getOwner();


                if (projectile instanceof AbstractHurtingProjectile) {


                    AbstractHurtingProjectile hurtingProjectile = (AbstractHurtingProjectile) projectile;
                    if (owner instanceof net.minecraft.world.entity.LivingEntity) {

                        net.minecraft.world.entity.LivingEntity livingOwner = (net.minecraft.world.entity.LivingEntity) owner;


                    }

                }


            }


        }


    }


    @SubscribeEvent
    public static void seeAttributes(ItemAttributeModifierEvent event) {


        if (!ItemEngine.isItemInitialized(event.getItemStack())) {

            event.clearModifiers();

        } else {


        }


    }


    @SubscribeEvent
    public static void removeProjectile(ProjectileImpactEvent event) {


        event.getProjectile().remove(Entity.RemovalReason.DISCARDED);

    }


    @SubscribeEvent
    public static void playertickEvent(TickEvent.PlayerTickEvent event) {


        if (event.phase == TickEvent.Phase.START) {

            Player player = event.player;
            if (player.isDeadOrDying()) {
                return;
            }
            IPlayerData playerData = Utilities.getPlayerData(player);
            IEntityData entityData = Utilities.getEntityData(player);


            entityData.tick();


            if (entityData.getTicks() == 20) {


                player.heal((float) player.getAttributeValue(AttributeRegistration.HEALTH_REGENERATION.get()) / 5);


            }
            if (playerData.getActiveClass() != null) {

                Resource resource = playerData.getActiveClass().getCastResource();

                if (resource != null) {


                    resource.tick(player);

                }

            }
            ArrayList<Spell> spells = playerData.getActiveSpells();

            Spell weaponSpell = playerData.getActiveWeaponSpell();

            if (!Spell.isSpellDefault(weaponSpell)) {
                weaponSpell.tick(player, playerData.getSpellStack(weaponSpell));

            }


            if (spells != null) {
                for (Spell spell : spells) {


                    if (!Spell.isSpellDefault(spell)) {
                        spell.tick(player, playerData.getSpellStack(spell));

                    }
                }

            }


        }


    }


    @SubscribeEvent
    public static void equipChangeEvent(LivingEquipmentChangeEvent event) {

        if (event.getEntityLiving().level.isClientSide) {
            return;
        }
        if (event.getEntityLiving() instanceof Player) {


            if (event.getSlot() == EquipmentSlot.MAINHAND) {
                Player player = (Player) event.getEntityLiving();

                IPlayerData playerData = Utilities.getPlayerData(player);
                IEntityData entityData = Utilities.getEntityData(player);

                Item weapon = event.getTo().getItem();

                ItemStack weaponStack = event.getTo();


                Spell activeSpell = playerData.getActiveWeaponSpell();
                Spell weaponSpell = ItemEngine.getSpellFromItem(player,weaponStack);


                if (ItemEngine.isWeapon(weaponStack)) {


                    if (ItemEngine.isItemInitialized(weaponStack)) {

                        if (Spell.isSpellDefault(activeSpell)) {

                            playerData.setActiveWeaponSpell(weaponSpell);


                        } else {

                            SpellStack spellStack = Utilities.getOrCreateSpellstack(player,activeSpell);

                            if (spellStack.isRecharging() && spellStack.getCharges() < 1){

                                return;
                            } else {
                                playerData.removeSpellStack(activeSpell);
                                playerData.setActiveWeaponSpell(weaponSpell);

                            }


                        }


                    }
                } else {

                    if (!Spell.isSpellDefault(activeSpell)) {


                        SpellStack spellStack = Utilities.getOrCreateSpellstack(player,activeSpell);


                        if (spellStack.isRecharging() && spellStack.getCharges() < 1) {

                            return;
                        } else {

                            playerData.removeSpellStack(activeSpell);
                            playerData.setActiveWeaponSpell(Spell.getDefaultSpell());


                        }


                    }


                }
                Utilities.syncTotalPlayerData(player);


            }
        }


    }

    @SubscribeEvent
    public static void negateFrames(LivingDamageEvent event) {

        event.getEntityLiving().hurtDir=0;
        event.getEntityLiving().hurtTime=0;
        event.getEntityLiving().hurtDuration=0;

        event.getEntityLiving().invulnerableTime=0;

    }

    @SubscribeEvent
    public static void experienceReward(LivingDeathEvent event) {

        if (event.getSource().getEntity() != null) {

            if (event.getSource().getEntity() instanceof Player) {

                Player player = (Player) event.getSource().getEntity();


                if ((event.getEntity() instanceof Mob)) {

                    Mob mob = (Mob) event.getEntityLiving();

                    IMobData mobData = Utilities.getMobData(mob);
                    IPlayerData playerData = Utilities.getPlayerData(player);

                    if (playerData.getActiveClassInstance() !=null) {
                        playerData.getActiveClassInstance().addExperience(mobData.getExperienceReward());

                        while (playerData.getActiveClassInstance().getExperience() >= Utilities.experienceForLevel(playerData.getActiveClassInstance().getLevel() + 1)) {


                            playerData.getActiveClass().onLevelUp(player);


                        }
                    }

                }


            }


        }


    }


    @SubscribeEvent
    public static void tickAllEntities(TickEvent.WorldTickEvent event) {


        if (event.side == LogicalSide.SERVER) {

            if (event.phase == TickEvent.Phase.START) {


                ServerLevel level = (ServerLevel) event.world;
                for (Entity entity : level.getAllEntities()) {


                    if (entity instanceof Player) {

                        continue;
                    }

                    if (entity instanceof LivingEntity) {


                        LivingEntity livingEntity = (LivingEntity) entity;

                        IEntityData entityData = Utilities.getEntityData(livingEntity);


                        entityData.tick();


                        if (entityData.getTicks() == 20) {


                            livingEntity.heal((float) livingEntity.getAttributeValue(AttributeRegistration.HEALTH_REGENERATION.get()) / 5);


                        }


                    }


                }


            }


        }


    }


    @SubscribeEvent
    public static void playerDeath(LivingDeathEvent event) {

        if (event.getEntity() instanceof Player) {

            Player player = (Player) event.getEntity();


        }


    }

    @SubscribeEvent
    public static void playerDeathDataTransfer(PlayerEvent.Clone event) {


        if (event.getOriginal().level.isClientSide || !event.isWasDeath()) {
            return;
        }


        Utilities.getEntityDataOptional(event.getOriginal()).ifPresent(oldData ->
                Utilities.getEntityDataOptional(event.getPlayer()).ifPresent(newData ->
                        newData.readNBT(oldData.writeNBT())
                )
        );


        Utilities.getPlayerDataOptional(event.getOriginal()).ifPresent(oldData ->
                Utilities.getPlayerDataOptional(event.getPlayer()).ifPresent(newData ->
                        newData.readNBT(oldData.writeNBT())
                )
        );

    }


    @SubscribeEvent
    public static void noknoc(LivingDamageEvent event) {

        if (event.getSource() instanceof APDamageSource) {

            APDamageSource damageSource = (APDamageSource) event.getSource();

            DamageInstance instance = damageSource.instance;
            if (event.getEntityLiving() !=null) {


                LivingEntity victim = event.getEntityLiving();

                if (!instance.doesKnockback ){

                    System.out.println("shouldn't knockback! nk");
                    System.out.println("DT: " + instance.damage_type.toString());

                    if (instance.savedMotion!=null) {
                         victim.setDeltaMovement( 0,0,0);
                    }
                }
            }






        }


    }

}
