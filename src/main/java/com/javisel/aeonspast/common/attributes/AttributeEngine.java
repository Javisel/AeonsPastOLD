package com.javisel.aeonspast.common.attributes;

import com.javisel.aeonspast.common.config.weapon.WeaponData;
import com.javisel.aeonspast.common.items.ItemEngine;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public class AttributeEngine {





    public static double getAttributeValueExcludeModifiers(LivingEntity entity, Attribute attribute, UUID...excludes) {


        AttributeInstance instance = entity.getAttribute(attribute);



                 double base =  instance.getBaseValue();

                 double flats = 0;
                 double base_multipliers = 0;
                 double total_multipliers = 0;

                double result = base;
                 for(AttributeModifier attributemodifier : instance.getModifiers(AttributeModifier.Operation.ADDITION)) {




                     for (UUID id : excludes) {


                         if (!id.equals(attributemodifier.getId())) {


                             flats += attributemodifier.getAmount();

                         }



                     }
                  }



                 for(AttributeModifier attributemodifier : instance.getModifiers(AttributeModifier.Operation.MULTIPLY_BASE)) {
                     for (UUID id : excludes) {


                         if (!id.equals(attributemodifier.getId())) {


                             base_multipliers += base * attributemodifier.getAmount();

                         }



                     }
                 }

                 for(AttributeModifier attributemodifier : instance.getModifiers(AttributeModifier.Operation.MULTIPLY_TOTAL)) {
                     for (UUID id : excludes) {


                         if (!id.equals(attributemodifier.getId())) {


                             total_multipliers +=  1.0D + attributemodifier.getAmount();

                         }



                     }

                 }


                 result = (base + flats + base_multipliers) * total_multipliers;


    return result;

    }






}
