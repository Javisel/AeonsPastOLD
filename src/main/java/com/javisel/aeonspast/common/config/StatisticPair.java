package com.javisel.aeonspast.common.config;

import java.util.Random;

public class StatisticPair {

    final float min;
    final float max;


    public StatisticPair(float min, float max) {

        this.min = min;
        this.max = max;

    }


    public float roll(float luck, Random random) {


         float rng = min + random.nextFloat() * (max - min);


        if (luck < 0) {


            if (random.nextInt(101) <= (luck * -1)) {

                float newroll = min + random.nextFloat() * (max - min);

                if (newroll < rng) {

                    return newroll;
                }


            }


        } else if (luck > 0) {


            if (random.nextInt(101) <= luck) {

                float newroll = min + random.nextFloat() * (max - min);

                if (newroll > rng) {

                    return newroll;
                }


            }

        }


         return rng;
    }

    public float getmin() {
        return min;
    }

    public float getMax() {
        return max;
    }


    public float getAverage() {


        return (min + max) / 2;
    }


}
