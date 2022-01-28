package com.javisel.aeonspast.common.capabiltiies.itemstack;

import com.javisel.aeonspast.common.items.properties.ItemProperty;
import com.javisel.aeonspast.common.items.properties.ItemRarity;
import com.javisel.aeonspast.common.spell.Spell;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;

import static com.javisel.aeonspast.utilities.StringKeys.PROPERTIES;
import static com.javisel.aeonspast.utilities.StringKeys.SPELL;


public class ItemStackData implements IItemStackData{

     private ArrayList<ItemProperty> itemProperties;
    private Spell spell;



    @Override
    public ArrayList<ItemProperty> getItemProperties() {
        if (itemProperties==null) {
            itemProperties = new ArrayList<>();
        }
        return itemProperties;
    }

    @Override
    public Spell getSpell() {
        return spell;
    }

    @Override
    public void setSpell(Spell spell) {

        this.spell=spell;


    }

    public CompoundTag toNBT() {

        CompoundTag tag = new CompoundTag();


        if (itemProperties!=null) {

            CompoundTag properties = new CompoundTag();
            for (ItemProperty property : itemProperties) {

                tag.putString(property.getRegistryName().toString(),"");



            }




            tag.put(PROPERTIES,properties);


        }

        if (spell!=null) {


            tag.putString(SPELL,spell.getRegistryName().toString());


        }




            return tag;

    }


    public void readNBT(CompoundTag tag) {

        if (tag.contains(PROPERTIES)) {


            CompoundTag properties = tag.getCompound(PROPERTIES);


            for (String key : properties.getAllKeys()) {


               itemProperties = new ArrayList<>();
               itemProperties.add(ItemProperty.getPropertyByLocation(new ResourceLocation(key)));




            }





        }

        if (tag.contains(SPELL)) {

            spell = Spell.getSpellByResourceLocation(new ResourceLocation(tag.getString(SPELL)) );

        }




    }

    @Override
    public ItemRarity getRarity() {


        if (itemProperties==null) {
            return null;
        }

        else {


            for (ItemProperty property : itemProperties) {


                if (property instanceof ItemRarity) {

                    return (ItemRarity) property;
                }
            }

        }



        return null;
    }
}
