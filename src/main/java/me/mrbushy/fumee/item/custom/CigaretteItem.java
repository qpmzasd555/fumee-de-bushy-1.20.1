package me.mrbushy.fumee.item.custom;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.Objects;

public class CigaretteItem extends Item {
    public CigaretteItem(Settings settings) {
        super(settings);
    }

    public static boolean isUsable(ItemStack stack) {
        return stack.getDamage() < stack.getMaxDamage() - 1;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (CigaretteItem.isUsable(user.getStackInHand(hand))) {
            user.setCurrentHand(hand);
            user.getItemCooldownManager().set(this, 60);
            user.incrementStat(Stats.USED.getOrCreateStat(this));

            boolean hasPlayerEffectWeakness = user.hasStatusEffect(StatusEffects.WEAKNESS);
            boolean hasPlayerEffectWither = user.hasStatusEffect(StatusEffects.WITHER);

            if (!hasPlayerEffectWeakness) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 0));
            }
            if (!hasPlayerEffectWither) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 20, 0));
                Objects.requireNonNull(user.getStatusEffect(StatusEffects.WITHER)).applyUpdateEffect(user);
            }

            user.getStackInHand(hand).damage(1, user,
                    playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));

            return TypedActionResult.consume(itemStack);
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.TOOT_HORN;
    }
}