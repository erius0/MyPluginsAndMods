package ru.erius.blocksarelava;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class SpecialBoots extends ItemStack {

    private final static Material MATERIAL = Material.NETHERITE_BOOTS;
    private final static FileConfiguration CONFIG = ConfigHandler.getConfig();
    private final static List<String> LORE = new ArrayList<>();
    private static String name;
    public final static String ID = "special_boots";

    static {
        setNameAndLore();
    }

    public SpecialBoots() {
        this.setType(MATERIAL);
        this.setAmount(1);
        ItemMeta meta = new ItemStack(MATERIAL).getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(LORE);
        NamespacedKey key = new NamespacedKey(BlocksAreLava.plugin, "item-id");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, ID);
        this.setItemMeta(meta);
        this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        this.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10);
        this.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 10);
        this.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10);
        this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
    }

    public static void addCraftingRecipe(Plugin plugin) {
        NamespacedKey key = new NamespacedKey(plugin, "special_boots");
        ShapedRecipe recipe = new ShapedRecipe(key, new SpecialBoots());
        recipe.shape(".S.", "IDG", "L.W");
        recipe.setIngredient('L', Material.LAVA_BUCKET);
        recipe.setIngredient('I', Material.IRON_BOOTS);
        recipe.setIngredient('D', Material.DIAMOND_BOOTS);
        recipe.setIngredient('G', Material.GOLDEN_BOOTS);
        recipe.setIngredient('W', Material.WATER_BUCKET);
        recipe.setIngredient('S', Material.NETHER_STAR);
        Bukkit.addRecipe(recipe);
    }

    private static void setNameAndLore() {
        String nameString = CONFIG.getString("name"), loreString = CONFIG.getString("lore");
        if (loreString != null)
            LORE.add(ChatColor.translateAlternateColorCodes('&', loreString));
        else
            LORE.add(ChatColor.RED + "Missing lore");
        if (nameString != null)
            name = ChatColor.translateAlternateColorCodes('&', nameString);
        else
            name = ChatColor.RED + "Missing name";
    }

    public static void onCommand(CommandSender sender, String[] args) {
        if (args.length < 1) {
            if (sender instanceof Player) {
                ((Player) sender).getInventory().addItem(new SpecialBoots());
                sender.sendMessage(ChatColor.GREEN + "boots");
            } else
                sender.sendMessage(ChatColor.RED + "You are not a player");
        } else {
            String name = args[0];
            Player player = BlocksAreLava.plugin.getServer().getPlayer(name);
            if (player != null) {
                player.getInventory().addItem(new SpecialBoots());
                sender.sendMessage(ChatColor.GREEN + "boots");
            } else
                sender.sendMessage(ChatColor.RED + "The player " + name + " is offline or doesn't exist");
        }
    }
}
