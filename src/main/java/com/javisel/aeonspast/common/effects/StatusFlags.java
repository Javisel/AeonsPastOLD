package com.javisel.aeonspast.common.effects;

import com.javisel.aeonspast.common.items.properties.ItemRarity;

import java.util.Arrays;
import java.util.Comparator;

public enum StatusFlags {


    MULTIPLICATIVE_BASE(0),
    MULTIPLICATIVE_TOTAL(1),

    ADDITIVE(2);




    int id;
    public static final StatusFlags[] STATUS_FLAGS = Arrays.stream(values()).sorted(Comparator.comparingInt(StatusFlags::getId)).toArray((p_41067_) -> {
        return new StatusFlags[p_41067_];
    });


    StatusFlags(int id) {
          this.id=id;
    }


    public int getId() {
        return id;
    }
}
