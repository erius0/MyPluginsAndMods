package ru.erius.milkblock.items.bucket;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import ru.erius.eriuslib.Registry;
import ru.erius.eriuslib.items.CustomItem;
import ru.erius.milkblock.MilkBlock;
import ru.erius.milkblock.items.Buckets;

import java.util.HashMap;


public class EmptyBlockBucket extends CustomItem {

    public final static String NAME = "empty_block_bucket";
    private final static Material MATERIAL = Material.BUCKET;
    private final static String DEFAULT_TITLE = "Empty block bucket";
    private final static HashMap<Player, Boolean> GET_AIR_BUCKET_CD = new HashMap<>();

    public EmptyBlockBucket() {
        super(NAME, MATERIAL, DEFAULT_TITLE);
        this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
    }

    @EventHandler
    private void onPlayerFillThis(PlayerInteractEvent evt) {
        ItemStack item = evt.getPlayer().getInventory().getItem(evt.getHand());
        if (!this.isThisItem(item)) return;
        if (evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack bucket = Buckets.giveBucket(evt.getClickedBlock().getType());
            if (bucket == null) return;
            evt.getPlayer().getInventory().getItem(evt.getHand()).setAmount(item.getAmount() - 1);
            if (evt.getPlayer().getInventory().getItem(evt.getHand()).getAmount() == 0)
                evt.getPlayer().getInventory().setItem(evt.getHand(), bucket);
            else {
                HashMap<Integer, ItemStack> notFit = evt.getPlayer().getInventory().addItem(bucket);
                notFit.values().forEach(itemStack -> evt.getPlayer().getWorld().dropItem(evt.getPlayer().getLocation(), itemStack));
            }
            evt.getPlayer().getWorld().playSound(evt.getClickedBlock().getLocation(), Sound.ENTITY_COW_MILK, 1.0F, 1.0F);
            evt.getPlayer().getWorld().spawnParticle(Particle.BLOCK_CRACK, evt.getClickedBlock().getLocation().add(0.5D, 0.5D, 0.5D),
                    100, 0.3D, 0.3D, 0.3D, evt.getClickedBlock().getBlockData());
            Bukkit.getScheduler().runTaskLater(MilkBlock.getInstance(), () -> evt.getClickedBlock().setType(Material.AIR), 1L);
        } else if (evt.getAction() == Action.RIGHT_CLICK_AIR && this.canBucketBecomeAir(evt.getPlayer())) {
            ItemStack airBucket = new ItemStack(Registry.ItemsRegistry.getCustomItem(AirBlockBucket.NAME));
            evt.getPlayer().getInventory().getItem(evt.getHand()).setAmount(item.getAmount() - 1);
            if (evt.getPlayer().getInventory().getItem(evt.getHand()).getAmount() == 0)
                evt.getPlayer().getInventory().setItem(evt.getHand(), airBucket);
            else {
                HashMap<Integer, ItemStack> notFit = evt.getPlayer().getInventory().addItem(airBucket);
                notFit.values().forEach(itemStack -> evt.getPlayer().getWorld().dropItem(evt.getPlayer().getLocation(), itemStack));
            }
            evt.getPlayer().getWorld().playSound(evt.getPlayer().getEyeLocation(), Sound.ENTITY_COW_MILK, 1.0F, 1.0F);
        }
    }

    @EventHandler
    private void onPlayerMilkCow(PlayerInteractEntityEvent evt) {
        if (this.isThisItem(evt.getPlayer().getInventory().getItem(evt.getHand())) &&
                evt.getRightClicked().getType() == EntityType.COW) evt.setCancelled(true);
    }

    @EventHandler
    private void onPlayerFillBucket(PlayerBucketFillEvent evt) {
        if (this.isThisItem(evt.getPlayer().getInventory().getItem(evt.getHand()))) evt.setCancelled(true);
    }

    private boolean canBucketBecomeAir(Player player) {
        if (!GET_AIR_BUCKET_CD.containsKey(player)) GET_AIR_BUCKET_CD.put(player, true);
        return GET_AIR_BUCKET_CD.get(player);
    }

    public static void startBucketCountdown(Player player) {
        GET_AIR_BUCKET_CD.put(player, false);
        Bukkit.getScheduler().runTaskLater(MilkBlock.getInstance(), () -> GET_AIR_BUCKET_CD.put(player, true), 30L);
    }
}
