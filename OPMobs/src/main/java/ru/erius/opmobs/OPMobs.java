package ru.erius.opmobs;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import ru.erius.opmobs.OPHandler.MobEvents;

public class OPMobs extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        getLogger().info("OPMobs enabled");
        getServer().getPluginManager().registerEvents(new ServerEvents(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info("OPMobs disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName().toLowerCase();
        if (cmd.equals("mop")) {
            if (args.length >= 1) {
                switch (args[0]) {
                    case "oneshot":
                        if (MobEvents.doMobsOneShot) {
                            sender.sendMessage(ChatColor.RED + "Mobs no longer can instantly kill you");
                            MobEvents.doMobsOneShot = false;
                        } else {
                            sender.sendMessage(ChatColor.GREEN + "Mobs can instantly kill you now");
                            MobEvents.doMobsOneShot = true;
                        }
                        break;
                    case "immortality":
                        if (MobEvents.areMobsImmortal) {
                            sender.sendMessage(ChatColor.RED + "Mobs are no longer immortal");
                            MobEvents.areMobsImmortal = false;
                        } else {
                            sender.sendMessage(ChatColor.GREEN + "Mobs are immortal now");
                            MobEvents.areMobsImmortal = true;
                        }
                        break;
                    default:
                        sender.sendMessage(ChatColor.RED + "Incorrect argument for command");
                        break;
                }
            } else
                sender.sendMessage(ChatColor.RED + "Not enough arguments");
        }
        return super.onCommand(sender, command, label, args);
    }
}
