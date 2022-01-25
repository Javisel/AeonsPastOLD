package com.javisel.aeonspast;


import com.javisel.aeonspast.common.capabiltiies.APEntityProvider;
import com.javisel.aeonspast.common.capabiltiies.APPlayerProvider;
import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.IPlayerData;
import com.javisel.aeonspast.common.combat.APDamageEngine;
import com.javisel.aeonspast.common.events.APDamageEvent;
import com.javisel.aeonspast.common.events.EventFactory;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.utilities.APUtilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;


@Mod.EventBusSubscriber

public class GameEventHandler {


    // @SubscribeEvent
    public static void newDamageCalculations(LivingDamageEvent event) {


        if (!(event instanceof APDamageEvent)) {

            event.setCanceled(true);
             Entity victim = event.getEntity();


             if (event.getSource().getDirectEntity() instanceof Player){

                 Player player = (Player) event.getSource().getDirectEntity();



             }



        }
    }



    @SubscribeEvent
    public static void serversetup(ServerStartedEvent event) {

        event.getServer().overworld().getGameRules().getRule(GameRules.RULE_NATURAL_REGENERATION).set(false, event.getServer());


    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {


        if (event.getObject() instanceof LivingEntity) {

            APEntityProvider provider = new APEntityProvider();
            event.addCapability(new ResourceLocation(AeonsPast.MODID, "entitydata"), provider);


            if (event.getObject() instanceof Player) {


                APPlayerProvider playerProvider = new APPlayerProvider();

                event.addCapability(new ResourceLocation(AeonsPast.MODID, "playerdata"), playerProvider);

            }


        }


    }


    //Player Regeneration
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent tickEvent) {


        if (tickEvent.phase == TickEvent.Phase.END) {

            Player player = tickEvent.player;

            if (player == null) {

                return;

            }
            if (player.isDeadOrDying()) {


                return;
            }

            IEntityData data = APUtilities.getEntityData(player);
            IPlayerData playerData = APUtilities.getPlayerData(player);

            data.tick();

            if (playerData.getActiveClass() !=null) {

                Resource resource = playerData.getActiveClass().getCastResource();

                if (resource !=null) {

                    if (player.level.isClientSide) {

                        System.out.println("Client Values: " + data.getResourceAmountRaw(resource) + "/" + player.getAttributeValue(resource.getResourceMaxAttribute().get()));
                    }
                    if (!player.level.isClientSide) {

                        System.out.println("Server Values: " + data.getResourceAmountRaw(resource) + "/" + player.getAttributeValue(resource.getResourceMaxAttribute().get()));
                    }
                    resource.tick(player);

                }

            }
            ArrayList<Spell> spells = APUtilities.getPlayerData(player).getActiveSpells();


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


            APUtilities.syncTotalPlayerData(event.getPlayer());
        }


    }


    @SubscribeEvent
    public static void tickEntity(TickEvent.WorldTickEvent event) {


    }


    @SubscribeEvent
    public static void onDirectHitEvent(LivingDamageEvent event) {


        if (event.getEntityLiving() !=null) {

            LivingEntity victim = event.getEntityLiving();


            if (event.getSource().getDirectEntity() !=null) {

               Entity direct =  event.getSource().getDirectEntity();


               if (direct instanceof LivingEntity) {


                   LivingEntity directAttacker = (LivingEntity) direct;




               }




            }






        }






    }





    @SubscribeEvent
    public static void eventTrigger(LivingEvent event){


        if (event.getEntityLiving() instanceof Player) {

            Player player = (Player) event.getEntityLiving();

            if (player.isDeadOrDying()) {

                return;
            }

            IPlayerData playerData = APUtilities.getPlayerData(player);
            IEntityData entityData = APUtilities.getEntityData(player);

            for (Spell spell : playerData.getActiveSpells()) {


                spell.onEventTrigger(player,entityData.getSpellStackRaw(spell),event);




            }





        }



    }

}
