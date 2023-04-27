package ru.erius.milkblock.items.bucket;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import ru.erius.milkblock.items.AbstractBlockBucket;

import java.util.Arrays;

public class IronOreBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "iron_ore_block_bucket";
    private final static String DEFAULT_TITLE = "Iron ore block bucket";
    private final static ItemStack[] EQUIPMENT = {
            new ItemStack(Material.IRON_HELMET),
            new ItemStack(Material.IRON_CHESTPLATE),
            new ItemStack(Material.IRON_LEGGINGS),
            new ItemStack(Material.IRON_BOOTS)
    };

    static {
        Arrays.stream(EQUIPMENT).forEach(itemStack -> itemStack.addEnchantment(Enchantment.BINDING_CURSE, 1));
    }

    public IronOreBlockBucket() {
        super(NAME, DEFAULT_TITLE);
    }

    @Override @EventHandler
    public void onConsume(PlayerItemConsumeEvent evt) {
        if (!this.isThisItem(evt.getItem())) return;
        replaceBucket(evt);
        ItemStack equip = EQUIPMENT[(int) (Math.random() * EQUIPMENT.length)];
        evt.getPlayer().getInventory().setItem(equip.getType().getEquipmentSlot(), equip);
    }
}
