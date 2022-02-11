package com.javisel.aeonspast.common.spell.weaponspell;

import com.javisel.aeonspast.common.mixin.LivingEntityMixin;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;

public class Uppercut extends WeaponSpell {
    public Uppercut() {
        super(60, 25, SpellRank.WEAPON_SPELL);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {


        if (entity.level.isClientSide) {






        } else {



        }


        if (entity instanceof Player) {


             Player player = (Player) entity;
            player.attackStrengthTicker=100;
        }



    }
}
