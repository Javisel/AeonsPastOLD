package com.javisel.aeonspast.common.items.itemproperties;

public enum APDescriptors {

    VERSATILE("aeonspast.properties.versatile"),
    TWOHANDED("aeonspast.properties.twohanded"),
    HEAVY("aeonspast.properties.heavy")



    ;



    private final String translationKey;
    private final String descriptionKey;


    APDescriptors(String translationkey) {


        this.translationKey=translationkey;
        this.descriptionKey=translationkey+".description";
    }


  public String getTranslationKey() {


      return  translationKey;
  }
}
