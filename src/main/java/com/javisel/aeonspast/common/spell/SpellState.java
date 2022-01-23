package com.javisel.aeonspast.common.spell;

import java.util.Arrays;
import java.util.Comparator;

public enum SpellState {

    OFF(0),
    CHANNELING(1),
    CASTING(2),
    ON(3),
    RESOLVING(4);


    public static final SpellState[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(SpellState::getId)).toArray((p_41067_) -> {
        return new SpellState[p_41067_];
    });
    int id;

    SpellState(int id) {

        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String toString() {


        return this.toString();
    }


}
