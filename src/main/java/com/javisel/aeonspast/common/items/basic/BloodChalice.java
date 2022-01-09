package com.javisel.aeonspast.common.items.basic;

import net.minecraft.world.item.Item;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BloodChalice extends Item {


    public static final String BLOOD = "blood";


    public BloodChalice() {
        super(new Properties().setNoRepair().durability(40));
    }


}
