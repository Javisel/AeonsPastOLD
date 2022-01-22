package com.javisel.aeonspast.common.combat;

public class PRCombatRules {


    public static float getDamagePostMitigations(double armor, double armorToughness, float damage) {


        float newarmor = (float) (armor + armorToughness);


        float mitigations = (float) 1 - (newarmor / (100 + newarmor));

        float newdamage = mitigations * damage;

        return newdamage;


    }


    







}
