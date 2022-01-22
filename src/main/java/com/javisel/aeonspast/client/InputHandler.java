package com.javisel.aeonspast.client;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.capabiltiies.IPlayerData;
import com.javisel.aeonspast.common.enums.TrinketEnums;
import com.javisel.aeonspast.common.items.TrinketItem;
import com.javisel.aeonspast.common.networking.AbilityMessage;
import com.javisel.aeonspast.common.networking.ManaMessage;
import com.javisel.aeonspast.common.networking.PacketHandler;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.utilities.APUtilities;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.extensions.IForgeKeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkDirection;
import org.lwjgl.glfw.GLFW;
import top.theillusivec4.curios.api.CuriosApi;

import javax.swing.text.JTextComponent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class InputHandler {


    public static final KeyMapping SPELL_1 = new KeyMapping("keybinding.aeonspast.spell_1.desc", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, AeonsPast.MODID);
    public static final KeyMapping SPELL_2 = new KeyMapping("keybinding.aeonspast.spell_2.desc", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, AeonsPast.MODID);
    public static final KeyMapping SPELL_3 = new KeyMapping("keybinding.aeonspast.spell_3.desc", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, AeonsPast.MODID);
    public static final KeyMapping SPELL_4 = new KeyMapping("keybinding.aeonspast.spell_4.desc", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_M, AeonsPast.MODID);



    public static void registerKeyBindings() {


        ClientRegistry.registerKeyBinding(SPELL_1);
        ClientRegistry.registerKeyBinding(SPELL_2);
        ClientRegistry.registerKeyBinding(SPELL_3);
        ClientRegistry.registerKeyBinding(SPELL_4);



    }





    @SubscribeEvent
    public static void keys(InputEvent.KeyInputEvent event) {



        if (SPELL_1.isDown()) {



            attemptCastFromClient( 0);
        }
        if (SPELL_1.isDown()) {

            attemptCastFromClient( 1);


        } if (SPELL_1.isDown()) {

            attemptCastFromClient( 2);


        } if (SPELL_1.isDown()) {

            attemptCastFromClient( 3);


        }

    }


    public static void attemptCastFromClient( int slot) {


        Minecraft minecraft = Minecraft.getInstance();

        Player player = minecraft.player;


        IPlayerData playerData = APUtilities.getPlayerData(player);


        if (slot > playerData.getSpellBar().size()) {

            return;
        }
        Spell spell =  playerData.getSpellBar().get(slot);
        if (!spell.isEmpty( spell)){


                PacketHandler.INSTANCE.sendTo(new AbilityMessage(slot), minecraft.getConnection().getConnection(), NetworkDirection.PLAY_TO_SERVER);





        }




    }



}
