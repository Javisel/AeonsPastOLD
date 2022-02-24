package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.effects.Debuffs.TauntDebuff;
import com.javisel.aeonspast.common.effects.buffs.generic.AttackSpeedBuff;
import com.javisel.aeonspast.common.effects.trackers.BrutalCooldown;
import com.javisel.aeonspast.common.effects.Debuffs.*;
import com.javisel.aeonspast.common.effects.buffs.generic.PhysicalPowerBuff;
import com.javisel.aeonspast.common.effects.buffs.spell.TrueStrikeSpellBuff;
import com.javisel.aeonspast.common.effects.buffs.spell.UppercutSpellBuff;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectRegistration {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AeonsPast.MODID);


    public static final RegistryObject<MobEffect> UPPERCUT_SPELL_BUFF = EFFECTS.register("uppercut_spell_buff", () -> new UppercutSpellBuff());
    public static final RegistryObject<MobEffect> TRUE_STRIKE_SPELL_BUFF = EFFECTS.register("true_strike_spell_buff", () -> new TrueStrikeSpellBuff());
    public static final RegistryObject<MobEffect> BRUTAL_COOLDOWN = EFFECTS.register("brutal_cooldown", () -> new BrutalCooldown());



    //Damage Debuffs
    public static final RegistryObject<MobEffect> BLEED = EFFECTS.register("bleed", () -> new Bleed());
    public static final RegistryObject<MobEffect> PERFORATE = EFFECTS.register("perforate", () -> new Perforate());
    public static final RegistryObject<MobEffect> STAGGER = EFFECTS.register("stagger", () -> new Stagger());
    public static final RegistryObject<MobEffect> RADIANCE = EFFECTS.register("radiance", () -> new Radiance());
    public static final RegistryObject<MobEffect> BURN = EFFECTS.register("burn", () -> new Burn());


    public static final RegistryObject<MobEffect> ATTACK_SPEED_BUFF = EFFECTS.register("complex_attack_speed_buff", () -> new AttackSpeedBuff());
    public static final RegistryObject<MobEffect> PHYSICAL_POWER_BUFF = EFFECTS.register("complex_physical_power_buff", () -> new PhysicalPowerBuff());

    public static final RegistryObject<MobEffect> TAUNT_DEBUFF = EFFECTS.register("taunt_debuff", () -> new TauntDebuff());

}
