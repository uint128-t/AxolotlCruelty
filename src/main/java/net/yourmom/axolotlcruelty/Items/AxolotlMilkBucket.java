package net.yourmom.axolotlcruelty.Items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import net.minecraft.server.network.ServerPlayerEntity;

public class AxolotlMilkBucket extends Item {
    public AxolotlMilkBucket(Settings settings) {
        super(settings);
    }

    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        super.finishUsing(stack, world, user);
        if (user instanceof PlayerEntity) {
            if (!world.isClient()) {
                if (((ServerPlayerEntity) user).interactionManager.getGameMode() == GameMode.CREATIVE) {
                    return stack;
                }
            }
        }
        return new ItemStack(Items.BUCKET);
    }
}