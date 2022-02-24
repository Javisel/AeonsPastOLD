package com.javisel.aeonspast.common.combat.damage.instances;

import com.javisel.aeonspast.utilities.StringKeys;
import net.minecraft.nbt.CompoundTag;

import java.util.UUID;

import static com.javisel.aeonspast.utilities.StringKeys.*;

public class DamageModifier {

    final UUID id;
    final String name;
    final Operation operation;
    final double amount;

    public DamageModifier(UUID id, String name, Operation operation, double amount) {
        this.id = id;
        this.name = name;
        this.operation = operation;
        this.amount = amount;
    }

    public DamageModifier(  String name, Operation operation, double amount) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.operation = operation;
        this.amount = amount;
    }


    public CompoundTag toCompoundTag( ) {

        CompoundTag tag = new CompoundTag();

        tag.putUUID(UNIQUE_ID,id);
        tag.putString(NAME,name);
        tag.putDouble(ORIGINAL_AMOUNT,amount);
        tag.putString(StringKeys.OPERATION,operation.toString());
        return  tag;
    }

    public static DamageModifier fromNBT(CompoundTag tag) {



        return  new DamageModifier(tag.getUUID(UNIQUE_ID),tag.getString(NAME),Operation.valueOf(tag.getString(OPERATION)),tag.getDouble(ORIGINAL_AMOUNT)  );


    }




    public enum Operation {

        ADDITON,
        MULTIPLE_BASE,
        MULTIPLE_TOTAL


    }

}
