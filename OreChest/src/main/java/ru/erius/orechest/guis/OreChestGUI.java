package ru.erius.orechest.guis;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import ru.erius.eriuslib.gui.CustomGUI;
import ru.erius.orechest.ConfigHandler;
import ru.erius.orechest.OreChest;

public class OreChestGUI extends CustomGUI {

    private final static String NAME = "ore_chest_gui";
    private final static String DEFAULT_TITLE = "Ore";
    private final static InventoryType INVENTORY_TYPE = InventoryType.CHEST;
    private final static boolean doPlayerPlacedOresHaveLoot = ConfigHandler.getConfig().getBoolean("do_player_placed_ores_have_loot");

    public OreChestGUI() {
        super(NAME, DEFAULT_TITLE, INVENTORY_TYPE, null);
    }

    public OreChestGUI(Block block, Player player) {
        super(NAME, DEFAULT_TITLE, INVENTORY_TYPE, new LootTables.OreInventory(block));
        this.getInventory().setContents(LootTables.generateLoot(block, INVENTORY_TYPE.getDefaultSize()));
        block.getWorld().playSound(block.getLocation(), Sound.BLOCK_CHEST_OPEN, 1F, 0.8F);
        player.openInventory(this.getInventory());
    }

    @EventHandler
    private void onBlockPlace(BlockPlaceEvent evt) {
        if (!doPlayerPlacedOresHaveLoot)
            LootTables.addToMap(evt.getBlock(), INVENTORY_TYPE.getDefaultSize());
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent evt) {
        InventoryView view = evt.getView();
        if (!isThisGUI(view)) return;
        InventoryHolder holder = view.getTopInventory().getHolder();
        if (!(holder instanceof LootTables.OreInventory oreInventory)) return;
        Bukkit.getScheduler().runTaskLater(OreChest.getInstance(), () ->
                LootTables.ORE_CONTENTS.put(oreInventory.getBlock().getLocation(), view.getTopInventory().getContents()), 1);
    }

    @EventHandler
    private void onInventoryClose(InventoryCloseEvent evt) {
        InventoryView view = evt.getView();
        if (!isThisGUI(view)) return;
        InventoryHolder holder = view.getTopInventory().getHolder();
        if (!(holder instanceof LootTables.OreInventory oreInventory)) return;
        evt.getPlayer().getWorld().playSound(oreInventory.getBlock().getLocation(), Sound.BLOCK_CHEST_CLOSE, 1F, 0.8F);
    }

    public static String getGUIName() {
        return NAME;
    }
}
