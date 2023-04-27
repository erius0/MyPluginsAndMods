package ru.erius.orechest.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.RecipeChoice;
import ru.erius.eriuslib.Registry;
import ru.erius.eriuslib.items.CustomItem;
import ru.erius.eriuslib.quests.CustomQuest;
import ru.erius.eriuslib.recipes.shaped.CustomShapedRecipe;
import ru.erius.orechest.OreChest;
import ru.erius.orechest.quests.CraftChestedCoalPickQuest;

import java.util.Map;

public class ChestedCopperPickRecipe extends CustomShapedRecipe {

    private final static NamespacedKey KEY = new NamespacedKey(OreChest.getInstance(), "chested_copper_pickaxe");
    private final static CustomItem RESULT = Registry.ItemsRegistry.getCustomItem("chested_copper_pickaxe");
    private final static String[] SHAPE = {
            "ABA",
            "CDC",
            "_D_"
    };
    private final static Map<Character, RecipeChoice> MAP = Map.of(
            'A', new RecipeChoice.MaterialChoice(Material.COPPER_BLOCK),
            'B', new RecipeChoice.MaterialChoice(Material.CHEST),
            'C', new RecipeChoice.MaterialChoice(Material.COPPER_INGOT),
            'D', new RecipeChoice.MaterialChoice(Material.STICK)
    );
    private final static CustomQuest QUEST = Registry.QuestsRegistry.getCustomQuest(CraftChestedCoalPickQuest.getQuestsName());

    public ChestedCopperPickRecipe() {
        super(KEY, RESULT, SHAPE, MAP, QUEST);
    }
}
