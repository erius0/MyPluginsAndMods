package ru.erius.milkblock.items.bucket;

import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import ru.erius.milkblock.items.AbstractBlockBucket;

public class AirBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "air_block_bucket";
    private final static String DEFAULT_TITLE = "Air block bucket";

    public AirBlockBucket() {
        super(NAME, DEFAULT_TITLE);
    }

    @Override @EventHandler
    public void onConsume(PlayerItemConsumeEvent evt) {
        if (!this.isThisItem(evt.getItem())) return;
        replaceBucket(evt);
        evt.getPlayer().setVelocity(evt.getPlayer().getEyeLocation().getDirection().multiply(5));
        evt.getPlayer().getWorld().playSound(evt.getPlayer().getEyeLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0F, 1.5F);
    }
}
