package com.javisel.aeonspast.client;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.networking.abilitymessage.AbilityMessage;
import com.javisel.aeonspast.common.networking.abilitymessage.WeaponAbilityMessage;
import com.javisel.aeonspast.common.registration.PacketRegistration;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.utilities.Utilities;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkDirection;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class InputHandler {


    public static final KeyMapping SPELL_1 = new KeyMapping("keybinding.aeonspast.spell_1.desc", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, AeonsPast.MODID);
    public static final KeyMapping SPELL_2 = new KeyMapping("keybinding.aeonspast.spell_2.desc", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, AeonsPast.MODID);
    public static final KeyMapping SPELL_3 = new KeyMapping("keybinding.aeonspast.spell_3.desc", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, AeonsPast.MODID);
    public static final KeyMapping SPELL_4 = new KeyMapping("keybinding.aeonspast.spell_4.desc", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_M, AeonsPast.MODID);
    public static final KeyMapping WEAPON_SPELL = new KeyMapping("keybinding.aeonspast.weapon_spell.desc", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, AeonsPast.MODID);


    public static void registerKeyBindings() {


        ClientRegistry.registerKeyBinding(SPELL_1);
        ClientRegistry.registerKeyBinding(SPELL_2);
        ClientRegistry.registerKeyBinding(SPELL_3);
        ClientRegistry.registerKeyBinding(SPELL_4);
        ClientRegistry.registerKeyBinding(WEAPON_SPELL);


    }


    @SubscribeEvent
    public static void keys(InputEvent.KeyInputEvent event) {


        if (SPELL_1.isDown()) {


            attemptCastFromClient(0);
        }
        if (SPELL_2.isDown()) {

            attemptCastFromClient(1);


        }
        if (SPELL_3.isDown()) {

            attemptCastFromClient(2);


        }
        if (SPELL_4.isDown()) {

            attemptCastFromClient(3);


        }

        if (WEAPON_SPELL.isDown()) {

            attemptWeaponCast();


        }

    }


    public static void attemptCastFromClient(int slot) {


        Minecraft minecraft = Minecraft.getInstance();

        Player player = minecraft.player;


        IPlayerData playerData = Utilities.getPlayerData(player);


        if (slot > playerData.getSpellBar().getSpellList().size()) {

            return;
        }

        Spell spell = playerData.getSpellBar().getSpellList().get(slot);


        if (!Spell.isSpellDefault(spell)) {


            if (spell.attemptCast(player, Utilities.getPlayerData(player).getSpellStack(spell))) {


                PacketRegistration.INSTANCE.sendTo(new AbilityMessage(slot), minecraft.getConnection().getConnection(), NetworkDirection.PLAY_TO_SERVER);


            }


        }


    }

    public static void attemptWeaponCast() {


        Minecraft minecraft = Minecraft.getInstance();

        Player player = minecraft.player;


        IPlayerData playerData = Utilities.getPlayerData(player);


        Spell spell = playerData.getActiveWeaponSpell();


        if (!Spell.isSpellDefault(spell)) {


            if (spell.attemptCast(player, Utilities.getPlayerData(player).getSpellStack(spell))) {


                PacketRegistration.INSTANCE.sendTo(new WeaponAbilityMessage(), minecraft.getConnection().getConnection(), NetworkDirection.PLAY_TO_SERVER);


            }


        }


    }
}
