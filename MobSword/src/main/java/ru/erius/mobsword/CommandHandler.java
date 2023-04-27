package ru.erius.mobsword;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.erius.mobsword.PluginInit.ItemsInit.Items;


public class CommandHandler {

    private static final String PACK_URL = "https://drive.google.com/u/0/uc?id=1SqL--WIs1juyAvbXav_DY311X4wTO8Bo&export=download";

    public static void onCommand(CommandSender sender, Command command, String[] args) {
        String cmd = command.getName().toLowerCase();
        switch (cmd) {
            case "give":
                giveItem(sender, args);
                break;
            case "pack":
                offerPack(sender);
                break;
            case "hearts":
                removeHearts(sender);
                break;
        }
    }

    public static void removeHearts(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            player.sendMessage(ChatColor.GREEN + "Resetting health...");
        } else
            sender.sendMessage(ChatColor.RED + "You are not a player");
    }

    public static void offerPack(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            try {
                player.setResourcePack(PACK_URL);
            } catch (IllegalArgumentException e) {
                player.sendMessage(ChatColor.RED + "Failed ro load the resource pack");
                return;
            }
            player.sendMessage(ChatColor.GREEN + "Downloading...");
        } else
            sender.sendMessage(ChatColor.RED + "You are not a player");
    }

    private static void giveItem(CommandSender sender, String[] args) {
        if (args.length != 3) {
            sender.sendMessage(ChatColor.RED + "Wrong usage of command");
            return;
        }
        String name = args[0], idStr = args[1], amountStr = args[2];
        int id, amount;
        try {
            id = Integer.parseInt(idStr);
            amount = Integer.parseInt(amountStr);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "The ID and amount must be integer");
            return;
        }
        Player player = Bukkit.getPlayer(name);
        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Such player is offline or doesn't exist");
            return;
        }
        ItemStack item = Items.getItem(id, amount);
        if (item == null) {
            sender.sendMessage(ChatColor.RED + "No such item exists");
            return;
        }
        player.getInventory().addItem(item);
        sender.sendMessage(ChatColor.GREEN + "Gave " + amount + " [" + item.getItemMeta().getDisplayName() +  ChatColor.GREEN + "] to " + name);
    }
}
