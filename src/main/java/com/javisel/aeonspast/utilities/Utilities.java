package com.javisel.aeonspast.utilities;

import com.javisel.aeonspast.common.capabiltiies.entity.EntityCapability;
import com.javisel.aeonspast.common.capabiltiies.itemstack.IItemStackData;
import com.javisel.aeonspast.common.capabiltiies.itemstack.ItemStackDataCapability;
import com.javisel.aeonspast.common.capabiltiies.player.APPlayerCapability;
import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.enums.TrinketEnums;
import com.javisel.aeonspast.common.networking.PacketHandler;
import com.javisel.aeonspast.common.networking.PlayerCapabiltiiesMessage;
import com.javisel.aeonspast.common.networking.ResourceMessage;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.resource.Resource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkDirection;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;
import java.util.UUID;

public class Utilities {


    public static IEntityData getEntityData(LivingEntity entity) {

        return entity.getCapability(EntityCapability.ENTITY_DATA_CAP, null).orElseThrow(NullPointerException::new);


    }

    public static IPlayerData getPlayerData(Player player) {


        return player.getCapability(APPlayerCapability.PLAYER_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);

    }


    public static float experienceForLevel(int level) {

        if (level == 0) return 0;

        return (level * 100) + experienceForLevel(level - 1);


    }

    public static ItemStack getTrinket(LivingEntity entity, TrinketEnums trinketType, int slot) {

        ItemStack result = ItemStack.EMPTY;

        List<SlotResult> slotResult = CuriosApi.getCuriosHelper().findCurios(entity, trinketType.getIdentifier());


        if (slotResult.isEmpty()) {

            return result;
        }

        if (slotResult.size() < slot) {

            System.err.println("Searched for slot " + slot + " when only " + slotResult.size() + " are available!");
            return result;
        }


        return slotResult.get(slot).stack();
    }


    public static void syncTotalPlayerData(Player player) {


        IEntityData entityData = Utilities.getEntityData(player);
        IPlayerData playerData = Utilities.getPlayerData(player);

        PlayerCapabiltiiesMessage playerCapabiltiiesMessage = new PlayerCapabiltiiesMessage(entityData.writeNBT(), playerData.writeNBT());

        ServerPlayer serverPlayer = (ServerPlayer) player;


        PacketHandler.INSTANCE.sendTo(playerCapabiltiiesMessage, serverPlayer.connection.connection, NetworkDirection.PLAY_TO_CLIENT);


    }


    public static boolean removeAttributeModifierIfPresent(LivingEntity entity, Attribute attribute, String uuid) {


        if (entity.getAttribute(attribute).getModifier(UUID.fromString(uuid)) != null) {

            entity.getAttribute(attribute).removeModifier(UUID.fromString(uuid));

            return true;
        }


        return false;

    }


    public static double getCooldownCoefficient(LivingEntity entity) {


        if (entity.getAttributeValue(AttributeRegistration.COOLDOWN_REDUCTION.get()) <= 0) {

            return 1;
        }

        return 1 - (100 / (100 + entity.getAttributeValue(AttributeRegistration.COOLDOWN_REDUCTION.get())));


    }


    public static void syncResourceData(Player player, Resource resource) {

        IEntityData entityData = Utilities.getEntityData(player);

        ResourceMessage resourceMessage = new ResourceMessage(entityData.getOrCreateResource(resource), resource.getRegistryName());


        ServerPlayer serverPlayer = (ServerPlayer) player;
        PacketHandler.INSTANCE.sendTo(resourceMessage, serverPlayer.connection.connection, NetworkDirection.PLAY_TO_CLIENT);

    }




public static IItemStackData getItemStackData(ItemStack stack) {



        return  stack.getCapability(ItemStackDataCapability.ITEM_STACK_DATA_CAP,null).orElseThrow(NullPointerException::new);
}




}
