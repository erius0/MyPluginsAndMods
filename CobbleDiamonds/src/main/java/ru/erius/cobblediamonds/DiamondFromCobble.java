package ru.erius.cobblediamonds;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import ru.erius.eriuslib.EriusLib;
import ru.erius.eriuslib.Registry;
import ru.erius.eriuslib.quests.CustomQuest;
import ru.erius.eriuslib.recipes.shaped.CustomShapedRecipe;

import java.util.Map;

public class DiamondFromCobble extends CustomShapedRecipe {

    private final static String NAME = "diamond_from_cobblestone";
    private final static NamespacedKey KEY = new NamespacedKey(EriusLib.getInstance(), NAME);
    private final static ItemStack RESULT = new ItemStack(Material.DIAMOND);
    private final static String[] SHAPE = {"AAA", "AAA", "AAA"};
    private final static Map<Character, RecipeChoice> MAP = Map.of('A', new RecipeChoice.MaterialChoice(Material.COBBLESTONE));
    private final static CustomQuest QUEST = Registry.QuestsRegistry.getQuests().get("kill_evil_walrus");

    public DiamondFromCobble() {
        super(KEY, RESULT, SHAPE, MAP, QUEST);
    }

    @EventHandler
    private void onCraft(CraftItemEvent evt) {
        Player player = (Player) evt.getWhoClicked();
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 0.5F);
    }
}
