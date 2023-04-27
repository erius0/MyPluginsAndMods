package ru.erius.milkblock.items.bucket;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import ru.erius.milkblock.MilkBlock;
import ru.erius.milkblock.items.AbstractBlockBucket;

public class ObsidianBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "obsidian_block_bucket";
    private final static String DEFAULT_TITLE = "Obsidian block bucket";

    public ObsidianBlockBucket() {
        super(NAME, DEFAULT_TITLE);
    }

    @Override
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent evt) {
        if (!this.isThisItem(evt.getItem())) return;
        replaceBucket(evt);
        World nether = Bukkit.getWorlds().get(1);
        if (evt.getPlayer().getWorld().equals(nether)) {
            evt.getPlayer().getWorld().playSound(evt.getPlayer().getEyeLocation(), Sound.ENTITY_BLAZE_DEATH, 1.0F, 0.5F);
            return;
        }
        Location playerLoc = evt.getPlayer().getLocation();
        Location teleportTo = new Location(nether, playerLoc.getX() / 8, 64.0D, playerLoc.getZ() / 8);
        int radius = 3;
        for (int x = radius; x >= -radius; x--)
            for (int y = radius; y >= -radius; y--)
                for (int z = radius; z >= -radius; z--) {
                    Block block = nether.getBlockAt(x, y, z);
                    block.setType(Material.AIR);
                }
        Location setPlatform = teleportTo.add(0.0D, -1.0D, 0.0D);
        nether.getBlockAt(setPlatform).setType(Material.OBSIDIAN);
        Bukkit.getScheduler().runTaskLater(MilkBlock.getInstance(), () -> {
            evt.getPlayer().teleport(teleportTo, PlayerTeleportEvent.TeleportCause.PLUGIN);
            nether.playSound(evt.getPlayer().getEyeLocation(), Sound.BLOCK_PORTAL_TRAVEL, 1.0F, 1.5F);
        }, 1L);
    }
}
