package com.javisel.aeonspast.common.combat.damagesource;

import com.javisel.aeonspast.common.combat.DamageTypes;
import com.javisel.aeonspast.common.combat.DamageInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;

import static net.minecraft.world.damagesource.DamageSource.*;

public class VanillaAPDamageSourceMap {

    private static final HashMap<DamageSource,APDamageSource> VANILLA_REPLACEMENTS = new HashMap<>();



    static  {

       VANILLA_REPLACEMENTS.put(IN_FIRE,new EnvironmentalDamageSource("inFire",new DamageInstance(DamageTypes.FIRE)));
        VANILLA_REPLACEMENTS.put(LIGHTNING_BOLT,new EnvironmentalDamageSource("lightningBolt",new DamageInstance(DamageTypes.ELECTRIC)));
        VANILLA_REPLACEMENTS.put(ON_FIRE,new EnvironmentalDamageSource("onFire",new DamageInstance(DamageTypes.FIRE)));
        VANILLA_REPLACEMENTS.put(LAVA,new EnvironmentalDamageSource("lava",new DamageInstance(DamageTypes.FIRE)));
        VANILLA_REPLACEMENTS.put(HOT_FLOOR,new EnvironmentalDamageSource("hotFloor",new DamageInstance(DamageTypes.FIRE)));
        VANILLA_REPLACEMENTS.put(IN_WALL,new EnvironmentalDamageSource("inWall",new DamageInstance(DamageTypes.ENVIRONMENTAL)));
        VANILLA_REPLACEMENTS.put(CRAMMING,new EnvironmentalDamageSource("cramming",new DamageInstance(DamageTypes.ENVIRONMENTAL)));
        VANILLA_REPLACEMENTS.put(DROWN,new EnvironmentalDamageSource("drown",new DamageInstance(DamageTypes.ENVIRONMENTAL)));
        VANILLA_REPLACEMENTS.put(STARVE,new EnvironmentalDamageSource("starve",new DamageInstance(DamageTypes.ENVIRONMENTAL)));
        VANILLA_REPLACEMENTS.put(CACTUS,new EnvironmentalDamageSource("cactus",new DamageInstance(DamageTypes.PUNCTURE)));
        VANILLA_REPLACEMENTS.put(FALL,new EnvironmentalDamageSource("cramming",new DamageInstance(DamageTypes.ENVIRONMENTAL)));
        VANILLA_REPLACEMENTS.put(FLY_INTO_WALL,new EnvironmentalDamageSource("flyIntoWall",new DamageInstance(DamageTypes.ENVIRONMENTAL)));
        VANILLA_REPLACEMENTS.put(OUT_OF_WORLD,new EnvironmentalDamageSource("outOfWorld",new DamageInstance(DamageTypes.ENVIRONMENTAL)));
        VANILLA_REPLACEMENTS.put(GENERIC,new EnvironmentalDamageSource("generic",new DamageInstance(DamageTypes.IMPACT)));
        VANILLA_REPLACEMENTS.put(MAGIC,new EnvironmentalDamageSource("magic",new DamageInstance(DamageTypes.ARCANE)));
        VANILLA_REPLACEMENTS.put(WITHER,new EnvironmentalDamageSource("wither",new DamageInstance(DamageTypes.WITHER)));
        VANILLA_REPLACEMENTS.put(ANVIL, (APDamageSource) new EnvironmentalDamageSource("anvil",new DamageInstance(DamageTypes.IMPACT)).damageHelmet());
        VANILLA_REPLACEMENTS.put(FALLING_BLOCK, (APDamageSource) new EnvironmentalDamageSource("fallingBlock",new DamageInstance(DamageTypes.IMPACT)).damageHelmet());
        VANILLA_REPLACEMENTS.put(DRAGON_BREATH,new EnvironmentalDamageSource("dragonBreath",new DamageInstance(DamageTypes.ENDER)));
        VANILLA_REPLACEMENTS.put(DRY_OUT,new EnvironmentalDamageSource("dryout",new DamageInstance(DamageTypes.ENVIRONMENTAL)));
        VANILLA_REPLACEMENTS.put(SWEET_BERRY_BUSH,new EnvironmentalDamageSource("sweetBerryBush",new DamageInstance(DamageTypes.PUNCTURE)));
        VANILLA_REPLACEMENTS.put(FREEZE,new EnvironmentalDamageSource("freeze",new DamageInstance(DamageTypes.COLD)));
        VANILLA_REPLACEMENTS.put(FALLING_STALACTITE, (APDamageSource) new EnvironmentalDamageSource("fallingstalacite",new DamageInstance(DamageTypes.PUNCTURE)).damageHelmet());
        VANILLA_REPLACEMENTS.put(STALAGMITE, (APDamageSource) new EnvironmentalDamageSource("stalagmite",new DamageInstance(DamageTypes.PUNCTURE)).setIsFall());














    }


    public  static APDamageSource getNewDamageSource(DamageSource source, float amount, LivingEntity entity) {



        APDamageSource damageSource = VANILLA_REPLACEMENTS.get(source);


        if (damageSource==null) {

            System.err.println("Damage Type " + damageSource.getMsgId() +" has no AP Equivalent!");
            return null;
        }
        damageSource.instance.preMitigationsAmount=amount * 5;

        if (entity!=null) {

            if (damageSource.instance.getDamage_type() ==DamageTypes.WITHER) {


                damageSource.instance.preMitigationsAmount += entity.getMaxHealth() * 0.0325;



            }

            if (damageSource.instance.getDamage_type() ==DamageTypes.ENDER) {


                damageSource.instance.preMitigationsAmount += entity.getMaxHealth() * 0.025;



            }

        }





        return damageSource;

    }






}
