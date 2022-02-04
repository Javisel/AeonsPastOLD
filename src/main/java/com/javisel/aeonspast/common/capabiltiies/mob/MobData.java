package com.javisel.aeonspast.common.capabiltiies.mob;

import com.javisel.aeonspast.common.entities.entitytraits.EntityTrait;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;

import static com.javisel.aeonspast.utilities.StringKeys.ENTITY_TRAITS;

public class MobData implements IMobData {

    ArrayList<EntityTrait> traits = new ArrayList<>();


    @Override
    public float attackTimer() {
        return 0;
    }

    @Override
    public ArrayList<EntityTrait> getEntityTraits() {
        return traits;
    }

    public void fromNBT(CompoundTag tag) {


        if (tag.contains(ENTITY_TRAITS)) {


            CompoundTag traittag = tag.getCompound(ENTITY_TRAITS);


            for (String key : traittag.getAllKeys()) {


                EntityTrait trait = EntityTrait.getTraitByLocation(new ResourceLocation(key));


                if (trait != null) {

                    traits.add(trait);

                }


            }


        }


    }

    public CompoundTag toNBT() {


        CompoundTag tag = new CompoundTag();


        if (!traits.isEmpty()) {


            CompoundTag entityTag = new CompoundTag();


            for (EntityTrait trait : getEntityTraits()) {


                entityTag.putString(trait.getRegistryName().toString(), "");


            }

            tag.put(ENTITY_TRAITS, entityTag);

        }


        return tag;
    }


}
