package ru.erius.milkblock.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import ru.erius.eriuslib.recipes.shaped.CustomShapedRecipe;
import ru.erius.milkblock.MilkBlock;
import ru.erius.milkblock.items.bucket.EmptyBlockBucket;

import java.util.Map;

public class EmptyBlockBucketRecipe extends CustomShapedRecipe {

    private final static NamespacedKey KEY = new NamespacedKey(MilkBlock.getInstance(), "empty_block_bukcet_recipe");
    private final static ItemStack RESULT = new EmptyBlockBucket();
    private final static String[] SHAPE = {
            "A.A",
            ".A."
    };
    private final static Map<Character, RecipeChoice> MAP = Map.of('A', new RecipeChoice.MaterialChoice(Material.IRON_BLOCK));

    public EmptyBlockBucketRecipe() {
        super(KEY, RESULT, SHAPE, MAP);
    }
}
