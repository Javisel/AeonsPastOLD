package com.javisel.aeonspast.common.effects;

import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.registries.RegistryObject;

public class DamageEffect extends ComplexEffect{


    private final DamageTypeEnum damageType;
    private final RegistryObject<Attribute> chanceAttribute;

    protected DamageEffect(int p_19452_, DamageTypeEnum damageType, RegistryObject<Attribute> chanceAttribute) {
        super(MobEffectCategory.HARMFUL, p_19452_);
        this.damageType = damageType;
        this.chanceAttribute = chanceAttribute;
    }


    public DamageTypeEnum getDamageType() {
        return damageType;
    }

    public Attribute getChanceAttribute() {
        return chanceAttribute.get();
    }
}
