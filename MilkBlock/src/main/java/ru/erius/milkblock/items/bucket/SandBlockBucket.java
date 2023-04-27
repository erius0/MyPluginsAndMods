package ru.erius.milkblock.items.bucket;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EquipmentSlot;
import ru.erius.milkblock.MilkBlock;
import ru.erius.milkblock.items.AbstractBlockBucket;

import java.util.HashMap;

public class SandBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "sand_block_bucket";
    private final static String DEFAULT_TITLE = "Sand block bucket";
    private final static HashMap<Player, Boolean> PLAYER_SAND = new HashMap<>();

    public SandBlockBucket() {
        super(NAME, DEFAULT_TITLE);
    }

    @Override @EventHandler
    public void onConsume(PlayerItemConsumeEvent evt) {
        if (!this.isThisItem(evt.getItem())) return;
        replaceBucket(evt);
        PLAYER_SAND.put(evt.getPlayer(), true);
        Bukkit.getScheduler().runTaskLater(MilkBlock.getInstance(), () -> PLAYER_SAND.put(evt.getPlayer(), false), 600L);
    }

    @EventHandler
    private void onPlayerRightClickAir(PlayerInteractEvent evt) {
        if (!canPlace(evt.getPlayer()) || evt.getHand() == EquipmentSlot.OFF_HAND || evt.getAction() != Action.LEFT_CLICK_AIR && evt.getAction() != Action.LEFT_CLICK_BLOCK) return;
        Block block = evt.getPlayer().getTargetBlock(64);
        if (block == null) return;
        evt.getPlayer().getWorld().spawnFallingBlock(block.getLocation().add(0.0D, 1.0D, 0.0D), Material.SAND.createBlockData());
        evt.getPlayer().getWorld().playSound(evt.getPlayer().getEyeLocation(), Sound.BLOCK_SAND_PLACE, 1.0F, 1.5F);
    }

    private boolean canPlace(Player player) {
        if (!PLAYER_SAND.containsKey(player)) PLAYER_SAND.put(player, false);
        return PLAYER_SAND.get(player);
    }
}
