package com.javisel.aeonspast.common.config;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.entities.EntityStatisticalData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.Deserializers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public class EntityDataLoader extends SimpleJsonResourceReloadListener {
    private static final Gson GSON_INSTANCE = Deserializers.createFunctionSerializer().create();
    private static final String folder = "entities/data";
    private   Map<ResourceLocation, EntityStatisticalData> entityStatisticsMap = ImmutableMap.of();

    public EntityDataLoader() {
        super(GSON_INSTANCE, folder);
    }


    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceList, ResourceManager resourceManager, ProfilerFiller profilerFiller) {


        System.out.println("Applying Entity Loader!");
        ImmutableMap.Builder<ResourceLocation, EntityStatisticalData> builder = ImmutableMap.builder();

        ResourceLocation resourceLocation = new ResourceLocation(AeonsPast.MODID, "entities/directory/entity_data_files.json");
        ArrayList<ResourceLocation> finalLocations = new ArrayList<ResourceLocation>();


        try {
            for (Resource resource : resourceManager.getResources(resourceLocation)) {


                 InputStream stream = resource.getInputStream();


                Reader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

                JsonObject jsonobject = GsonHelper.fromJson(GSON_INSTANCE, reader, JsonObject.class);
                boolean replace = jsonobject.get("replace").getAsBoolean();
                if (replace) finalLocations.clear();
                JsonArray entryList = jsonobject.get("entries").getAsJsonArray();
                for (JsonElement entry : entryList) {
                    String loc = entry.getAsString();
                    ResourceLocation res = new ResourceLocation(loc);
                    finalLocations.remove(res);
                    finalLocations.add(res);
                }


                finalLocations.forEach(location -> {

                     try {
                        EntityStatisticalData stats = getEntityProperties(location, resourceList.get(location));
                        if (stats != null)
                            builder.put(location, stats);
                    } catch (Exception exception) {
                        AeonsPast.LOGGER.error("Couldn't parse entity stats {}", location, exception);
                    }
                });


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        entityStatisticsMap =builder.build();

    }


    private EntityStatisticalData getEntityProperties(ResourceLocation location, JsonElement jsonElement) {

        if (!jsonElement.isJsonObject()) {

            return null;
        }

        JsonObject json = jsonElement.getAsJsonObject();


        EntityStatisticalData weaponData = new Gson().fromJson(json, EntityStatisticalData.class);


        return weaponData;


    }

    public Map<ResourceLocation, EntityStatisticalData> getEntityStatisticsMap() {
        return entityStatisticsMap;
    }


    public EntityStatisticalData getEntityData(ResourceLocation location) {


        for (ResourceLocation test: entityStatisticsMap.keySet()) {


            if (test.equals(location)) {


                return entityStatisticsMap.get(test);
            }




        }



        return null;

    }

    public EntityStatisticalData getEntityData(Entity entity) {


        ResourceLocation location = entity.getType().getRegistryName();

        for (ResourceLocation test: entityStatisticsMap.keySet()) {


            if (test.equals(location)) {


                return entityStatisticsMap.get(test);
            }




        }



        return null;

    }



}
