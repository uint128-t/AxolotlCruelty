package net.yourmom.axolotlcruelty.Items;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class AxolotlMaterial implements ToolMaterial {
    public static final AxolotlMaterial INSTANCE = new AxolotlMaterial();

    @Override
    public int getDurability() {
        return 4096;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 1.5F;
    }

    @Override
    public float getAttackDamage() {
        return -2.0F;
    }

    @Override
    public int getMiningLevel() {
        return 1;
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.AXOLOTL_BUCKET);
    }
}