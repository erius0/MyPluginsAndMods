package ru.erius.mobfromore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class MobFromOre extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        getLogger().info("MobFromOre enabled");
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info("MobFromOre disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName().toLowerCase();
        if (cmd.equals("mobchance"))
            try {
                float newRate = Integer.parseInt(args[0]) / 100F;
                if (newRate < 0 || newRate > 1) {
                    sender.sendMessage(ChatColor.RED + "The value must be within [0, 100]");
                } else {
                    PlayerEvents.spawnRate = newRate;
                    sender.sendMessage(ChatColor.GOLD + "Changed the mob spawn rate from ores successfully");
                }
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "The value must be an integer");
            }
        return super.onCommand(sender, command, label, args);
    }
}
