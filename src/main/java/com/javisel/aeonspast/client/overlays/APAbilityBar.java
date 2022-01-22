package com.javisel.aeonspast.client.overlays;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.client.RenderUtilities;
import com.javisel.aeonspast.client.main.ClientProxy;
import com.javisel.aeonspast.client.spell.SpellRenderer;
import com.javisel.aeonspast.common.capabiltiies.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.IPlayerData;
import com.javisel.aeonspast.common.items.SpellContainer;
import com.javisel.aeonspast.common.items.emblem.BasicEmblem;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import com.javisel.aeonspast.common.registration.SpellRegistration;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.APUtilities;
import com.javisel.aeonspast.utilities.StringKeys;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.ArrayList;
import java.util.List;


@OnlyIn(Dist.CLIENT)
public class APAbilityBar implements IIngameOverlay {



    public  static final ResourceLocation ABILITY_BAR_TEXTURES = new ResourceLocation(AeonsPast.MODID, "textures/gui/ability_bar.png");

    private ItemStack dummy = new ItemStack(Items.DIAMOND);
    @Override
    public void render(ForgeIngameGui gui, PoseStack mStack, float partialTicks, int width, int height) {

        Minecraft minecraft = Minecraft.getInstance();

        //Minecraft.getInstance().getItemRenderer().renderAndDecorateItem(dummy,5,5);


        ItemRenderer itemRenderer = minecraft.getItemRenderer();
        IEntityData entityData = APUtilities.getEntityData(minecraft.player);

        IPlayerData playerData = APUtilities.getPlayerData(minecraft.player);
        Player player = minecraft.player;
        //Renders the Item Holders

         int zpos =  -40;
        RenderUtilities.renderTextureFromSprite(mStack,ABILITY_BAR_TEXTURES,20,27,1,height-39,zpos,0,0,20,27);
        RenderUtilities.renderTextureFromSprite(mStack,ABILITY_BAR_TEXTURES,20,20,23,height-32,zpos,20,7,20,20);
        RenderUtilities.renderTextureFromSprite(mStack,ABILITY_BAR_TEXTURES,20,20,45,height-32,zpos,20 ,7,20,20);
        RenderUtilities.renderTextureFromSprite(mStack,ABILITY_BAR_TEXTURES,20,20,67,height-32,zpos,40,7,20,20);





        List<SlotResult> emblems = CuriosApi.getCuriosHelper().findCurios(player, StringKeys.EMBLEM_IDENTIFIER);

        ArrayList<Spell> spellBarList = playerData.getSpellBar();



        int xoffset = 0;
        for (Spell  spell: spellBarList) {


            SpellStack stack= entityData.getSpellStackRaw(spell);


            ClientProxy.spellRenderer.renderSpellAndSpellInfo(gui,stack,mStack,3+xoffset,height-39);

            xoffset+=20;

        }
        SpellStack stack= new SpellStack(SpellRegistration.WARRIOR_CLASS_SPELL.get());

        ClientProxy.spellRenderer.renderSpellAndSpellInfo(gui,stack,mStack,3+xoffset,height-39);






        float manaRatio = (float) (entityData.getMana() / player.getAttributeValue(AttributeRegistration.MAXIMUM_RESOURCE.get()));


        //Mana Bar
        RenderUtilities.renderTextureFromSprite(mStack,ABILITY_BAR_TEXTURES,88,6,0,height-7,--zpos,0,29,96,6);

        ++zpos;

        RenderUtilities.renderTextureFromSprite(mStack,ABILITY_BAR_TEXTURES,86 * manaRatio,4,1,height-6,++zpos,1,35,94 * manaRatio,4);




        mStack.pushPose();


        float textScaling = 0.60f;

        ++zpos;
        mStack.scale(textScaling,textScaling,textScaling);
        String manaData =  (int)entityData.getMana() + "/" + (int) player.getAttributeValue(AttributeRegistration.MAXIMUM_RESOURCE.get());
        int stringx = 97/2;
        int stringy = height-6;






       stringx*= 1/textScaling;
        stringy*= 1/textScaling;
        Gui.drawCenteredString(mStack, gui.getFont(), manaData, stringx, stringy, -1);

        zpos-=2;

        mStack.popPose();







    }


}
