package com.javisel.aeonspast.client.spell;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;

public class SpellModelLoader extends SimpleJsonResourceReloadListener {


    public SpellModelLoader(Gson p_10768_, String p_10769_) {
        super(p_10768_, p_10769_);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> map, ResourceManager manager, ProfilerFiller profilerFiller) {


    }

    @Override
    public String getName() {
        return null;
    }
}
