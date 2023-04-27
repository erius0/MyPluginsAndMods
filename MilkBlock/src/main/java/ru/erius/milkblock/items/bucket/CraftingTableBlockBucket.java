package ru.erius.milkblock.items.bucket;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import ru.erius.milkblock.items.AbstractBlockBucket;

public class CraftingTableBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "crafting_table_block_bucket";
    private final static String DEFAULT_TITLE = "Crafting table block bucket";

    public CraftingTableBlockBucket() {
        super(NAME, DEFAULT_TITLE);
    }

    @Override @EventHandler
    public void onConsume(PlayerItemConsumeEvent evt) {
        if (!this.isThisItem(evt.getItem())) return;
        replaceBucket(evt);
        evt.getPlayer().openWorkbench(null, true);
    }
}
