package com.javisel.aeonspast;


import com.javisel.aeonspast.common.capabiltiies.entity.EntityProvider;
import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.mob.IMobData;
import com.javisel.aeonspast.common.capabiltiies.mob.MobDataProvider;
import com.javisel.aeonspast.common.capabiltiies.player.APPlayerProvider;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.capabiltiies.player.PlayerData;
import com.javisel.aeonspast.common.combat.CombatEngine;
import com.javisel.aeonspast.common.combat.CombatInstance;
import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import com.javisel.aeonspast.common.combat.damagesource.VanillaAPDamageSourceMap;
import com.javisel.aeonspast.common.effects.ComplexEffect;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.common.spell.SpellState;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
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
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.Collection;


@Mod.EventBusSubscriber

public class GameEventHandler {


    @SubscribeEvent
    public static void playerAttack(AttackEntityEvent event) {

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

        CombatInstance combatInstance = new CombatInstance(player, victim);

        if (combatInstance.onPreHit()) {
            if (combatInstance.onHit()) {

                return;
            }

        }
    }


    @SubscribeEvent
    public static void attackEntityEvent(LivingAttackEvent event) {


        net.minecraft.world.entity.LivingEntity victim = event.getEntityLiving();
        DamageSource source = event.getSource();

        if (victim.getLevel().isClientSide) {
            return;
        }

        if (!(event.getSource() instanceof APDamageSource)) {


            APDamageSource damageSource = null;


            if (source instanceof IndirectEntityDamageSource) {

                IndirectEntityDamageSource indirectSource = (IndirectEntityDamageSource) source;
                net.minecraft.world.entity.LivingEntity livingEntity = (net.minecraft.world.entity.LivingEntity) source.getEntity();

                if (indirectSource.getEntity() != null) {

                    float power = 1;

                    if (indirectSource.getDirectEntity() != null && indirectSource.getDirectEntity() instanceof Projectile) {

                        Projectile projectile = (Projectile) indirectSource.getDirectEntity();

                        power = (float) projectile.getDeltaMovement().length();

                        if (power <= 1.6f) {

                            power = 0.10f;

                        } else {

                            power = 3 / power;
                        }

                    }

                    CombatInstance combatInstance = new CombatInstance(livingEntity, victim, power);


                    damageSource = combatInstance.source;

                }


            } else if (source.getEntity() != null && source.getEntity() instanceof net.minecraft.world.entity.LivingEntity) {



                if ( !(event.getSource().getEntity() instanceof Player)) {

                    net.minecraft.world.entity.LivingEntity livingEntity = (net.minecraft.world.entity.LivingEntity) source.getEntity();
                    CombatInstance combatInstance = new CombatInstance(livingEntity, victim);


                    damageSource = combatInstance.source;
                }
            } else {



                    damageSource = VanillaAPDamageSourceMap.getNewDamageSource(source, event.getAmount(),victim);



            }


            event.setCanceled(true);

            if (damageSource != null) {

                victim.hurt(damageSource, (float) damageSource.instance.getPreMitigationsAmount());


            } else {


                AeonsPast.LOGGER.log(Level.TRACE, "Source: " + source.getMsgId() + " has no equivalent!");
            }
            return;

        } else {


            APDamageSource apsource = (APDamageSource) event.getSource();


            DamageInstance instance = apsource.getInstance();

            if (!instance.isMitigated) {


                double mitigate = CombatEngine.getMitigatedDamage(victim, instance);

                instance.setMitigateDamage((float) mitigate);


                victim.hurt(source, (float) instance.postMitigationsAmount);

                return;
            } else {

                System.out.println(apsource.getInstance().preMitigationsAmount + " " + apsource.instance.getDamage_type().toString()  +" Pre-Mitigations Damage");
                System.out.println(apsource.getInstance().postMitigationsAmount + " " + apsource.instance.getDamage_type().toString()  +" Post-Mitigations Damage");
                System.out.println("Critical: " + apsource.getInstance().isCritical);


                if (instance.postMitigationsAmount / victim.getMaxHealth() > 0.25) {

                    victim.getLevel().playLocalSound(victim.getX(), victim.getY(), victim.getZ(), SoundEvents.PLAYER_ATTACK_STRONG, SoundSource.NEUTRAL, 1, 1, true);
                    victim.getLevel().playSound(null, victim, SoundEvents.PLAYER_ATTACK_STRONG, SoundSource.HOSTILE, 1, 1);


                }

                if (instance.isCritical) {

                    victim.getLevel().playLocalSound(victim.getX(), victim.getY(), victim.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.NEUTRAL, 1, 1, true);
                    victim.getLevel().playSound(null, victim, SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.HOSTILE, 1, 1);


                }

                net.minecraft.world.entity.LivingEntity attacker = null;

                if (source.getEntity() != null) {

                    attacker = (net.minecraft.world.entity.LivingEntity) source.getEntity();


                    Object device = instance.damageDevice;


                    if (device != null) {

                        if (instance.damageDevice instanceof Spell) {


                            CombatEngine.applySpellLifestal(attacker, victim, instance);


                        }
                        if (instance.damageDevice instanceof ItemStack) {


                            CombatEngine.applyWeaponLifesteal(attacker, victim, instance);


                        }

                        if (device instanceof ItemStack) {

                            ItemStack weapon = (ItemStack) device;


                            for (ItemProperty property : ItemEngine.getItemProperties(weapon)) {


                                property.postHitEntityInHand(attacker, victim, instance, weapon);


                            }


                        }

                        if (attacker != null) {
                            ArrayList<ItemStack> attackerItems = ItemEngine.getAllAppicableItems(attacker);
                            for (ItemStack attackerStack : attackerItems) {


                                if (ItemEngine.isItemInitialized(attackerStack)) {


                                    for (ItemProperty property : ItemEngine.getItemProperties(attackerStack)) {


                                        property.postHitEntity(attacker, victim, instance);


                                    }

                                }


                            }

                            Collection<MobEffectInstance> effects = attacker.getActiveEffects();


                            for (MobEffectInstance mobEffectInstance : effects) {

                                if (mobEffectInstance.getEffect() instanceof ComplexEffect) {

                                    ComplexEffect effect = (ComplexEffect) mobEffectInstance.getEffect();

                                    effect.onpostHitEffect(attacker,victim,apsource);

                                }


                            }


                        }
                        ArrayList<ItemStack> victimItems = ItemEngine.getAllAppicableItems(victim);



                        for (ItemStack victimStack : victimItems) {


                            if (ItemEngine.isItemInitialized(victimStack)) {


                                for (ItemProperty property : ItemEngine.getItemProperties(victimStack)) {


                                    property.onOwnerPostHit(attacker, victim, instance);


                                }

                            }


                        }
                        Collection<MobEffectInstance> effects = victim.getActiveEffects();


                        for (MobEffectInstance mobEffectInstance : effects) {

                            if (mobEffectInstance.getEffect() instanceof ComplexEffect) {

                                ComplexEffect effect = (ComplexEffect) mobEffectInstance.getEffect();

                                effect.onOwnerpostHitEffect(attacker,victim,apsource);

                            }


                        }




                    }


                    if (attacker instanceof Player) {


                        Player player = (Player) attacker;









                    }

                }
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


                APPlayerProvider playerProvider = new APPlayerProvider();

                event.addCapability(new ResourceLocation(AeonsPast.MODID, "playerdata"), playerProvider);

            }

            if (event.getObject() instanceof Mob && !(event.getObject() instanceof Player)) {

                MobDataProvider mobDataProvider = new MobDataProvider();
                event.addCapability(new ResourceLocation(AeonsPast.MODID, "mobdata"), mobDataProvider);

            }


        }


    }






