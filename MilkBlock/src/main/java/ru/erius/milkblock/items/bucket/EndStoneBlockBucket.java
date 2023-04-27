package ru.erius.milkblock.items.bucket;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import ru.erius.milkblock.MilkBlock;
import ru.erius.milkblock.items.AbstractBlockBucket;

public class EndStoneBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "end_stone_block_bucket";
    private final static String DEFAULT_TITLE = "End stone block bucket";

    public EndStoneBlockBucket() {
        super(NAME, DEFAULT_TITLE);
    }

    @Override
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent evt) {
        if (!this.isThisItem(evt.getItem())) return;
        replaceBucket(evt);
        Block block = evt.getPlayer().getTargetBlock(64);
        if (block == null) return;
        evt.getPlayer().teleport(block.getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
        Bukkit.getScheduler().runTaskLater(MilkBlock.getInstance(), () -> {
            evt.getPlayer().getWorld().playSound(block.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.5F);
            evt.getPlayer().getWorld().spawnParticle(Particle.DRAGON_BREATH, block.getLocation(), 100, 1.0D, 1.0D, 1.0D);
        }, 1L);
    }
}
