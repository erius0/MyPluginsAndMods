package ru.erius.cobblediamonds;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ru.erius.eriuslib.quests.CustomQuest;

public class KillEvilWalrus extends CustomQuest {

    private final static String NAME = "kill_evil_walrus";
    private final static String DEFAULT_QUEST_NAME = "Kill the Evil Walrus";
    private final static String DEFAULT_DESCRIPTION = "You must kill the Evil Walrus";
    private final static String DEFAULT_TITLE = ChatColor.GREEN + "Quest completed";
    private final static String DEFAULT_SUBTITLE = ChatColor.GOLD + "New recipe unlocked";

    public KillEvilWalrus() {
        super(NAME, DEFAULT_QUEST_NAME, DEFAULT_DESCRIPTION, DEFAULT_TITLE, DEFAULT_SUBTITLE);
    }

    @EventHandler
    private void progressQuest(EntityDamageByEntityEvent evt) {
        if (!(evt.getDamager() instanceof Player player) || player.hasMetadata("NPC") || this.getCompleted(player))
            return;
        if (evt.getEntity() instanceof Player npc) {
            if (npc.getHealth() - evt.getFinalDamage() <= 0 && npc.hasMetadata("NPC")) {
                this.onCompletion(player);
                this.setCompeted(player, true);
            }
        }
    }
}
