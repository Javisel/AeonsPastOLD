package com.javisel.aeonspast.common.spell.weaponspell;

import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class Uppercut extends Spell {
    public Uppercut() {
        super(60, 25, SpellRank.WEAPON_SPELL);
    }

    @Override
    public void cast(LivingEntity entity, SpellStack stack) {


        if (entity.level.isClientSide) {

            if (entity instanceof Player) {
                ClientLevel clientLevel= (ClientLevel) entity.level;


            }





        }

        entity.swing(InteractionHand.MAIN_HAND);





    }
}
