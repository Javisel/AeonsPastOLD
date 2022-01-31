package com.javisel.aeonspast;


import com.javisel.aeonspast.common.capabiltiies.entity.EntityProvider;
import com.javisel.aeonspast.common.capabiltiies.player.APPlayerProvider;
import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.combat.APDamageSource;
import com.javisel.aeonspast.common.combat.DamageInstance;
import com.javisel.aeonspast.common.config.ClassDataLoader;
import com.javisel.aeonspast.common.config.WeaponDataLoader;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.items.weapons.WeaponData;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.common.spell.SpellState;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;


@Mod.EventBusSubscriber

public class GameEventHandler {

    public static ClassDataLoader CLASS_STATISTICS_LOADER;
    public static WeaponDataLoader WEAPON_STATISTICS_LOADER;

    // @SubscribeEvent
    public static void newDamageCalculations(LivingHurtEvent event) {



        System.out.println("Source: "  + event.getSource().toString());
        if (!(event.getSource() instanceof APDamageSource)) {


            if (event.getSource() == DamageSource.OUT_OF_WORLD) {

                DamageInstance instance =   DamageInstance.penaltyDamage(event.getAmount() * 5);


                event.getEntityLiving().hurt(new APDamageSource(event.getSource().getMsgId(),instance),instance.getAmount()    );


            }
            event.setCanceled(true);


        }
    }


    @SubscribeEvent
    public static void attackEntityEvent(LivingAttackEvent event) {



        if (!(event.getSource() instanceof APDamageSource)) {

            event.setCanceled(true);


        }


    }















    @SubscribeEvent
    public static void serversetup(ServerStartedEvent event) {

        event.getServer().overworld().getGameRules().getRule(GameRules.RULE_NATURAL_REGENERATION).set(false, event.getServer());


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
                if (attunedWeaponData==null) {


                    playerData.setActiveWeaponSpell(spell);
                    entityData.getOrCreateSpellStack(spell);




                } else {

                    if (spell !=attunedWeaponSpell) {

                        if (attunedWeaponData.getCharges() < 1 || attunedWeaponData.getSpellState()!= SpellState.OFF) {

                            cast=false;

                        }

                    } else {
                        entityData.getOrCreateSpellStack(spell);

                    }

                }



                if (cast) {

                    spell.attemptCast(player,entityData.getSpellStackRaw(attunedWeaponSpell));

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
            ArrayList<Spell> spells = Utilities.getPlayerData(player).getActiveSpells();


            for (Spell spell : spells) {

                spell.tick(player, data.getSpellStackRaw(spell));


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
    public static void tickEntity(TickEvent.WorldTickEvent event) {


    }


    @SubscribeEvent
    public static void onDirectHitEvent(LivingDamageEvent event) {


        if (event.getEntityLiving() != null) {

            LivingEntity victim = event.getEntityLiving();


            if (event.getSource().getDirectEntity() != null) {

                Entity direct = event.getSource().getDirectEntity();


                if (direct instanceof LivingEntity) {


                    LivingEntity directAttacker = (LivingEntity) direct;


                }


            }


        }


    }


    @SubscribeEvent
    public static void eventTrigger(LivingEvent event) {


        if (event.getEntityLiving() instanceof Player) {

            Player player = (Player) event.getEntityLiving();

            if (player.isDeadOrDying()) {

                return;
            }

            IPlayerData playerData = Utilities.getPlayerData(player);
            IEntityData entityData = Utilities.getEntityData(player);

            for (Spell spell : playerData.getActiveSpells()) {


                spell.onEventTrigger(player, entityData.getSpellStackRaw(spell), event);


            }


        }


    }
    @SubscribeEvent
    public static void reload(AddReloadListenerEvent event) {


        WEAPON_STATISTICS_LOADER = new WeaponDataLoader();


        CLASS_STATISTICS_LOADER = new ClassDataLoader( );

        event.addListener(CLASS_STATISTICS_LOADER);
        event.addListener(WEAPON_STATISTICS_LOADER);



    }


    @SubscribeEvent
    public static void awakenWeapon(PlayerInteractEvent.RightClickItem event) {


        ItemStack stack = event.getItemStack();
        if (!event.getItemStack().isEmpty() && event.getSide()== LogicalSide.SERVER) {


            if (!ItemEngine.isItemInitialized(event.getItemStack())) {


                ItemEngine.initializeItem(event.getPlayer(),stack);



            }




        }






    }







}
