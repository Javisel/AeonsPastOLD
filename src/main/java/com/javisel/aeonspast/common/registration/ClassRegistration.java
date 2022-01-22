package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.playerclasses.PlayerGameClass;
import com.javisel.aeonspast.common.playerclasses.WarriorGameClass;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ClassRegistration {

    public static final DeferredRegister<PlayerGameClass> PLAYER_GAME_CLASSES = DeferredRegister.create(PlayerGameClass.class, AeonsPast.MODID);


    public static final RegistryObject<PlayerGameClass> WARRIOR = PLAYER_GAME_CLASSES.register("warrior", () -> new WarriorGameClass());


}
