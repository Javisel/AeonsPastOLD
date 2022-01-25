package com.javisel.aeonspast.common.config;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.items.weapons.WeaponGenerationStats;
import com.javisel.aeonspast.common.items.weapons.WeaponStatistics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.storage.loot.Deserializers;
import net.minecraftforge.common.loot.IGlobalLootModifier;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public class WeaponConfig extends SimpleJsonResourceReloadListener {
    private static final Gson GSON_INSTANCE = Deserializers.createFunctionSerializer().create();
    private Map<ResourceLocation, WeaponGenerationStats> weaponStatisticsMap = ImmutableMap.of();
    private static final String folder = "weapon_statistics";

    public WeaponConfig( ) {
        super(GSON_INSTANCE, folder);
    }


    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceList, ResourceManager resourceManager, ProfilerFiller profilerFiller) {

        ImmutableMap.Builder<ResourceLocation, WeaponGenerationStats> builder = ImmutableMap.builder();

        ResourceLocation resourceLocation = new ResourceLocation(AeonsPast.MODID,"weapon_statistics/weapon_statistic_files.json");
        ArrayList<ResourceLocation> finalLocations = new ArrayList<ResourceLocation>();


        try {
            for (Resource resource : resourceManager.getResources(resourceLocation)) {


                InputStream stream = resource.getInputStream();


                Reader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

                JsonObject jsonobject = GsonHelper.fromJson(GSON_INSTANCE, reader, JsonObject.class);
                boolean replace = jsonobject.get("replace").getAsBoolean();
                if(replace) finalLocations.clear();
                JsonArray entryList = jsonobject.get("entries").getAsJsonArray();
                for(JsonElement entry : entryList) {
                    String loc = entry.getAsString();
                    ResourceLocation res = new ResourceLocation(loc);
                    if(finalLocations.contains(res)) finalLocations.remove(res);
                    finalLocations.add(res);
                }




                finalLocations.forEach(location -> {
                    try {
                        WeaponGenerationStats stats = getWeaponProperties(location, resourceList.get(location));
                        if(stats != null)
                            builder.put(location, stats);
                    } catch (Exception exception) {
                        AeonsPast.LOGGER.error("Couldn't parse weapon stats {}", location, exception);
                    }
                });






            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public WeaponGenerationStats getWeaponProperties(ResourceLocation location, JsonElement jsonElement) {

        if (!jsonElement.isJsonObject()) {

            return null;
        }

        JsonObject json = jsonElement.getAsJsonObject();




        WeaponGenerationStats weaponStatistics = new Gson().fromJson(json,WeaponGenerationStats.class);





        return  weaponStatistics;



    }



}
