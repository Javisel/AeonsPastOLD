package com.javisel.aeonspast.common.config.armor;

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

public class ArmorDataLoader extends SimpleJsonResourceReloadListener {
    private static final Gson GSON_INSTANCE = Deserializers.createFunctionSerializer().create();

    private static final String folder = "items/armor";
    private Map<ResourceLocation, ArmorData> armorStatisticsMap = ImmutableMap.of();

    public ArmorDataLoader() {
        super(GSON_INSTANCE, folder);
    }


    @Override
    protected void apply(Map<ResourceLocation, JsonElement> resourceList, ResourceManager resourceManager, ProfilerFiller profilerFiller) {


        ImmutableMap.Builder<ResourceLocation, ArmorData> builder = ImmutableMap.builder();

        ResourceLocation resourceLocation = new ResourceLocation(AeonsPast.MODID, "tags/items/armor/armor.json");
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
                        ArmorData stats = getArmorProperties(location, resourceList.get(location));
                        if (stats != null)
                            builder.put(location, stats);
                    } catch (Exception exception) {
                        AeonsPast.LOGGER.error("Couldn't parse armor stats {}", location, exception);
                    }
                });


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        armorStatisticsMap = builder.build();

    }


    private ArmorData getArmorProperties(ResourceLocation location, JsonElement jsonElement) {

        if (!jsonElement.isJsonObject()) {

            return null;
        }

        JsonObject json = jsonElement.getAsJsonObject();


        return new Gson().fromJson(json, ArmorData.class);


    }

    public Map<ResourceLocation, ArmorData> getArmorStatisticsMap() {
        return armorStatisticsMap;
    }


    public ArmorData getArmorData(ResourceLocation location) {


        for (ResourceLocation test : armorStatisticsMap.keySet()) {


            if (test.equals(location)) {


                return armorStatisticsMap.get(test);
            }


        }


        return null;

    }

    public ArmorData getArmorData(Item item) {


        ResourceLocation location = item.getRegistryName();

        for (ResourceLocation test : armorStatisticsMap.keySet()) {


            if (test.equals(location)) {


                return armorStatisticsMap.get(test);
            }


        }


        return null;

    }

    public CompoundTag toNBT() {

        CompoundTag tag = new CompoundTag();
        for (Map.Entry armorEntry : armorStatisticsMap.entrySet()) {


            tag.put(armorEntry.getKey().toString(), ((ArmorData) armorEntry.getValue()).toNBT());


        }


        return tag;


    }
}
