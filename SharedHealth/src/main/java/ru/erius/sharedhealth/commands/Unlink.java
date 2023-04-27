package ru.erius.sharedhealth.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import ru.erius.eriuslib.commands.MyCommand;
import ru.erius.sharedhealth.PlayersHealthPool;

import java.util.List;

public class Unlink extends MyCommand {

    private final static String NAME = "unlink";

    public Unlink() {
        super(NAME);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        PlayersHealthPool.getAllLinkedPlayers().clear();
        PlayersHealthPool.getAllInstances().forEach(HandlerList::unregisterAll);
        Bukkit.getLogger().info(sender.getName() + " executed the /" + NAME + " command");
        sender.sendMessage(ChatColor.GREEN + "Successfully unlinked all players");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
