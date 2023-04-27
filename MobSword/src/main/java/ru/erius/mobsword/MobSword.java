package ru.erius.mobsword;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import ru.erius.mobsword.PluginInit.ItemsInit.Items;

public final class MobSword extends JavaPlugin {

    public static MobSword plugin;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        ConfigHandler.reloadConfig(false);
        getServer().getPluginManager().registerEvents(new ServerEvents(), this);
        Items.initItems();
        getLogger().info(getName() + " enabled");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info(getName() + " disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CommandHandler.onCommand(sender, command, args);
        return super.onCommand(sender, command, label, args);
    }
}
