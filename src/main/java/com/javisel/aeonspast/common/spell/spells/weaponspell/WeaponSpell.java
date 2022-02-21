package com.javisel.aeonspast.common.spell.spells.weaponspell;

import com.javisel.aeonspast.common.capabiltiies.entity.IEntityData;
import com.javisel.aeonspast.common.capabiltiies.player.IPlayerData;
import com.javisel.aeonspast.common.items.ItemEngine;
import com.javisel.aeonspast.common.spell.Spell;
import com.javisel.aeonspast.common.spell.SpellRank;
import com.javisel.aeonspast.common.spell.SpellStack;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public abstract class WeaponSpell extends Spell {
    public WeaponSpell(int defaultMaxCharges, int defaultChargeTime, int defaultCooldown, float defaultCost, SpellRank spellRank) {
        super(defaultMaxCharges, defaultChargeTime, defaultCooldown, defaultCost, spellRank);
    }

    public WeaponSpell(int defaultChargeTime, float defaultCost, SpellRank spellRank) {
        super(defaultChargeTime, defaultCost, spellRank);
    }

    public static void attackReset(LivingEntity entity) {

        entity.attackStrengthTicker = 40;

    }

    @Override
    public void onFinishCooldown(Player entity, SpellStack stack) {
        super.onFinishCooldown(entity, stack);


        deEquipWeaponSpell((Player) entity);


    }

    public void deEquipWeaponSpell(Player player) {

        if (player.level.isClientSide) {

            return;
        }
        IPlayerData playerData = Utilities.getPlayerData(player);
        IEntityData entityData = Utilities.getEntityData(player);
        ItemStack weapon = player.getMainHandItem();

        if (ItemEngine.getSpellFromItem(player,weapon) != null && ItemEngine.getSpellFromItem(player,weapon) == this) {

            return;


        } else {

            playerData.setActiveWeaponSpell(Spell.getDefaultSpell());
            playerData.removeSpellStack(this);
            if (ItemEngine.getSpellFromItem(player,weapon) != null) {
                Spell newSpell = ItemEngine.getSpellFromItem(player,weapon);


                newSpell.equipWeaponSpell(player);


            }


        }


    }

}
