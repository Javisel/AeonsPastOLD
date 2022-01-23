package com.javisel.aeonspast;

import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.IPlayerData;
import com.javisel.aeonspast.common.playerclasses.PlayerGameClass;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBusEventHandler {


    public static final ResourceLocation CLASS_REGISTRY_NAME = new ResourceLocation(AeonsPast.MODID, "classes");
    public static final ResourceLocation SPELL_REGISTRY_NAME = new ResourceLocation(AeonsPast.MODID, "spells");
    public static final ResourceLocation RESOURCE_REGISTRY_NAME = new ResourceLocation(AeonsPast.MODID,"resources");

    @SubscribeEvent
    public static void registerNewCapabilties(RegisterCapabilitiesEvent event) {
        event.register(IEntityData.class);
        event.register(IPlayerData.class);

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
    public static void addnewRegistries(RegistryEvent.NewRegistry event) {


         RegistryBuilder<PlayerGameClass> registryBuilder = new RegistryBuilder<>();

        registryBuilder.setName(CLASS_REGISTRY_NAME).setMaxID(Integer.MAX_VALUE).setType(PlayerGameClass.class).allowModification().setDefaultKey(new ResourceLocation(AeonsPast.MODID, "none")).create();

        RegistryBuilder<Spell> spellRegistryBuilder = new RegistryBuilder<>();

        spellRegistryBuilder.setName(SPELL_REGISTRY_NAME).setMaxID(Integer.MAX_VALUE).setType(Spell.class).allowModification().setDefaultKey(new ResourceLocation(AeonsPast.MODID, "none")).create();


        RegistryBuilder<Resource> resourceRegistryBuilder = new RegistryBuilder<>();

        resourceRegistryBuilder.setName(RESOURCE_REGISTRY_NAME).setMaxID(Integer.MAX_VALUE).setType(Resource.class).allowModification().setDefaultKey(new ResourceLocation(AeonsPast.MODID,"none")).create();




    }


}
