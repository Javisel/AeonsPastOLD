package com.javisel.aeonspast;

import com.javisel.aeonspast.common.capabiltiies.APEntityCapability;
import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import com.javisel.aeonspast.common.playerclasses.PlayerGameClass;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.*;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEventHandler {





    @SubscribeEvent
    public static void registerNewCapabilties(RegisterCapabilitiesEvent event) {

        event.register(IEntityData.class);

    }




    @SubscribeEvent
    public static void addNewAttributesToExistingMobs(EntityAttributeModificationEvent event) {


        List<EntityType<? extends LivingEntity>> entities = event.getTypes();

        for (EntityType<? extends LivingEntity> entity : entities) {

            for (RegistryObject<Attribute> entry : AttributeRegistration.ATTRIBUTES.getEntries()) {
                event.add(entity, entry.get());


            }
        }

    }


    @SubscribeEvent
    public static void addnewRegistries(RegistryEvent.NewRegistry event)

    {


        System.out.println("Adding new registries!");
        RegistryBuilder<PlayerGameClass> registryBuilder = new RegistryBuilder<>();

        registryBuilder.setName(new ResourceLocation(AeonsPast.MODID,"classes")).setMaxID(Integer.MAX_VALUE).setType(PlayerGameClass.class).allowModification().setDefaultKey(new ResourceLocation(AeonsPast.MODID,"none")).create();




    }




}
