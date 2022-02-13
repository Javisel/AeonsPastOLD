package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.spell.NoneSpell;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.spells.basicspell.*;
import com.javisel.aeonspast.common.spell.spells.classspell.WarriorClassSpell;
import com.javisel.aeonspast.common.spell.spells.weaponspell.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SpellRegistration {

    public static final DeferredRegister<Spell> SPELLS = DeferredRegister.create(Spell.class, AeonsPast.MODID);


    public static final RegistryObject<Spell> NONE = SPELLS.register("none", () -> new NoneSpell());
    public static final RegistryObject<Spell> WARRIOR_CLASS_SPELL = SPELLS.register("warrior_class_spell", () -> new WarriorClassSpell());
    public static final RegistryObject<Spell> BATTLE_FRENZY = SPELLS.register("battle_frenzy", () -> new BattleFrenzy());
    public static final RegistryObject<Spell> ASSAULT_GUARD = SPELLS.register("assault_guard", () -> new BattleDance());
    public static final RegistryObject<Spell> WARRIOR_SHOUT = SPELLS.register("warrior_shout", () -> new WarriorsShout());
    public static final RegistryObject<Spell> STORM_STRIKE = SPELLS.register("storm_strike", () -> new StormStrike());
    public static final RegistryObject<Spell> BLADESTORM = SPELLS.register("bladestorm", () -> new Bladestorm());
    public static final RegistryObject<Spell> LIGHT_OF_GOD = SPELLS.register("light_of_god", () -> new LightOfGod());
    public static final RegistryObject<Spell> HOP = SPELLS.register("hop", () -> new Hop());
    public static final RegistryObject<Spell> CLEAVE = SPELLS.register("cleave", () -> new Cleave());
    public static final RegistryObject<Spell> UPPERCUT = SPELLS.register("uppercut", () -> new Uppercut());
    public static final RegistryObject<Spell> DOUBLE_STRIKE = SPELLS.register("double_strike", () -> new DoubleStrike());
    public static final RegistryObject<Spell> VENGEFUL_ASSAULT = SPELLS.register("vengeful_assault", () -> new VengefulAssault());
    public static final RegistryObject<Spell> ARROW_STORM = SPELLS.register("arrow_storm", () -> new ArrowStorm());
    public static final RegistryObject<Spell> TRUE_STRIKE = SPELLS.register("true_strike", () -> new TrueStrike());


}
