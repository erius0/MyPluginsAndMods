package ru.erius.milkblock.items.bucket;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.Vector;
import ru.erius.milkblock.MilkBlock;
import ru.erius.milkblock.items.AbstractBlockBucket;

import java.util.HashMap;

public class StoneBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "stone_block_bucket";
    private final static String DEFAULT_TITLE = "Stone block bucket";
    private final static HashMap<Player, Boolean> PLAYER_BREAK = new HashMap<>();

    public StoneBlockBucket() {
        super(NAME, DEFAULT_TITLE);
    }

    @Override @EventHandler
    public void onConsume(PlayerItemConsumeEvent evt) {
        if (!this.isThisItem(evt.getItem())) return;
        replaceBucket(evt);
        PLAYER_BREAK.put(evt.getPlayer(), true);
        Bukkit.getScheduler().runTaskLater(MilkBlock.getInstance(), () -> PLAYER_BREAK.put(evt.getPlayer(), false), 600L);
    }

    @EventHandler
    private void onPlayerLeftClickBlock(PlayerInteractEvent evt) {
        if (!canBreak(evt.getPlayer()) || evt.getHand() == EquipmentSlot.OFF_HAND || evt.getAction() != Action.LEFT_CLICK_BLOCK) return;
        evt.getClickedBlock().setType(Material.AIR);
        evt.getPlayer().getWorld().spawnParticle(Particle.BLOCK_CRACK, evt.getClickedBlock().getLocation().add(0.5D, 0.5D, 0.5D),
                100, 0.3D, 0.3D, 0.3D, evt.getClickedBlock().getBlockData());
        evt.getPlayer().getWorld().playSound(evt.getClickedBlock().getLocation(), Sound.BLOCK_ANVIL_FALL, 1.0F, 1.0F);
    }

    private boolean canBreak(Player player) {
        if (!PLAYER_BREAK.containsKey(player)) PLAYER_BREAK.put(player, false);
        return PLAYER_BREAK.get(player);
    }
}
