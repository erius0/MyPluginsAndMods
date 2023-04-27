package ru.erius.blocksarelava;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.stream.Collectors;

public class BlocksHandler {

    private static FileConfiguration config = ConfigHandler.getConfig();
    private final static List<Material> blocks = config.getStringList("blocks")
            .stream()
            .map(Material::valueOf)
            .collect(Collectors.toList());
    private static boolean pluginOn = false;

    public static void checkBlock(Player player) {
        if (!pluginOn || player.getGameMode() == GameMode.CREATIVE)
            return;
        Location blockLocation = player.getLocation();
        blockLocation.setY(blockLocation.getY() - 0.1);
        Block block = player.getWorld().getBlockAt(blockLocation);
        Bukkit.getScheduler().scheduleSyncDelayedTask(BlocksAreLava.plugin, () -> {
            if (blocks.contains(block.getType()) && !checkId(player.getInventory().getBoots()))
                player.setHealth(0);
        }, 1);
    }

    public static void onCommand(CommandSender sender) {
        if (pluginOn) {
            pluginOn = false;
            sender.sendMessage(ChatColor.RED + "Blocks no longer kill players");
        } else {
            pluginOn = true;
            sender.sendMessage(ChatColor.GOLD + "Blocks can kill players now");
        }
    }

    private static boolean checkId(ItemStack item) {
        if (item != null) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                NamespacedKey key = new NamespacedKey(BlocksAreLava.plugin, "item-id");
                String itemId = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);
                return SpecialBoots.ID.equals(itemId);
            }
        }
        return false;
    }
}
