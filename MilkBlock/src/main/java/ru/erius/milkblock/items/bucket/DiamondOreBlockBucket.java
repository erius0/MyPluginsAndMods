package ru.erius.milkblock.items.bucket;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.erius.milkblock.items.AbstractBlockBucket;

import java.util.Arrays;
import java.util.List;

public class DiamondOreBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "diamond_ore_block_bucket";
    private final static String DEFAULT_TITLE = "Diamond block bucket";
    private final static List<PotionEffect> POTION_EFFECTS = List.of(
            new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2400, 2),
            new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 2400, 1),
            new PotionEffect(PotionEffectType.ABSORPTION, 2400, 2),
            new PotionEffect(PotionEffectType.LUCK, 2400, 2),
            new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 2400, 0)
    );
    private final static ItemStack[] EQUIPMENT = {
            new ItemStack(Material.DIAMOND_HELMET),
            new ItemStack(Material.DIAMOND_CHESTPLATE),
            new ItemStack(Material.DIAMOND_LEGGINGS),
            new ItemStack(Material.DIAMOND_BOOTS)
    };

    static {
        Arrays.stream(EQUIPMENT).forEach(itemStack -> itemStack.addEnchantment(Enchantment.BINDING_CURSE, 1));
    }

    public DiamondOreBlockBucket() {
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
        ItemStack equip = EQUIPMENT[(int) (Math.random() * EQUIPMENT.length)];
        evt.getPlayer().getInventory().setItem(equip.getType().getEquipmentSlot(), equip);
    }
}
