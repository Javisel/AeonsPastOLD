package com.javisel.aeonspast.common.config;

import com.google.common.collect.ImmutableMap;
import com.google.gson.*;
import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.playerclasses.ClassData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public class ClassDataLoader extends SimpleJsonResourceReloadListener {
    private static final Gson GSON_INSTANCE = (new GsonBuilder()).setPrettyPrinting().create();
    private static final String folder = "classes";
    private Map<ResourceLocation, ClassData> classStatisticsMap = ImmutableMap.of();


    public ClassDataLoader() {
        super(GSON_INSTANCE, folder);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceList, ResourceManager resourceManager, ProfilerFiller profilerFiller) {

        ImmutableMap.Builder<ResourceLocation, ClassData> builder = ImmutableMap.builder();

        ResourceLocation resourceLocation = new ResourceLocation(AeonsPast.MODID, "classes/class_data_files.json");
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
                        ClassData stats = getClassProperties(location, resourceList.get(location));
                        if (stats != null)
                            builder.put(location, stats);
                    } catch (Exception exception) {
                        AeonsPast.LOGGER.error("Couldn't parse class  stats {}", location, exception);
                    }
                });


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        classStatisticsMap = builder.build();

    }


    public ClassData getClassProperties(ResourceLocation location, JsonElement jsonElement) {

        if (!jsonElement.isJsonObject()) {

            return null;
        }

        JsonObject json = jsonElement.getAsJsonObject();


        ClassData classStatistics = new Gson().fromJson(json, ClassData.class);


        return classStatistics;


    }

    public Map<ResourceLocation, ClassData> getClassStatisticsMap() {
        return classStatisticsMap;
    }
}
