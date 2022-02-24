package com.javisel.aeonspast.common.combat.damage.sources;

import com.javisel.aeonspast.common.combat.damage.instances.DamageInstance;
import net.minecraft.world.damagesource.DamageSource;

public class APDamageSource extends DamageSource {


    public DamageInstance instance;

    public APDamageSource(String p_19333_, DamageInstance instance) {
        super(p_19333_);
        this.instance = instance;

        this.bypassArmor();
    }


    public DamageInstance getInstance() {
        return instance;
    }


}
