package com.javisel.aeonspast.common.items.basic;


import net.minecraft.world.item.Item;


public class BottleofForce extends Item {


    public BottleofForce(Properties p_i48487_1_) {
        super(p_i48487_1_);
    }

    /*
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!world.isClientSide) {
            PotionEntity potionentity = new PotionEntity(world, player);
            potionentity.setItem(itemstack);
            potionentity.shootFromRotation(player, player.xRot, player.yRot, -20.0F, 0.5F, 1.0F);
            world.addFreshEntity(potionentity);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.abilities.instabuild) {
            itemstack.shrink(1);
        }

        return ActionResult.sidedSuccess(itemstack, world.isClientSide());
    }

     */
}
