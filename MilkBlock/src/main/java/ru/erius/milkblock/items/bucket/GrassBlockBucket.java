package ru.erius.milkblock.items.bucket;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.erius.milkblock.MilkBlock;
import ru.erius.milkblock.items.AbstractBlockBucket;

import java.util.List;

public class GrassBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "grass_block_bucket";
    private final static String DEFAULT_TITLE = "Grass block bucket";
    private final static List<PotionEffect> POTION_EFFECTS = List.of(
            new PotionEffect(PotionEffectType.SPEED, 1200, 2),
            new PotionEffect(PotionEffectType.REGENERATION, 100, 1)
    );

    public GrassBlockBucket() {
        super(NAME, DEFAULT_TITLE);
    }

    @Override @EventHandler
    public void onConsume(PlayerItemConsumeEvent evt) {
        if (!this.isThisItem(evt.getItem())) return;
        replaceBucket(evt);
        POTION_EFFECTS.forEach(potionEffect -> {
            if (potionEffect.equals(evt.getPlayer().getPotionEffect(potionEffect.getType())))
                evt.getPlayer().removePotionEffect(potionEffect.getType());
            evt.getPlayer().addPotionEffect(potionEffect);
        });
        World over = Bukkit.getWorlds().get(0);
        if (evt.getPlayer().getWorld().equals(over)) {
            final long[] t = {0};
            Bukkit.getScheduler().runTaskTimer(MilkBlock.getInstance(), bukkitTask -> {
                t[0]++;
                Block block = evt.getPlayer().getWorld().getBlockAt(evt.getPlayer().getLocation().add(0.0D, -1.0D, 0.0D));
                block.applyBoneMeal(BlockFace.UP);
                if (t[0] > 100)
                    bukkitTask.cancel();
            }, 0L, 1L);
        } else {
            Location playerLoc = evt.getPlayer().getLocation();
            Location teleportTo = new Location(over, playerLoc.getX() * 8, 128.0D, playerLoc.getZ() * 8);
            int radius = 3;
            for (int x = radius; x >= -radius; x--)
                for (int y = radius; y >= -radius; y--)
                    for (int z = radius; z >= -radius; z--) {
                        Block block = over.getBlockAt(x, y, z);
                        block.setType(Material.AIR);
                    }
            Location setPlatform = teleportTo.add(0.0D, -1.0D, 0.0D);
            over.getBlockAt(setPlatform).setType(Material.OBSIDIAN);
            Bukkit.getScheduler().runTaskLater(MilkBlock.getInstance(), () -> {
                evt.getPlayer().teleport(teleportTo, PlayerTeleportEvent.TeleportCause.PLUGIN);
                over.playSound(evt.getPlayer().getEyeLocation(), Sound.BLOCK_PORTAL_TRAVEL, 1.0F, 1.5F);
            }, 1L);
        }
    }
}
