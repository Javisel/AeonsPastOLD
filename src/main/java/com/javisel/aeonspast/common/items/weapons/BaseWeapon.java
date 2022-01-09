package com.javisel.aeonspast.common.items.weapons;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.javisel.aeonspast.common.attributes.APAttributeContainer;
import com.javisel.aeonspast.common.items.BaseItem;
import com.javisel.aeonspast.common.items.itemproperties.APItemProperties;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class BaseWeapon extends BaseItem {


    private final float attackDamage;
    private final float baseattackSpeed;
    protected Multimap<Attribute, AttributeModifier> defaultModifiers;
    private ArrayList<APAttributeContainer> attributeList;

    public BaseWeapon(Item.Properties properties, APItemProperties apItemProperties, float baseAttackDamage, float baseAttackSpeed, @Nullable APAttributeContainer... attributes) {
        super(properties, apItemProperties);


        this.attackDamage = baseAttackDamage;
        this.baseattackSpeed = baseAttackSpeed;
        if (attributes != null) {

            attributeList = new ArrayList<>();
            for (APAttributeContainer attribute : attributes) {

                attributeList.add(attribute);

            }

        }
    }


    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {

        if (defaultModifiers == null) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", baseattackSpeed, AttributeModifier.Operation.ADDITION));
            for (APAttributeContainer attribute : attributeList) {

                builder.put(attribute.getAttribute(), attribute.getModifier());


            }
            this.defaultModifiers = builder.build();
        }
        return slot == EquipmentSlot.MAINHAND ? defaultModifiers : super.getAttributeModifiers(slot, stack);
    }
}
