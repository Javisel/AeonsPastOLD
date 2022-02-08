package com.javisel.aeonspast;


import com.javisel.aeonspast.common.capabiltiies.entity.EntityProvider;
import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.mob.MobDataProvider;
import com.javisel.aeonspast.common.capabiltiies.player.APPlayerProvider;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.combat.CombatEngine;
import com.javisel.aeonspast.common.combat.CombatInstance;
import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.combat.damagesource.APDamageSource;
import com.javisel.aeonspast.common.combat.damagesource.VanillaAPDamageSourceMap;
import com.javisel.aeonspast.common.config.ArmorDataLoader;
import com.javisel.aeonspast.common.config.ClassDataLoader;
import com.javisel.aeonspast.common.config.EntityDataLoader;
import com.javisel.aeonspast.common.config.WeaponDataLoader;
import com.javisel.aeonspast.common.entities.EntityStatisticalData;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.Map;


@Mod.EventBusSubscriber

public class GameEventHandler {

    public static ClassDataLoader CLASS_STATISTICS_LOADER;
    public static WeaponDataLoader WEAPON_STATISTICS_LOADER;
    public static ArmorDataLoader ARMOR_DATA_LOADER;

    public static EntityDataLoader ENTITY_DATA_LOADER;




    @SubscribeEvent
    public static void playerAttack(AttackEntityEvent event) {

        Player player = event.getPlayer();

        if (!(event.getTarget() instanceof  LivingEntity)) {
            return;
        }
         float f2 = player.getAttackStrengthScale(0.5F);
        float check = 1 * ( 0.2F + f2 * f2 * 0.8F);

        if (check != 1.0){

            return;
        }

        LivingEntity victim = (LivingEntity) event.getTarget();

        CombatInstance combatInstance = new CombatInstance(player,victim  );

        if (combatInstance.onPreHit()) {
            if (combatInstance.onHit()) {

                return;
            }

        }
    }






    @SubscribeEvent
    public static void attackEntityEvent(LivingAttackEvent event) {



        LivingEntity victim = event.getEntityLiving();
        DamageSource source = event.getSource();

        if (victim.getLevel().isClientSide) {
            return;
        }

        if (!(event.getSource() instanceof APDamageSource)) {





            APDamageSource damageSource = null;



            if (source instanceof IndirectEntityDamageSource) {

                IndirectEntityDamageSource indirectSource = (IndirectEntityDamageSource) source;
                LivingEntity livingEntity = (LivingEntity) source.getEntity();

                if (indirectSource.getEntity() !=null) {




                    float power = 1;

                    if (indirectSource.getDirectEntity() != null && indirectSource.getDirectEntity() instanceof  Projectile) {

                        Projectile projectile = (Projectile) indirectSource.getDirectEntity();



                       power = (float) projectile.getDeltaMovement().length();

                        if (power<= 1.6f) {

                            power = 0.10f;

                        } else {

                            power = 3/power;
                        }


                     }


                    CombatInstance combatInstance = new CombatInstance(livingEntity,victim,power);


                    damageSource = combatInstance.source;

                }





            }

          else  if ( source.getEntity() != null && source.getEntity() instanceof LivingEntity) {


                LivingEntity livingEntity = (LivingEntity) source.getEntity();
                CombatInstance combatInstance = new CombatInstance(livingEntity,victim );


                damageSource = combatInstance.source;
            }
           else {

               if (source==DamageSource.WITHER) {

                   float amount = victim.getMaxHealth() * 0.025f;

                    damageSource = VanillaAPDamageSourceMap.getNewDamageSource(source, event.getAmount());
                    damageSource.instance.preMitigationsAmount=amount;




               } else if (source ==DamageSource.DRAGON_BREATH) {


                   float amount = victim.getMaxHealth() * 0.01f;

                   damageSource = VanillaAPDamageSourceMap.getNewDamageSource(source, event.getAmount());
                   damageSource.instance.preMitigationsAmount=amount;



               } else {


                   damageSource = VanillaAPDamageSourceMap.getNewDamageSource(source, event.getAmount());
               }


            }


            event.setCanceled(true);

           if (damageSource!=null) {

               victim.hurt(damageSource, (float) damageSource.instance.getPreMitigationsAmount());


           } else {


               AeonsPast.LOGGER.log(Level.TRACE,"Source: " + source.getMsgId() + " has no equivalent!");
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


                     if (instance.isCritical) {

                         victim.getLevel().playLocalSound(victim.getX(),victim.getY(),victim.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, SoundSource.NEUTRAL,1,1,true);
                         victim.getLevel().playSound(null,victim,SoundEvents.PLAYER_ATTACK_CRIT,SoundSource.HOSTILE,1,1);



                     }

                     LivingEntity attacker = null;
                    if (source.getEntity() != null) {

                        attacker = (LivingEntity) source.getEntity();


                        Object device = instance.damageDevice;


                        if (device != null) {

                            if (instance.damageDevice instanceof Spell) {

                                //TODO apply spell-lifesteal


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

                            }
                            ArrayList<ItemStack> victimItems = ItemEngine.getAllAppicableItems(victim);

                        }


                    }
                }


            }




    }




