package ru.erius.milkblock.items.bucket;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import ru.erius.milkblock.MilkBlock;
import ru.erius.milkblock.items.AbstractBlockBucket;

import java.util.HashMap;
import java.util.List;

public class NetherBricksBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "nether_bricks_block_bucket";
    private final static String DEFAULT_TITLE = "Nether bricks block bucket";
    private final static List<PotionEffect> POTION_EFFECTS = List.of(
            new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1200, 0)
    );
    private final static HashMap<Player, Boolean> PLAYER_SHOOT = new HashMap<>();

    public NetherBricksBlockBucket() {
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
        PLAYER_SHOOT.put(evt.getPlayer(), true);
        Bukkit.getScheduler().runTaskLater(MilkBlock.getInstance(), () -> PLAYER_SHOOT.put(evt.getPlayer(), false), 200L);
    }

    @EventHandler
    private void onPlayerRightClickAir(PlayerInteractEvent evt) {
        if (!canShoot(evt.getPlayer()) || evt.getHand() == EquipmentSlot.OFF_HAND || evt.getAction() != Action.LEFT_CLICK_AIR && evt.getAction() != Action.LEFT_CLICK_BLOCK) return;
        Location spawnLoc = evt.getPlayer().getEyeLocation().add(evt.getPlayer().getEyeLocation().getDirection());
        Entity fireball = evt.getPlayer().getWorld().spawnEntity(spawnLoc, EntityType.FIREBALL);
        Vector fireballVelocity = evt.getPlayer().getEyeLocation().getDirection().multiply(3);
        fireball.setVelocity(fireballVelocity);
        evt.getPlayer().getWorld().playSound(evt.getPlayer().getEyeLocation(), Sound.ENTITY_BLAZE_SHOOT, 1.0F, 1.5F);
    }

    private boolean canShoot(Player player) {
        if (!PLAYER_SHOOT.containsKey(player)) PLAYER_SHOOT.put(player, false);
        return PLAYER_SHOOT.get(player);
    }
}
