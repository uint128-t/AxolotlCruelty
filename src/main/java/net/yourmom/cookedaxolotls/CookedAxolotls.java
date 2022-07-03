package net.yourmom.cookedaxolotls;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CookedAxolotls implements ModInitializer {
	public static final Item COOKED_AXOLOTL = new Item(
			new FabricItemSettings().group(ItemGroup.FOOD)
					.food(new FoodComponent.Builder().hunger(3).saturationModifier(9.6F).snack().meat().statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,20*10), 1).build()));
	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("cookedaxolotls", "cooked_axolotl"), COOKED_AXOLOTL);
		FuelRegistry.INSTANCE.add(Items.AXOLOTL_BUCKET, 200);
	}
}
