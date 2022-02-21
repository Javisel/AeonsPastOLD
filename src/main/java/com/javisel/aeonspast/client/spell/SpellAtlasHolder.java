package com.javisel.aeonspast.client.spell;

import com.javisel.aeonspast.client.main.ClientProxy;
import com.javisel.aeonspast.common.spell.Spell;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.TextureAtlasHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.RegistryManager;

import java.util.ArrayList;
import java.util.stream.Stream;

import static com.javisel.aeonspast.AeonsPast.MODID;
import static com.javisel.aeonspast.ModBusEventHandler.SPELL_REGISTRY_NAME;

@OnlyIn(Dist.CLIENT)
public class SpellAtlasHolder extends TextureAtlasHolder {


    public SpellAtlasHolder(TextureManager p_118887_) {
        super(p_118887_, ClientProxy.SPELL_TEXTURES_LOCATION, "spells");
    }

    @Override
    protected Stream<ResourceLocation> getResourcesToLoad() {


        Object[] objects = RegistryManager.ACTIVE.getRegistry(SPELL_REGISTRY_NAME).getValues().toArray();

        ArrayList<ResourceLocation> locations = new ArrayList<>();
        for (Object o : objects) {

            Spell spell = (Spell) o;


            if (spell == Spell.getDefaultSpell()) {
                continue;
            }

            for (ResourceLocation spellLocation : spell.getSpellRenderLocations()) {


                String newloc = MODID + ":" + spellLocation.getPath();

                ResourceLocation newlocation = new ResourceLocation(newloc);


                locations.add(newlocation);

            }

        }


        return locations.stream();
    }


    public TextureAtlasSprite get(Spell spell) {


        return this.getSprite(spell.getRegistryName());


    }
}
