package net.yourmom.axolotlcruelty;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.yourmom.axolotlcruelty.Items.*;

public class AxolotlCruelty implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(AxolotlCruelty.class);
	public static final Item COOKED_AXOLOTL = new Item(
			new FabricItemSettings().group(ItemGroup.FOOD)
					.food(new FoodComponent.Builder().hunger(3).saturationModifier(9.6F).snack().meat()
							.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20 * 10), 1).build()));
	public static final Item AXOLOTL_MILK_BUCKET = new AxolotlMilkBucket(
			new FabricItemSettings().group(ItemGroup.FOOD)
					.food(new FoodComponent.Builder().hunger(9).saturationModifier(9.6F)
							.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20 * 40), 1).build())
					.maxCount(1).recipeRemainder(Items.BUCKET));
	public static final Item AXOLOTL_SMOOTHIE = new AxolotlSmoothie(
			new FabricItemSettings().group(ItemGroup.FOOD)
					.food(new FoodComponent.Builder().hunger(20).saturationModifier(9.6F)
							.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20 * 100), 1).build())
					.maxCount(1).recipeRemainder(Items.BUCKET));
	public static final Item AXOLOTL_BOTTLE = new AxolotlBottle(
			new FabricItemSettings().group(ItemGroup.FOOD).maxCount(1)
					.food(new FoodComponent.Builder().hunger(20).saturationModifier(9.6F)
							.statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20 * 10), 1).build())
					.recipeRemainder(Items.GLASS_BOTTLE));
	public static ToolItem AXEOLOTL = new Axeolotl(AxolotlMaterial.INSTANCE, -2, -3.2F,
			new Item.Settings().group(ItemGroup.TOOLS));

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("axolotlcruelty", "cooked_axolotl"), COOKED_AXOLOTL);
		Registry.register(Registry.ITEM, new Identifier("axolotlcruelty", "axolotl_milk_bucket"), AXOLOTL_MILK_BUCKET);
		Registry.register(Registry.ITEM, new Identifier("axolotlcruelty", "axolotl_smoothie"), AXOLOTL_SMOOTHIE);
		Registry.register(Registry.ITEM, new Identifier("axolotlcruelty", "axeolotl"), AXEOLOTL);
		Registry.register(Registry.ITEM, new Identifier("axolotlcruelty", "axolotl_bottle"), AXOLOTL_BOTTLE);
		FuelRegistry.INSTANCE.add(Items.AXOLOTL_BUCKET, 200);
		FuelRegistry.INSTANCE.add(AXOLOTL_MILK_BUCKET, 200);
		FuelRegistry.INSTANCE.add(AXOLOTL_SMOOTHIE, 200);
		FuelRegistry.INSTANCE.add(AXOLOTL_BOTTLE, 200);
		FuelRegistry.INSTANCE.add(AXEOLOTL, 200);
		CompostingChanceRegistry.INSTANCE.add(Items.AXOLOTL_BUCKET, 1F);
		UseEntityCallback.EVENT.register((PlayerEntity p, World world, Hand hand, Entity clicked, EntityHitResult ehr)->{
			if(clicked instanceof AxolotlEntity){
				AxolotlEntity axolotl = (AxolotlEntity)clicked;
				ItemStack stack = p.getStackInHand(hand);
				if(stack.getItem() == Items.MILK_BUCKET || stack.getItem() == Items.GLASS_BOTTLE){
					p.setStackInHand(hand,new ItemStack(stack.getItem() == Items.MILK_BUCKET?AxolotlCruelty.AXOLOTL_MILK_BUCKET:AxolotlCruelty.AXOLOTL_BOTTLE));
					axolotl.updatePosition(0,-1000,0); //   without death animation
					axolotl.setHealth(0); // destroy entity
				}
			}
			return ActionResult.PASS;
		});
	}
	
}

/*
 * IDEAS
 * - Axolotl fuel
 * - Cooked axolotls
 * - Axolotl in a milk bucket
 * - Axolotl in a bottle
 * - Compostable Axolotl 
 * - Axolotl Smoothie: Axolotl in a milk bucket + sugar
 * - Axe-olotl: New type of Axe
 */
