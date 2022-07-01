package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.*;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer {
	public static final Item CUSTOM_ITEM = new Item(
			new Item.Settings().group(ItemGroup.FOOD)
					.food(new FoodComponent.Builder().hunger(3).saturationModifier(9.6F).snack().meat().build()));
	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("cookedaxolotls", "cooked_axolotl"), CUSTOM_ITEM);
		FuelRegistry.INSTANCE.add(Items.AXOLOTL_BUCKET, 200);
	}
}
