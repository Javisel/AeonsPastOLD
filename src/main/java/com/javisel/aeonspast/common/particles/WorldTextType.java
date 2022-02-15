package com.javisel.aeonspast.common.particles;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;

public class WorldTextType extends ParticleType  {





    public WorldTextType(  ) {
        super(true, WorldTextOptions.DESERIALIZER);

    }

    @Override
    public Codec<WorldTextOptions> codec() {


        return  WorldTextOptions.codec();

    }









}
