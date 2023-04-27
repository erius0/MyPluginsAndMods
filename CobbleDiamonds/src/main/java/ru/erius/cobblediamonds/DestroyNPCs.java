package ru.erius.cobblediamonds;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import ru.erius.eriuslib.commands.CustomCommand;

import java.util.List;

public class DestroyNPCs extends CustomCommand {

    private final static String NAME = "destroynpcs";

    public DestroyNPCs() {
        super(NAME);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        CitizensAPI.getNPCRegistries().forEach(NPCRegistry::deregisterAll);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
