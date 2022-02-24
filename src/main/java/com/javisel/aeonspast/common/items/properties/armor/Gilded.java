package com.javisel.aeonspast.common.items.properties.armor;

import com.javisel.aeonspast.common.combat.damage.instances.DamageInstance;
import com.javisel.aeonspast.common.combat.damage.instances.DamageModifier;
import com.javisel.aeonspast.common.items.properties.ArmorProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;

import java.util.UUID;

public class Gilded extends ArmorProperty {
    public Gilded() {
        setDisplayed();
    }

    @Override
    public boolean onOwnerPreHit(LivingEntity attacker, LivingEntity victim, DamageInstance damageInstance) {


        if (super.onOwnerPreHit(attacker, victim, damageInstance)) {

            if (attacker.getMobType() == MobType.UNDEAD) {


                DamageModifier modifier = new DamageModifier(UUID.fromString("5458148e-d777-45ca-9ac3-5222080844d7"),super.getRegistryName().toString(), DamageModifier.Operation.MULTIPLE_TOTAL,-0.025f);


                damageInstance.addModifier(modifier);

            }


            return true;
        } else {

            return false;
        }
    }
}
