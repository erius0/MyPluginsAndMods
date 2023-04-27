package ru.erius.sharedhealth.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.erius.eriuslib.commands.MyCommand;
import ru.erius.sharedhealth.PlayersHealthPool;
import ru.erius.sharedhealth.SharedHealth;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Link extends MyCommand {

    private final static String NAME = "link";

    public Link() {
        super(NAME);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length >= 2) {
            List<Player> players = Arrays.stream(args)
                    .map(Bukkit::getPlayer)
                    .distinct()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            if (players.size() < 2)
                return false;
            Bukkit.getServer().getPluginManager().registerEvents(new PlayersHealthPool(players), SharedHealth.getInstance());
            Bukkit.getLogger().info(sender.getName() + " executed the /" + NAME + " command");
            sender.sendMessage(ChatColor.GREEN + "Successfully linked the players");
            return true;
        } else
            return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
