package com.javisel.aeonspast.common.items.itemproperties;

public class APItemProperties {


    APItemRarity rarity;
    APDescriptors[] descriptors;



    UseDescriptors[] useDescriptors;

    public APItemProperties(APItemRarity rarity, APDescriptors... descriptors) {


        this.rarity = rarity;
        this.descriptors = descriptors;


    }
    public APItemProperties(APItemRarity rarity, UseDescriptors[] useDescriptors,APDescriptors... descriptors) {


        this.rarity = rarity;
        this.descriptors = descriptors;
        this.useDescriptors=useDescriptors;

    }
    public UseDescriptors[] getUseDescriptors() {
        return useDescriptors;
    }
}
