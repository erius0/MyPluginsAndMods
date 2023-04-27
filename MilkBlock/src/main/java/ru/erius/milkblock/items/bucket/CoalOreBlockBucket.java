package ru.erius.milkblock.items.bucket;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.erius.milkblock.items.AbstractBlockBucket;

import java.util.List;

public class CoalOreBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "coal_ore_block_bucket";
    private final static String DEFAULT_TITLE = "Coal ore block bucket";
    private final static List<PotionEffect> POTION_EFFECTS = List.of(
            new PotionEffect(PotionEffectType.NIGHT_VISION, 2400, 0),
            new PotionEffect(PotionEffectType.ABSORPTION, 2400, 0)
    );

    public CoalOreBlockBucket() {
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
