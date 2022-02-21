package com.javisel.aeonspast.common.config.trinket;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.javisel.aeonspast.AeonsPast;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.Deserializers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public class TrinketDataLoader extends SimpleJsonResourceReloadListener {
    private static final Gson GSON_INSTANCE = Deserializers.createFunctionSerializer().create();

    private static final String folder = "items/trinkets";
    private Map<ResourceLocation, TrinketData> trinketStatisticsMap = ImmutableMap.of();

    public TrinketDataLoader() {
        super(GSON_INSTANCE, folder);
    }


    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceList, ResourceManager resourceManager, ProfilerFiller profilerFiller) {


        ImmutableMap.Builder<ResourceLocation, TrinketData> builder = ImmutableMap.builder();

        ResourceLocation resourceLocation = new ResourceLocation(AeonsPast.MODID, "tags/items/trinkets/all_trinkets.json");
        ArrayList<ResourceLocation> finalLocations = new ArrayList<ResourceLocation>();


        try {
            for (Resource resource : resourceManager.getResources(resourceLocation)) {


                InputStream stream = resource.getInputStream();


                Reader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

                JsonObject jsonobject = GsonHelper.fromJson(GSON_INSTANCE, reader, JsonObject.class);
                boolean replace = jsonobject.get("replace").getAsBoolean();
                if (replace) finalLocations.clear();
                JsonArray entryList = jsonobject.get("values").getAsJsonArray();
                for (JsonElement entry : entryList) {
                    String loc = entry.getAsString();
                    ResourceLocation res = new ResourceLocation(loc);
                    finalLocations.remove(res);
                    finalLocations.add(res);
                }


                finalLocations.forEach(location -> {

                    System.out.println("Location: " + location.toString());
                    try {
                        TrinketData stats = getTrinketProperties(location, resourceList.get(location));
                        if (stats != null)
                            builder.put(location, stats);
                    } catch (Exception exception) {
                        AeonsPast.LOGGER.error("Couldn't parse trinket stats {}", location, exception);
                    }
                });


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        trinketStatisticsMap = builder.build();

    }


    private TrinketData getTrinketProperties(ResourceLocation location, JsonElement jsonElement) {

        if (!jsonElement.isJsonObject()) {

            return null;
        }

        JsonObject json = jsonElement.getAsJsonObject();


        return new Gson().fromJson(json, TrinketData.class);


    }

    public Map<ResourceLocation, TrinketData> getTrinketStatisticsMap() {
        return trinketStatisticsMap;
    }


    public TrinketData getTrinketData(ResourceLocation location) {


        for (ResourceLocation test : trinketStatisticsMap.keySet()) {


            if (test.equals(location)) {


                return trinketStatisticsMap.get(test);
            }


        }


        return null;

    }

    public TrinketData getTrinketData(Item item) {


        ResourceLocation location = item.getRegistryName();

        for (ResourceLocation test : trinketStatisticsMap.keySet()) {


            if (test.equals(location)) {


                return trinketStatisticsMap.get(test);
            }


        }


        return null;

    }

    public CompoundTag toNBT() {

        CompoundTag tag = new CompoundTag();
        for (Map.Entry trinketEntry : trinketStatisticsMap.entrySet()) {


            tag.put(trinketEntry.getKey().toString(), ((TrinketData) trinketEntry.getValue()).toNBT());


        }


        return tag;


    }
}
