package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.spell.NoneSpell;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.classspell.WarriorClassSpell;
import com.javisel.aeonspast.common.spell.spells.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SpellRegistration {

    public static final DeferredRegister<Spell> SPELLS = DeferredRegister.create(Spell.class, AeonsPast.MODID);


    public static final RegistryObject<Spell> NONE = SPELLS.register("none", () -> new NoneSpell());
    public static final RegistryObject<Spell> WARRIOR_CLASS_SPELL = SPELLS.register("warrior_class_spell", () -> new WarriorClassSpell());
    public static final RegistryObject<Spell> BATTLE_FRENZY = SPELLS.register("battle_frenzy", () -> new BattleFrenzy());
    public static final RegistryObject<Spell> ASSAULTGUARD = SPELLS.register("assault_guard", () -> new AssaultGuard());
    public static final RegistryObject<Spell> WARRIOR_SHOUT = SPELLS.register("warrior_shout", () -> new WarriorsShout());
    public static final RegistryObject<Spell> STORM_STRIKE = SPELLS.register("storm_strike", () -> new StormStrike());
    public static final RegistryObject<Spell> BLADESTORM = SPELLS.register("bladestorm", () -> new Bladestorm());
    public static final RegistryObject<Spell> LIGHT_OF_GOD = SPELLS.register("light_of_god", () -> new LightOfGod());


}
