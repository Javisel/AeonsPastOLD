package com.javisel.aeonspast.client.overlays;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.client.RenderUtilities;
import com.javisel.aeonspast.client.main.ClientProxy;
import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.registration.ResourceRegistration;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.Utilities;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.server.command.TextComponentHelper;

import java.awt.*;


@OnlyIn(Dist.CLIENT)
public class AbilityBar implements IIngameOverlay {


    public static final ResourceLocation ABILITY_BAR_TEXTURES = new ResourceLocation(AeonsPast.MODID, "textures/gui/ability_bar.png");

    private final ItemStack dummy = new ItemStack(Items.DIAMOND);

    @Override
    public void render(ForgeIngameGui gui, PoseStack mStack, float partialTicks, int width, int height) {

        Minecraft minecraft = Minecraft.getInstance();

        Player player = minecraft.player;



        Gui.drawString(mStack,gui.getFont(),"AEON'S PAST IN-DEV", 0,0,0);

        if (player ==null || player.isDeadOrDying()) {
            return;
        }
        IEntityData entityData = Utilities.getEntityData(minecraft.player);

        IPlayerData playerData = Utilities.getPlayerData(minecraft.player);

        if (playerData.getActiveClass() == null) {
            return;
        }


         //Renders the Item Holders

        int zpos = -40;
        RenderUtilities.renderTextureFromSprite(mStack, ABILITY_BAR_TEXTURES, 20, 27, 1, height - 39, zpos, 0, 0, 20, 27);
        RenderUtilities.renderTextureFromSprite(mStack, ABILITY_BAR_TEXTURES, 20, 20, 23, height - 32, zpos, 20, 7, 20, 20);
        RenderUtilities.renderTextureFromSprite(mStack, ABILITY_BAR_TEXTURES, 20, 20, 45, height - 32, zpos, 20, 7, 20, 20);
        RenderUtilities.renderTextureFromSprite(mStack, ABILITY_BAR_TEXTURES, 20, 20, 67, height - 32, zpos, 40, 7, 20, 20);





        NonNullList<Spell> spellBarList = playerData.getSpellBar().getSpellList();












        int xoffset = 0;
        zpos++;


        for (Spell spell : spellBarList) {


            if (spell != Spell.getDefaultSpell()) {


                SpellStack stack = entityData.getSpellStackRaw(spell);

                ClientProxy.spellRenderer.renderSpellAndSpellInfo(gui, stack, mStack, 3 + xoffset, height - 30, zpos);

            }


            xoffset += 22;

        }
        renderWeaponSpell(gui,mStack,width/2-91-28,height-43,zpos++);


        Resource resource = playerData.getActiveClass().getCastResource();

        if (resource != null && resource != ResourceRegistration.FOOD.get()) {

            float manaRatio = (float) (entityData.getResourceAmountRaw(resource) / player.getAttributeValue(resource.getResourceMaxAttribute().get()));


            //Mana Bar
            RenderUtilities.renderTextureFromSprite(mStack, ABILITY_BAR_TEXTURES, 88, 6, 0, height - 7, --zpos, 0, 29, 96, 6);

            ++zpos;

            RenderUtilities.renderTextureFromSprite(mStack, ABILITY_BAR_TEXTURES, 86 * manaRatio, 4, 1, height - 6, ++zpos, 1, 35, 94 * manaRatio, 4);


            mStack.pushPose();


            float textScaling = 0.60f;

            ++zpos;
            mStack.scale(textScaling, textScaling, textScaling);
            String manaData = Math.round(entityData.getResourceAmountRaw(resource)) + "/" + (int) player.getAttributeValue(resource.getResourceMaxAttribute().get());
            int stringx = 97 / 2;
            int stringy = height - 6;


            stringx *= 1 / textScaling;
            stringy *= 1 / textScaling;
            Gui.drawCenteredString(mStack, gui.getFont(), manaData, stringx, stringy, -1);

        }
        zpos -= 2;

        mStack.popPose();


    }




    public void renderWeaponSpell(ForgeIngameGui gui, PoseStack stack, int x, int y, int z) {


        Player player = Minecraft.getInstance().player;

        IPlayerData playerData = Utilities.getPlayerData(player);

        IEntityData entityData = Utilities.getEntityData(player);
        Spell weaponSpell = playerData.getActiveWeaponSpell();

         if (!Spell.isSpellDefault(weaponSpell)) {


            SpellStack weaponspelldata = entityData.getSpellStackRaw(weaponSpell);



                RenderUtilities.renderTextureFromSprite(stack, ABILITY_BAR_TEXTURES, 20, 20, x-2, y-2, z, 20, 7, 20, 20);

                ClientProxy.spellRenderer.renderSpellAndSpellInfo(gui,weaponspelldata,stack,x,y,z++);


        }

    }






}
