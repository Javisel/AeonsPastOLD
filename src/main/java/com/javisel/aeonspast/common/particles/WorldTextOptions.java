package com.javisel.aeonspast.common.particles;

import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.registration.ParticleTypeRegistration;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;

public class WorldTextOptions implements ParticleOptions {


        private   Component component;
   public WorldTextOptions() {

   }

   public WorldTextOptions(String text, boolean critical) {



      component = new TextComponent(text);

      if (critical) {

          component = component.copy().withStyle(ChatFormatting.BOLD);
      }


   }

    public WorldTextOptions(String message) {


        component = ComponentUtils.fromMessage(new Message() {
           @Override
           public String getString() {
               return message;
           }
       });


    }



    public static Codec<WorldTextOptions> codec() {

         return RecordCodecBuilder.create((instance) -> instance.group(


                Codec.STRING.fieldOf("component").forGetter((obj) -> obj.component.toString()))
                .apply(instance, WorldTextOptions::new));
    }




   public static WorldTextOptions getWorldNumberOptionByDamage(DamageTypeEnum type, float amount, boolean critical) {


       WorldTextOptions worldNumberOptions = new WorldTextOptions();


       worldNumberOptions.component = new TextComponent(Integer.toString((int) (amount)));

       worldNumberOptions.component = worldNumberOptions.component.copy().withStyle(Style.EMPTY.withColor(type.getColor()));

       if (critical) {

           worldNumberOptions.component = worldNumberOptions.component.copy().append("!");
           worldNumberOptions. component = worldNumberOptions.component.copy().withStyle(ChatFormatting.BOLD);
       }


       return  worldNumberOptions;

   }

    public WorldTextOptions(Component component) {

       this.component=component;

    }

    @Override
    public ParticleType<?> getType() {
        return ParticleTypeRegistration.WORLD_TEXT_TYPE.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf byteBuf) {

       System.out.println("Writing to network!");
         byteBuf.writeComponent(component);
     }

    @Override
    public String writeToString() {
        return  component.toString();
    }

    public static final ParticleOptions.Deserializer<WorldTextOptions> DESERIALIZER = new ParticleOptions.Deserializer<>() {
        public WorldTextOptions fromCommand(ParticleType<WorldTextOptions> particleType, StringReader reader) throws CommandSyntaxException {
                reader.expect(' ');
                String s = reader.readString();
            reader.expect(' ');
                boolean b = reader.readBoolean();


System.out.println("From the Commands!");
             return new WorldTextOptions(s,b);
        }

        public WorldTextOptions fromNetwork(ParticleType<WorldTextOptions> particleType, FriendlyByteBuf byteBuf) {

            System.out.println("WORLD TEXT FROM THE NETWORKSSSSS");
            return new WorldTextOptions(byteBuf.readComponent());
        }
    };


    public Component getComponent() {
        return component;
    }
}
