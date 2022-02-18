package com.javisel.aeonspast.common.registration;

import com.javisel.aeonspast.AeonsPast;
import com.javisel.aeonspast.common.effects.damageeffects.Bleed;
import com.javisel.aeonspast.common.effects.BrutalCooldown;
import com.javisel.aeonspast.common.effects.TrueStrikeSpellBuff;
import com.javisel.aeonspast.common.effects.UppercutSpellBuff;
import com.javisel.aeonspast.common.effects.damageeffects.Perforate;
import com.javisel.aeonspast.common.effects.damageeffects.Stagger;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectRegistration {

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, AeonsPast.MODID);


    public static final RegistryObject<MobEffect> UPPERCUT_SPELL_BUFF = EFFECTS.register("uppercut_spell_buff", () -> new UppercutSpellBuff());
    public static final RegistryObject<MobEffect> TRUE_STRIKE_SPELL_BUFF = EFFECTS.register("true_strike_spell_buff", () -> new TrueStrikeSpellBuff());
    public static final RegistryObject<MobEffect> BRUTAL_COOLDOWN = EFFECTS.register("brutal_cooldown", () -> new BrutalCooldown());


    public static final RegistryObject<MobEffect> BLEED = EFFECTS.register("bleed", () -> new Bleed());
    public static final RegistryObject<MobEffect> PERFORATE = EFFECTS.register("perforated", () -> new Perforate());
    public static final RegistryObject<MobEffect> STAGGER = EFFECTS.register("stagger", () -> new Stagger());







}
