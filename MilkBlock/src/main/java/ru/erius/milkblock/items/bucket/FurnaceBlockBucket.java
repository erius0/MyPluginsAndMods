package ru.erius.milkblock.items.bucket;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ru.erius.milkblock.items.AbstractBlockBucket;

public class FurnaceBlockBucket extends AbstractBlockBucket {

    public final static String NAME = "furnace_block_bucket";
    private final static String DEFAULT_TITLE = "Furnace block bucket";
    private final static ItemStack[] FURNACE_ITEMS = {
            new ItemStack(Material.BAKED_POTATO),
            new ItemStack(Material.COOKED_BEEF),
            new ItemStack(Material.COOKED_PORKCHOP),
            new ItemStack(Material.COOKED_MUTTON),
            new ItemStack(Material.COOKED_RABBIT),
            new ItemStack(Material.COOKED_CHICKEN),
            new ItemStack(Material.COOKED_COD),
            new ItemStack(Material.COOKED_SALMON)
    };

    public FurnaceBlockBucket() {
        super(NAME, DEFAULT_TITLE);
    }

    @Override
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent evt) {
        if (!this.isThisItem(evt.getItem())) return;
        replaceBucket(evt);
        Inventory furnace = Bukkit.createInventory(evt.getPlayer(), InventoryType.FURNACE);
        ItemStack coal = new ItemStack(Material.COAL);
        coal.setAmount(8);
        furnace.setItem(1, coal);
        ItemStack food = FURNACE_ITEMS[(int) (Math.random() * FURNACE_ITEMS.length)];
        food.setAmount((int) (Math.random() * 8 + 1));
        furnace.setItem(2, food);
        evt.getPlayer().openInventory(furnace);
    }
}
