package ru.erius.armorblocks;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import ru.erius.armorblocks.EquipHandle.BlockEffectsInit;

public class ArmorBlocks extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        BlockEffectsInit.initConfig();
        getLogger().info("ArmorBlocks enabled");
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info("ArmorBlocks disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName().toLowerCase();
        switch (cmd) {
            case "abreset":
                boolean reset = BlockEffectsInit.resetConfig();
                sender.sendMessage(reset ? ChatColor.GREEN + "Config reset successful" : ChatColor.RED + "Something went wrong");
                break;
            case "abreload":
                boolean reload = BlockEffectsInit.reloadConfig();
                sender.sendMessage(reload ? ChatColor.GREEN + "Config reload successful" : ChatColor.RED + "Something went wrong");
                break;
        }
        return super.onCommand(sender, command, label, args);
    }
}
