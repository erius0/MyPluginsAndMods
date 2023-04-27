package ru.erius.milkblock.items.bucket;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.erius.milkblock.MilkBlock;
import ru.erius.milkblock.items.AbstractBlockBucket;

import java.util.List;

public class NetherrackBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "netherrack_block_bucket";
    private final static String DEFAULT_TITLE = "Netherrack block bucket";
    private final static List<PotionEffect> POTION_EFFECTS = List.of(
            new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 600, 0)
    );

    public NetherrackBlockBucket() {
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
        final long[] t = {0};
        Bukkit.getScheduler().runTaskTimer(MilkBlock.getInstance(), bukkitTask -> {
            t[0]++;
            Location locIgnite = evt.getPlayer().getLocation();
            locIgnite.getBlock().setType(Material.FIRE);
            if (t[0] > 300)
                bukkitTask.cancel();
        }, 0L, 1L);
    }
}