    @SubscribeEvent
    public static void serversetup(ServerStartedEvent event) {

        event.getServer().overworld().getGameRules().getRule(GameRules.RULE_NATURAL_REGENERATION).set(false, event.getServer());

     //TODO Remove
        event.getServer().overworld().getGameRules().getRule(GameRules.RULE_KEEPINVENTORY).set(true, event.getServer());


    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {


        if (event.getObject() instanceof LivingEntity) {

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


    @SubscribeEvent
    public static void attemptWeaponCast(PlayerInteractEvent.RightClickItem event) {


        ItemStack stack = event.getItemStack();

        if (!event.getPlayer().isCrouching()) {
            return;
        }
        if (ItemEngine.isWeapon(event.getItemStack().getItem())) {

            if (ItemEngine.isItemInitialized(stack)) {


                Spell spell = ItemEngine.getSpellFromItem(stack);
                Player player = event.getPlayer();
                IPlayerData playerData = Utilities.getPlayerData(player);
                IEntityData entityData = Utilities.getEntityData(player);
                Spell attunedWeaponSpell = playerData.getActiveWeaponSpell();

                SpellStack attunedWeaponData = entityData.getSpellStackRaw(attunedWeaponSpell);


                boolean cast = true;
                if (attunedWeaponData == null) {


                    playerData.setActiveWeaponSpell(spell);
                    entityData.getOrCreateSpellStack(spell);


                } else {

                    if (spell != attunedWeaponSpell) {

                        if (attunedWeaponData.getCharges() < 1 || attunedWeaponData.getSpellState() != SpellState.OFF) {

                            cast = false;

                        }

                    } else {
                        entityData.getOrCreateSpellStack(spell);

                    }

                }


                if (cast) {
                    entityData.getOrCreateSpellStack(spell);
                    Utilities.syncTotalPlayerData(player);
                    spell.attemptCast(player, entityData.getSpellStackRaw(attunedWeaponSpell));

                }


            }


        }


    }


    //Player Regeneration
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


            if (spells != null) {
                for (Spell spell : spells) {

                    spell.tick(player, data.getSpellStackRaw(spell));


                }

            }
            if (data.getTicks() == 20) {

                player.heal((float) player.getAttributeValue(AttributeRegistration.HEALTH_REGENERATION.get()) / 5);


            }


        }


    }

    @SubscribeEvent
    public static void syncPlayerData(PlayerEvent.PlayerLoggedInEvent event) {


        if (!event.getPlayer().level.isClientSide) {


            Utilities.syncTotalPlayerData(event.getPlayer());
        }


    }


    @SubscribeEvent
    public static void reload(AddReloadListenerEvent event) {


        WEAPON_STATISTICS_LOADER = new WeaponDataLoader();
        ARMOR_DATA_LOADER = new ArmorDataLoader();


        CLASS_STATISTICS_LOADER = new ClassDataLoader();

        ENTITY_DATA_LOADER = new EntityDataLoader();
        event.addListener(CLASS_STATISTICS_LOADER);
        event.addListener(WEAPON_STATISTICS_LOADER);
        event.addListener(ENTITY_DATA_LOADER);
        event.addListener(ARMOR_DATA_LOADER);


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


        if (  !(event.getSource() instanceof APDamageSource)) {


            event.setAmount(0);
        }




    }

    @SubscribeEvent
    public static void newEntityData(EntityJoinWorldEvent event) {


        if (ENTITY_DATA_LOADER.getEntityData(event.getEntity()) != null) {

            EntityStatisticalData data = ENTITY_DATA_LOADER.getEntityData(event.getEntity());



            data.loadtoEntity((LivingEntity) event.getEntity());


        }


        if (event.getEntity() instanceof AbstractHurtingProjectile) {

            AbstractHurtingProjectile hurtingProjectile = (AbstractHurtingProjectile) event.getEntity();


        }


    }


    @SubscribeEvent
    public static void playerHealEvent(LivingHealEvent event) {


    }



    @SubscribeEvent
    public static void newExplosionInfo(ExplosionEvent.Detonate event) {



         for (Entity entity : event.getAffectedEntities()) {


             if (entity instanceof LivingEntity) {

                 LivingEntity livingEntity = (LivingEntity) entity;











             }







         }



    }


    @SubscribeEvent
    public static void adjustBaseStats(EntityJoinWorldEvent event){

        if (event.getEntity() instanceof Player) {

            Player player = (Player) event.getEntity();

            if (player.getAttributeBaseValue(Attributes.ATTACK_SPEED) != 1){

                player.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(1);
            }


        }




    }


    @SubscribeEvent
    public static void fireProjectile(EntityJoinWorldEvent event) {


        if (event.getEntity() instanceof Projectile) {

            Projectile projectile = (Projectile) event.getEntity();

            if (projectile.getOwner() !=null) {

                Entity owner = projectile.getOwner();


                if (projectile instanceof AbstractHurtingProjectile ) {


                    AbstractHurtingProjectile hurtingProjectile = (AbstractHurtingProjectile) projectile;
                    if (owner instanceof LivingEntity) {

                        LivingEntity livingOwner = (LivingEntity) owner;








                    }

                }


            }




        }



    }





    @SubscribeEvent
    public static void seeAttributes(ItemAttributeModifierEvent event) {


        if (!ItemEngine.isItemInitialized(event.getItemStack())) {

            event.clearModifiers();

        } else{



            for (Map.Entry<Attribute, AttributeModifier> set: event.getModifiers().entries()) {








            }






        }



    }


@SubscribeEvent
    public static void removeProjectile(ProjectileImpactEvent event) {


        event.getProjectile().remove(Entity.RemovalReason.DISCARDED);

}



}
