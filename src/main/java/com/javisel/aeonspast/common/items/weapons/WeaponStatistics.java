package com.javisel.aeonspast.common.items.weapons;

import com.javisel.aeonspast.common.combat.damagetypes.APDamageSubType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraftforge.registries.ForgeRegistries;

import static com.javisel.aeonspast.utilities.StringKeys.*;

public class WeaponStatistics {


    private float baseDamage;
    private float baseDamageLevelScaling;
    private float attributeScalar;
    private Attribute attribute;
    private APDamageSubType damageType;
    private float attackSpeed;
    private float criticalDamage;
    private float durability;
    public WeaponStatistics(float baseDamage, float attackSpeed, float damageLevelScaling, float attributeScalar, Attribute attribute, APDamageSubType subType, float criticalDamage, float durability) {
        this.baseDamage = baseDamage;
        this.baseDamageLevelScaling = damageLevelScaling;
        this.attributeScalar = attributeScalar;
        this.attribute = attribute;
        this.damageType = subType;
        this.attackSpeed=attackSpeed;
        this.criticalDamage=criticalDamage;
        this.durability=durability;
    }

    public WeaponStatistics() {



    }


 public CompoundTag toNBT() {


        CompoundTag tag = new CompoundTag();

        tag.putFloat(BASE_DAMAGE,baseDamage);
        tag.putFloat(BASE_DAMAGE_LEVEL_SCALING, baseDamageLevelScaling);
        tag.putFloat(ATTACK_SPEED,attackSpeed);
        tag.putFloat(ATTRIBUTE_SCALING,attributeScalar);
        tag.putString(ATTRIBUTE, attribute.getRegistryName().toString());
        tag.putString(DAMAGE_TYPE,damageType.getUnlocalizedName());
        tag.putFloat(CRITICAL_DAMAGE,criticalDamage);
        tag.putFloat(DURABILITY,durability);

        return tag;


 }

 public static WeaponStatistics getByNBT(CompoundTag tag) {


  WeaponStatistics weaponStatistics = new WeaponStatistics();

  weaponStatistics.baseDamage=tag.getFloat(BASE_DAMAGE);
  weaponStatistics.baseDamageLevelScaling =tag.getFloat(BASE_DAMAGE_LEVEL_SCALING);
  weaponStatistics.attributeScalar=tag.getFloat(ATTRIBUTE_SCALING);

  String key = tag.getString(ATTRIBUTE);

     weaponStatistics.attribute= ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(key));
 weaponStatistics.damageType=APDamageSubType.getByString(tag.getString(DAMAGE_TYPE));
weaponStatistics.criticalDamage=tag.getFloat(CRITICAL_DAMAGE);
weaponStatistics.durability=tag.getFloat(DURABILITY);

        return weaponStatistics;
 }








    public float getBaseDamage() {
        return baseDamage;
    }

    public float getBaseDamageLevelScaling() {
        return baseDamageLevelScaling;
    }

    public float getAttributeScalar() {
        return attributeScalar;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public APDamageSubType getSubType() {
        return damageType;
    }
}
