package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.classspell.WarriorClassSpell;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SpellRegistration {

    public static final DeferredRegister<Spell> SPELLS = DeferredRegister.create(Spell.class, AeonsPast.MODID);


    public static final RegistryObject<Spell> WARRIOR_CLASS_SPELL = SPELLS.register("warrior_class_spell", () -> new WarriorClassSpell());



}
