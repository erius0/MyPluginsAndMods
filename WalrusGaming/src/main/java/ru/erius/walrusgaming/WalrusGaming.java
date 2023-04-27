package ru.erius.walrusgaming;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class WalrusGaming extends JavaPlugin {

    public static WalrusGaming plugin;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        getLogger().info(getName() + " enabled");
        ConfigHandler.reloadConfig(false);
        getServer().getPluginManager().registerEvents(new ServerEvents(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info(getName() + " disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName().toLowerCase();
        switch (cmd) {
            case "walrusreset":
                ConfigHandler.reloadConfig(true);
                break;
            case "todo":
                MessageHandler.printMessage(sender, args);
                break;
            case "book":
                MessageHandler.giveBook(sender, args);
                break;
            case "heal":
                MessageHandler.heal(sender);
                break;
            case "feed":
                MessageHandler.feed(sender);
                break;
        }
        return super.onCommand(sender, command, label, args);
    }
}
