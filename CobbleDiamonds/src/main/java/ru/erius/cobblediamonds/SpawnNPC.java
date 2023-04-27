package ru.erius.cobblediamonds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.erius.eriuslib.commands.CustomCommand;

import java.util.List;

public class SpawnNPC extends CustomCommand {

    private final static String NAME = "spawnnpc";

    public SpawnNPC() {
        super(NAME);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            EvilWalrus evilWalrus = new EvilWalrus();
            Bukkit.getPluginManager().registerEvents(evilWalrus, CobbleDiamonds.getInstance());
            evilWalrus.spawn(player.getLocation());
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
