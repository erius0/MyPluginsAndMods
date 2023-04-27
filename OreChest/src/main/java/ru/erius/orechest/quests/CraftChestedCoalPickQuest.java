package ru.erius.orechest.quests;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.CraftItemEvent;
import ru.erius.eriuslib.Registry;
import ru.erius.eriuslib.quests.CustomQuest;
import ru.erius.orechest.items.pickaxes.ChestedCoalPick;

public class CraftChestedCoalPickQuest extends CustomQuest {

    private final static String NAME = "craft_chested_coal_pick";
    private final static String DEFAULT_QUEST_NAME = ChatColor.GOLD + "Get a chested coal pickaxe";
    private final static String DEFAULT_DESCRIPTION = ChatColor.AQUA + "Craft a chested coal pickaxe out of coal, sticks and chest";
    private final static String DEFAULT_TITLE = ChatColor.GREEN + "Quest Completed";
    private final static String DEFAULT_SUBTITLE = ChatColor.GOLD + "New recipes unlocked";

    public CraftChestedCoalPickQuest() {
        super(NAME, DEFAULT_QUEST_NAME, DEFAULT_DESCRIPTION, DEFAULT_TITLE, DEFAULT_SUBTITLE);
    }

    @EventHandler
    private void progressQuest(CraftItemEvent evt) {
        if (Registry.ItemsRegistry.getCustomItem(ChestedCoalPick.getItemName()).isThisItem(evt.getRecipe().getResult()))
            this.onCompletion((Player) evt.getWhoClicked());
    }

    public static String getQuestsName() {
        return NAME;
    }
}
