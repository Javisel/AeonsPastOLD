package com.javisel.aeonspast.common.combat.damage.sources;

import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.combat.damage.instances.DamageInstance;
import com.javisel.aeonspast.common.combat.damage.instances.DamageModifier;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashMap;
import java.util.UUID;

import static net.minecraft.world.damagesource.DamageSource.*;

public class VanillaAPDamageSourceMap {

    private static final HashMap<DamageSource, APDamageSource> VANILLA_REPLACEMENTS = new HashMap<>();


    static {

        VANILLA_REPLACEMENTS.put(IN_FIRE, new EnvironmentalDamageSource("inFire", new DamageInstance(DamageTypeEnum.FIRE)));
        VANILLA_REPLACEMENTS.put(LIGHTNING_BOLT, new EnvironmentalDamageSource("lightningBolt", new DamageInstance(DamageTypeEnum.ELECTRIC)));
        VANILLA_REPLACEMENTS.put(ON_FIRE, new EnvironmentalDamageSource("onFire", new DamageInstance(DamageTypeEnum.FIRE)));
        VANILLA_REPLACEMENTS.put(LAVA, new EnvironmentalDamageSource("lava", new DamageInstance(DamageTypeEnum.FIRE)));
        VANILLA_REPLACEMENTS.put(HOT_FLOOR, new EnvironmentalDamageSource("hotFloor", new DamageInstance(DamageTypeEnum.FIRE)));
        VANILLA_REPLACEMENTS.put(IN_WALL, new EnvironmentalDamageSource("inWall", new DamageInstance(DamageTypeEnum.ENVIRONMENTAL)));
        VANILLA_REPLACEMENTS.put(CRAMMING, new EnvironmentalDamageSource("cramming", new DamageInstance(DamageTypeEnum.ENVIRONMENTAL)));
        VANILLA_REPLACEMENTS.put(DROWN, new EnvironmentalDamageSource("drown", new DamageInstance(DamageTypeEnum.ENVIRONMENTAL)));
        VANILLA_REPLACEMENTS.put(STARVE, new EnvironmentalDamageSource("starve", new DamageInstance(DamageTypeEnum.ENVIRONMENTAL)));
        VANILLA_REPLACEMENTS.put(CACTUS, new EnvironmentalDamageSource("cactus", new DamageInstance(DamageTypeEnum.PUNCTURE)));
        VANILLA_REPLACEMENTS.put(FALL, new EnvironmentalDamageSource("cramming", new DamageInstance(DamageTypeEnum.ENVIRONMENTAL)));
        VANILLA_REPLACEMENTS.put(FLY_INTO_WALL, new EnvironmentalDamageSource("flyIntoWall", new DamageInstance(DamageTypeEnum.ENVIRONMENTAL)));
        VANILLA_REPLACEMENTS.put(OUT_OF_WORLD, new EnvironmentalDamageSource("outOfWorld", new DamageInstance(DamageTypeEnum.VOID)));
        VANILLA_REPLACEMENTS.put(GENERIC, new EnvironmentalDamageSource("generic", new DamageInstance(DamageTypeEnum.IMPACT)));
        VANILLA_REPLACEMENTS.put(MAGIC, new EnvironmentalDamageSource("magic", new DamageInstance(DamageTypeEnum.ARCANE)));
        VANILLA_REPLACEMENTS.put(WITHER, new EnvironmentalDamageSource("wither", new DamageInstance(DamageTypeEnum.WITHER)));
        VANILLA_REPLACEMENTS.put(ANVIL, (APDamageSource) new EnvironmentalDamageSource("anvil", new DamageInstance(DamageTypeEnum.IMPACT)).damageHelmet());
        VANILLA_REPLACEMENTS.put(FALLING_BLOCK, (APDamageSource) new EnvironmentalDamageSource("fallingBlock", new DamageInstance(DamageTypeEnum.IMPACT)).damageHelmet());
        VANILLA_REPLACEMENTS.put(DRAGON_BREATH, new EnvironmentalDamageSource("dragonBreath", new DamageInstance(DamageTypeEnum.ENDER)));
        VANILLA_REPLACEMENTS.put(DRY_OUT, new EnvironmentalDamageSource("dryout", new DamageInstance(DamageTypeEnum.ENVIRONMENTAL)));
        VANILLA_REPLACEMENTS.put(SWEET_BERRY_BUSH, new EnvironmentalDamageSource("sweetBerryBush", new DamageInstance(DamageTypeEnum.PUNCTURE)));
        VANILLA_REPLACEMENTS.put(FREEZE, new EnvironmentalDamageSource("freeze", new DamageInstance(DamageTypeEnum.COLD)));
        VANILLA_REPLACEMENTS.put(FALLING_STALACTITE, (APDamageSource) new EnvironmentalDamageSource("fallingstalacite", new DamageInstance(DamageTypeEnum.PUNCTURE)).damageHelmet());
        VANILLA_REPLACEMENTS.put(STALAGMITE, (APDamageSource) new EnvironmentalDamageSource("stalagmite", new DamageInstance(DamageTypeEnum.PUNCTURE)).setIsFall());


    }


    public static APDamageSource getNewDamageSource(DamageSource source, float amount, LivingEntity entity) {


        APDamageSource damageSource = VANILLA_REPLACEMENTS.get(source);


        if (damageSource == null) {

            System.err.println("Damage Type " + damageSource.getMsgId() + " has no AP Equivalent!");
            return null;
        }
        damageSource.instance.setBaseAmount(amount * 5);

        if (entity != null) {

            if (damageSource.instance.getDamageType() == DamageTypeEnum.WITHER) {



                DamageModifier modifier = new DamageModifier(UUID.fromString("d50a2cbd-2e8c-4a1e-921e-f139b640c7a9"), "healthmod", DamageModifier.Operation.ADDITON,entity.getMaxHealth() * 0.0325);

                damageSource.instance.addModifier(modifier);
            }


        }


        return damageSource;

    }


}
