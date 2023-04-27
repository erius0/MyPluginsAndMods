package ru.erius.milkblock.items.bucket;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import ru.erius.milkblock.MilkBlock;
import ru.erius.milkblock.items.AbstractBlockBucket;

public class BedrockBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "bedrock_block_bucket";
    private final static String DEFAULT_TITLE = "Bedrock block bucket";

    public BedrockBlockBucket() {
        super(NAME, DEFAULT_TITLE);
    }

    @Override
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent evt) {
        if (!this.isThisItem(evt.getItem())) return;
        replaceBucket(evt);
        World end = Bukkit.getWorlds().get(2);
        if (evt.getPlayer().getWorld().equals(end)) {
            evt.getPlayer().getWorld().playSound(evt.getPlayer().getEyeLocation(), Sound.ENTITY_BLAZE_DEATH, 1.0F, 0.5F);
            return;
        }
        Location teleportTo = new Location(end, 0.0D, 72.0D, 0.0D);
        int radius = 3;
        for (int x = radius; x >= -radius; x--)
            for (int y = radius; y >= -radius; y--)
                for (int z = radius; z >= -radius; z--) {
                    Block block = end.getBlockAt(x, y, z);
                    block.setType(Material.AIR);
                }
        Location setPlatform = teleportTo.add(0.0D, -1.0D, 0.0D);
        end.getBlockAt(setPlatform).setType(Material.OBSIDIAN);
        Bukkit.getScheduler().runTaskLater(MilkBlock.getInstance(), () -> {
            evt.getPlayer().teleport(teleportTo, PlayerTeleportEvent.TeleportCause.PLUGIN);
            end.playSound(evt.getPlayer().getEyeLocation(), Sound.BLOCK_PORTAL_TRAVEL, 1.0F, 1.5F);
        }, 1L);
    }
}
