package com.javisel.aeonspast.utilities;

import com.javisel.aeonspast.common.capabiltiies.APEntityCapability;
import com.javisel.aeonspast.common.capabiltiies.APPlayerCapability;
import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.IPlayerData;
import com.javisel.aeonspast.common.enums.TrinketEnums;
import com.javisel.aeonspast.common.networking.ManaMessage;
import com.javisel.aeonspast.common.networking.PacketHandler;
import com.javisel.aeonspast.common.networking.PlayerCapabiltiiesMessage;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
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

public class APUtilities {







    public static IEntityData getEntityData(LivingEntity entity) {

        return entity.getCapability(APEntityCapability.ENTITY_DATA_CAP, null).orElseThrow(NullPointerException::new);


    }

    public static IPlayerData getPlayerData(Player player) {



        return  player.getCapability(APPlayerCapability.PLAYER_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);

    }





    public static float experienceForLevel(int level) {

        if (level==0) return 0;

        return   (level * 100)  + experienceForLevel(level-1);


    }





    public static void addManaToUnit(LivingEntity entity, float mana) {


        setEntityMana(entity,getEntityData(entity).getMana() + mana);



    }





    public static void setEntityMana(LivingEntity entity, float mana) {


        if (!entity.level.isClientSide) {

            IEntityData data = getEntityData(entity);


            if (data.getMana() + mana <=0) {

                mana=0;
            }

           else  if (data.getMana() == entity.getAttributeValue(AttributeRegistration.MAXIMUM_RESOURCE.get())) {
                return;
            }

           else  if (data.getMana() + mana > entity.getAttributeValue(AttributeRegistration.MAXIMUM_RESOURCE.get())) {

                mana = (float) (entity.getAttributeValue(AttributeRegistration.MAXIMUM_RESOURCE.get()));


            }


            data.setMana(mana);

            if (entity instanceof ServerPlayer) {

                ServerPlayer serverPlayer = (ServerPlayer) entity;

                PacketHandler.INSTANCE.sendTo(new ManaMessage(data.getMana()), serverPlayer.connection.connection, NetworkDirection.PLAY_TO_CLIENT);


            }
        }






    }


    public  static ItemStack getTrinket(LivingEntity entity, TrinketEnums trinketType, int slot) {

        ItemStack result = ItemStack.EMPTY;

        List<SlotResult> slotResult = CuriosApi.getCuriosHelper().findCurios(entity,trinketType.getIdentifier());


        if (slotResult.isEmpty()) {

            return result;
        }

        if (slotResult.size() < slot) {

            System.err.println("Searched for slot "+  slot+ " when only " + slotResult.size() + " are available!");
            return result;
        }





        return slotResult.get(slot).stack();
    }


    public static void syncTotalPlayerData(Player player) {



        IEntityData entityData = APUtilities.getEntityData( player);
        IPlayerData playerData = APUtilities.getPlayerData(player);

        PlayerCapabiltiiesMessage playerCapabiltiiesMessage = new PlayerCapabiltiiesMessage(entityData.writeNBT(),playerData.writeNBT());

        ServerPlayer serverPlayer = (ServerPlayer) player;



        PacketHandler.INSTANCE.sendTo(playerCapabiltiiesMessage, serverPlayer.connection.connection, NetworkDirection.PLAY_TO_CLIENT);


    }



    public static boolean removeAttributeModifierIfPresent(LivingEntity entity, Attribute attribute, String uuid) {




        if (entity.getAttribute(attribute).getModifier(UUID.fromString(uuid)) !=null) {

            entity.getAttribute(attribute).removeModifier(UUID.fromString(uuid));

            return true;
        }





return false;

    }





}
