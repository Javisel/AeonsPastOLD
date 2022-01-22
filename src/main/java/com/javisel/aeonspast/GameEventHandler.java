package com.javisel.aeonspast;


import com.javisel.aeonspast.common.capabiltiies.APEntityProvider;
import com.javisel.aeonspast.common.capabiltiies.APPlayerProvider;
import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import com.javisel.aeonspast.common.combat.PRCombatRules;
import com.javisel.aeonspast.common.events.EventFactory;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.utilities.APUtilities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber

public class GameEventHandler {

    @SubscribeEvent
    public static void newHungerDamage(LivingHurtEvent event) {

        if (event.getSource() == DamageSource.STARVE) {


            event.setAmount(event.getAmount() + event.getEntityLiving().getHealth() * 0.15f);


        }

    }

    // @SubscribeEvent
    public static void newDamageCalculations(LivingHurtEvent event) {


        if (event.getEntityLiving() != null) {
            event.getSource().bypassArmor();
            float olddamage = event.getAmount();
            float newamount = PRCombatRules.getDamagePostMitigations(event.getEntityLiving().getAttribute(Attributes.ARMOR).getValue(), event.getEntityLiving().getAttribute(Attributes.ARMOR_TOUGHNESS).getValue(), olddamage);

            float mitigated = olddamage - newamount;
            if (mitigated < 1) mitigated = 0;
            if (event.getEntityLiving() instanceof ServerPlayer) {

                ServerPlayer player = (ServerPlayer) event.getEntityLiving();


                player.awardStat(Stats.DAMAGE_RESISTED, (int) (mitigated));
            }

        }


    }


    @SubscribeEvent
    public static void postDamageEvents(LivingDamageEvent event) {


        if (!event.getSource().isMagic() && !event.getSource().isProjectile() && event.getSource().getDirectEntity() != null) {

            LivingEntity entity = (LivingEntity) event.getSource().getDirectEntity();

            double lifesteal = entity.getAttribute(AttributeRegistration.LIFESTEAL.get()).getValue();
            lifesteal /= 100;

            double healing = event.getAmount() * lifesteal;

            if (healing > event.getEntityLiving().getHealth()) {
                healing = event.getEntityLiving().getHealth();
            }
            float posteventhealing = EventFactory.onLifesteal(entity, event.getEntityLiving(), (float) healing);


            entity.heal(posteventhealing);


        }


    }

    @SubscribeEvent
    public static void serversetup(ServerStartedEvent event) {

        System.out.println("SERVER STARTED");
        event.getServer().overworld().getGameRules().getRule(GameRules.RULE_NATURAL_REGENERATION).set(false, event.getServer());


    }

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {


        if (event.getObject() instanceof LivingEntity) {

            APEntityProvider provider = new APEntityProvider();
            event.addCapability(new ResourceLocation(AeonsPast.MODID, "entitydata"), provider);



            if (event.getObject() instanceof Player) {


                APPlayerProvider playerProvider = new APPlayerProvider();

                event.addCapability( new ResourceLocation(AeonsPast.MODID,"playerdata"),playerProvider);

            }


        }


    }


    //Player Regeneration
    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent tickEvent) {


        if (tickEvent.side.isServer() && tickEvent.phase == TickEvent.Phase.END) {

            Player player = tickEvent.player;

            if (player == null) {

                 return;

            }
            if (player.isDeadOrDying()) {


                return;
            }

            IEntityData data = APUtilities.getEntityData(player);


            data.tick();
            ;
            if (data.getTicks() == 20) {

                player.heal((float) player.getAttributeValue(AttributeRegistration.HEALTH_REGENERATION.get()) / 5);
                player.getAttribute(AttributeRegistration.HEALTH_SHIELD.get()).addPermanentModifier(new AttributeModifier("stuff", 1, AttributeModifier.Operation.ADDITION));

                APUtilities.setEntityMana(player, (float) (data.getMana() + player.getAttributeValue(AttributeRegistration.RESOURCE_REGENERATION_RATE.get())));



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





}
