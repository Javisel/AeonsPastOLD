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

    //Weapon Spells - Generic
    public static final RegistryObject<Spell> TRUE_STRIKE = SPELLS.register("true_strike", () -> new TrueStrike());


    //Weapon Spells - Sword
    public static final RegistryObject<Spell> CLEAVE = SPELLS.register("cleave", () -> new Cleave());
     public static final RegistryObject<Spell> DOUBLE_SLASH = SPELLS.register("double_slash", () -> new DoubleSlash());


     //Weapon Spells - Axe
    public static final RegistryObject<Spell> CHUCK = SPELLS.register("chuck", () -> new Chuck());
    public static final RegistryObject<Spell> UPPERCUT = SPELLS.register("uppercut", () -> new Uppercut());

    //Weapon Spells - Bow
    public static final RegistryObject<Spell> VENGEFUL_ASSAULT = SPELLS.register("vengeful_assault", () -> new VengefulAssault());
    public static final RegistryObject<Spell> ARROW_STORM = SPELLS.register("arrow_storm", () -> new ArrowStorm());




    public static final RegistryObject<Spell> SWIFT_STRIKE = SPELLS.register("swift_strikes", () -> new SwiftStrikes());


}
