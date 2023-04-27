package ru.erius.milkblock.items.bucket;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.util.Vector;
import ru.erius.milkblock.MilkBlock;
import ru.erius.milkblock.items.AbstractBlockBucket;

public class GravelBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "gravel_block_bucket";
    private final static String DEFAULT_TITLE = "Gravel block bucket";

    public GravelBlockBucket() {
        super(NAME, DEFAULT_TITLE);
    }

    @Override
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent evt) {
        if (!this.isThisItem(evt.getItem())) return;
        replaceBucket(evt);
        final long[] t = {0};
        Bukkit.getScheduler().runTaskTimer(MilkBlock.getInstance(), bukkitTask -> {
            t[0]++;
            Vector fallUp = new Vector(0.0D, Math.min(t[0] / 10.0D, 1.0D), 0.0D);
            evt.getPlayer().getNearbyEntities(64.0D, 64.0D, 64.0D).forEach(entity -> entity.setVelocity(fallUp));
            evt.getPlayer().setVelocity(fallUp);
            if (t[0] > 30)
                bukkitTask.cancel();
        }, 0L, 1L);
    }
}
