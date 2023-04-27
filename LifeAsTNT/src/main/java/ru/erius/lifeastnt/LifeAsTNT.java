package ru.erius.lifeastnt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import ru.erius.lifeastnt.TNTHandler.TransformTNT;

public final class LifeAsTNT extends JavaPlugin {

    public static LifeAsTNT plugin;

    @Override
    public void onEnable() {
        super.onEnable();
        plugin = this;
        getLogger().info(getName() + " enabled");
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
            case "transform":
                TransformTNT.onCommand(sender, args);
        }
        return super.onCommand(sender, command, label, args);
    }
}
