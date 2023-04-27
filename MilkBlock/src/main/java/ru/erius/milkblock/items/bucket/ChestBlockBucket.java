package ru.erius.milkblock.items.bucket;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import ru.erius.milkblock.items.AbstractBlockBucket;

public class ChestBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "chest_block_bucket";
    private final static String DEFAULT_TITLE = "Chest block bucket";

    public ChestBlockBucket() {
        super(NAME, DEFAULT_TITLE);
    }

    @Override
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent evt) {
        if (!this.isThisItem(evt.getItem())) return;
        replaceBucket(evt);
        evt.getPlayer().openInventory(evt.getPlayer().getEnderChest());
        evt.getPlayer().getWorld().playSound(evt.getPlayer().getEyeLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1.0F, 1.5F);
    }
}
