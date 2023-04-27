package ru.erius.blocksarelava;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public final class BlocksAreLava extends JavaPlugin {

    public static BlocksAreLava plugin;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        getLogger().info(getName() + " enabled");
        ConfigHandler.reloadConfig(false);
        SpecialBoots.addCraftingRecipe(this);
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
        if (cmd.equals("blocks"))
            BlocksHandler.onCommand(sender);
        if (cmd.equals("giveboots"))
            SpecialBoots.onCommand(sender, args);
        if (cmd.equals("resetconfig"))
            ConfigHandler.reloadConfig(true);
        return super.onCommand(sender, command, label, args);
    }
}
