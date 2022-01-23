package com.javisel.aeonspast.client.overlays;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.client.RenderUtilities;
import com.javisel.aeonspast.client.main.ClientProxy;
import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.IPlayerData;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.registration.ResourceRegistration;
import com.javisel.aeonspast.common.resource.Resource;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.APUtilities;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;


@OnlyIn(Dist.CLIENT)
public class APAbilityBar implements IIngameOverlay {


    public static final ResourceLocation ABILITY_BAR_TEXTURES = new ResourceLocation(AeonsPast.MODID, "textures/gui/ability_bar.png");

    private ItemStack dummy = new ItemStack(Items.DIAMOND);

    @Override
    public void render(ForgeIngameGui gui, PoseStack mStack, float partialTicks, int width, int height) {

        Minecraft minecraft = Minecraft.getInstance();


        IEntityData entityData = APUtilities.getEntityData(minecraft.player);

        IPlayerData playerData = APUtilities.getPlayerData(minecraft.player);
        Player player = minecraft.player;
        //Renders the Item Holders

        int zpos = -40;
        RenderUtilities.renderTextureFromSprite(mStack, ABILITY_BAR_TEXTURES, 20, 27, 1, height - 39, zpos, 0, 0, 20, 27);
        RenderUtilities.renderTextureFromSprite(mStack, ABILITY_BAR_TEXTURES, 20, 20, 23, height - 32, zpos, 20, 7, 20, 20);
        RenderUtilities.renderTextureFromSprite(mStack, ABILITY_BAR_TEXTURES, 20, 20, 45, height - 32, zpos, 20, 7, 20, 20);
        RenderUtilities.renderTextureFromSprite(mStack, ABILITY_BAR_TEXTURES, 20, 20, 67, height - 32, zpos, 40, 7, 20, 20);


        NonNullList<Spell> spellBarList = playerData.getSpellBar().getSpellList();


        int xoffset = 0;
        zpos++;


        Resource resource = playerData.getActiveClass().getCastResource();


        for (Spell spell : spellBarList) {


            if (spell != Spell.getDefaultSpell()) {


                SpellStack stack = entityData.getSpellStackRaw(spell);

                ClientProxy.spellRenderer.renderSpellAndSpellInfo(gui, stack, mStack, 3 + xoffset, height - 30, zpos);

            }


            xoffset += 22;

        }


        if (resource !=null && resource!= ResourceRegistration.FOOD.get()) {

            float manaRatio = (float) (entityData.getResourceAmount(resource) / player.getAttributeValue(resource.getResourceMaxAttribute().get()));


            //Mana Bar
            RenderUtilities.renderTextureFromSprite(mStack, ABILITY_BAR_TEXTURES, 88, 6, 0, height - 7, --zpos, 0, 29, 96, 6);

            ++zpos;

            RenderUtilities.renderTextureFromSprite(mStack, ABILITY_BAR_TEXTURES, 86 * manaRatio, 4, 1, height - 6, ++zpos, 1, 35, 94 * manaRatio, 4);


            mStack.pushPose();


            float textScaling = 0.60f;

            ++zpos;
            mStack.scale(textScaling, textScaling, textScaling);
            String manaData = ((int) Math.round(entityData.getResourceAmount(resource))) + "/" + (int) player.getAttributeValue(resource.getResourceMaxAttribute().get());
            int stringx = 97 / 2;
            int stringy = height - 6;


            stringx *= 1 / textScaling;
            stringy *= 1 / textScaling;
            Gui.drawCenteredString(mStack, gui.getFont(), manaData, stringx, stringy, -1);

        }
        zpos -= 2;

        mStack.popPose();


    }


}
