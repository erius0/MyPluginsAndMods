package ru.erius.milkblock.items.bucket;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.erius.milkblock.items.AbstractBlockBucket;

import java.util.List;

public class DirtBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "dirt_block_bucket";
    private final static String DEFAULT_TITLE = "Dirt block bucket";
    private final static List<PotionEffect> POTION_EFFECTS = List.of(
            new PotionEffect(PotionEffectType.SLOW, 600, 1),
            new PotionEffect(PotionEffectType.POISON, 100, 2)
    );


    public DirtBlockBucket() {
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
    }
}
