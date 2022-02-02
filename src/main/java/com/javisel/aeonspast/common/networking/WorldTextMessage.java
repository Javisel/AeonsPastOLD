package com.javisel.aeonspast.common.networking;

import com.javisel.aeonspast.common.combat.damagetypes.APDamageSubType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WorldTextMessage {

   public   static int amount;
    public  static int id;

    public static boolean critical = false;

    public WorldTextMessage(int amount, int type, boolean critical) {

        this.amount=amount;
        this.id=type;
        this.critical=critical;
    
    }


    public static void encode(WorldTextMessage pkt, FriendlyByteBuf buf) {
       buf.writeInt(pkt.amount);
       buf.writeInt(pkt.id);
       buf.writeBoolean(pkt.critical);


     }

    public static WorldTextMessage decode(FriendlyByteBuf buf) {
        return new WorldTextMessage(buf.readInt(),buf.readInt(),buf.readBoolean());
    }


    public static class Handler {

        public static void handle(final WorldTextMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ctx.get().enqueueWork(() -> {








            });

            ctx.get().setPacketHandled(true);

        }
    }


     enum Type{


        PENALTY(0),
         TRUE(1),
         PHYSICAL(2),
         MAGICAL(3),
         HEALING(4),
         EXPERIENCE(5);


         int id;

        Type(int id) {
            this.id=id;
        }

        public static int getByDamageType(APDamageSubType damageSubType) {




            switch (damageSubType){
                case PENALTY: return 0;

                case TRUE: return 1;
                case PHYSICAL: return 2;
                case MAGIC: return 3;



            }



            return -1;
        }






    }
}









