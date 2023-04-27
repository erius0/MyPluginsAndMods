package ru.erius.lifeasaxolotl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import ru.erius.lifeasaxolotl.AxolotlHandler.Transform;

public final class LifeAsAxolotl extends JavaPlugin {

    public static LifeAsAxolotl plugin;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        ConfigHandler.defaultConfig(false);
        getLogger().info("LifeAsAxolotl enabled");
        getServer().getPluginManager().registerEvents(new ServerEvents(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info("LifeAsAxolotl disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName().toLowerCase();
        if (cmd.equals("axolotl")) {
            Transform.onAxolotlCommand(sender);
        }
        return super.onCommand(sender, command, label, args);
    }
}