    //LivingEntityMixin Regeneration
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent tickEvent) {


        if (tickEvent.phase == TickEvent.Phase.START) {

            Player player = tickEvent.player;

            if (player == null) {

                return;

            }
            if (player.isDeadOrDying()) {


                return;
            }

            IEntityData data = Utilities.getEntityData(player);
            IPlayerData playerData = Utilities.getPlayerData(player);

            data.tick();

            if (playerData.getActiveClass() != null) {

                Resource resource = playerData.getActiveClass().getCastResource();

                if (resource != null) {


                    resource.tick(player);

                }

            }
            ArrayList<Spell> spells = Utilities.getEntityData(player).getActiveSpells();

            Spell weaponSpell = playerData.getActiveWeaponSpell();

            if (!Spell.isSpellDefault(weaponSpell)) {
                weaponSpell.tick(player,data.getSpellStackRaw(weaponSpell));

            }



            if (spells != null) {
                for (Spell spell : spells) {


                    if (!Spell.isSpellDefault(spell)) {
                        spell.tick(player, data.getSpellStackRaw(spell));

                    }
                }

            }
            if (data.getTicks() == 20) {


                if (player.getFoodData().getFoodLevel() > 6) {
                    player.heal((float) player.getAttributeValue(AttributeRegistration.HEALTH_REGENERATION.get()) / 5);

                }

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
    public static void playerHealEvent(LivingHealEvent event) {


    }


    @SubscribeEvent
    public static void newExplosionInfo(ExplosionEvent.Detonate event) {


        for (Entity entity : event.getAffectedEntities()) {


            if (entity instanceof net.minecraft.world.entity.LivingEntity) {

                net.minecraft.world.entity.LivingEntity livingEntity = (net.minecraft.world.entity.LivingEntity) entity;


            }


        }


    }


    @SubscribeEvent
    public static void adjustBaseStats(EntityJoinWorldEvent event) {


        if (!event.getEntity().level.isClientSide) {
            if (event.getEntity() instanceof Player) {

                Player player = (Player) event.getEntity();

                if (player.getAttributeBaseValue(Attributes.ATTACK_SPEED) != 1) {

                    player.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(1);
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
    public static void equipChangeEvent(LivingEquipmentChangeEvent event) {

        if (event.getEntityLiving().level.isClientSide) {
            return;
        }
        if (event.getEntityLiving() instanceof Player) {


            if (event.getSlot()== EquipmentSlot.MAINHAND) {
                Player player = (Player) event.getEntityLiving();

                IPlayerData playerData = Utilities.getPlayerData(player);
                IEntityData entityData = Utilities.getEntityData(player);

                Item weapon = event.getTo().getItem();

                ItemStack weaponStack = event.getTo();


                Spell activeSpell = playerData.getActiveWeaponSpell();
                Spell weaponSpell = ItemEngine.getSpellFromItem(weaponStack);


                if (ItemEngine.isWeapon(weaponStack)) {


                    if (ItemEngine.isItemInitialized(weaponStack)) {

                        if (Spell.isSpellDefault(activeSpell)) {

                            playerData.setActiveWeaponSpell(weaponSpell);


                        } else {

                            SpellStack spellStack = entityData.getOrCreateSpellStack(activeSpell);

                            if (spellStack.isCoolingDown()) {

                                return;
                            } else {
                                entityData.removeSpellStack(activeSpell);
                                playerData.setActiveWeaponSpell(weaponSpell);

                            }


                        }


                    }
                } else {

                    if (!Spell.isSpellDefault(activeSpell)) {


                        SpellStack spellStack = entityData.getOrCreateSpellStack(activeSpell);


                            if (spellStack.isCoolingDown()) {

                                return;
                            } else {

                                entityData.removeSpellStack(activeSpell);
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

        event.getEntityLiving().hurtTime=0;
}

@SubscribeEvent
    public static void experienceReward(LivingDeathEvent event) {

        if (event.getSource().getEntity() !=null) {

            if (event.getSource().getEntity() instanceof Player) {

                Player player = (Player) event.getSource().getEntity();


                if ((event.getEntity() instanceof Mob)) {

                    Mob mob = (Mob) event.getEntityLiving();

                    IMobData mobData = Utilities.getMobData(mob);
                    IPlayerData playerData = Utilities.getPlayerData(player);

                    playerData.getActiveClassInstance().addExperience(mobData.getExperienceReward());

                    while (playerData.getActiveClassInstance().getExperience() >= Utilities.experienceForLevel(playerData.getActiveClassInstance().getLevel() +1)) {


                        playerData.getActiveClass().onLevelUp(player);





                    }





                }



            }




        }


}


}
