package ru.erius.sharedhealth;

import org.bukkit.plugin.java.JavaPlugin;
import ru.erius.eriuslib.commands.MyCommand;
import ru.erius.sharedhealth.commands.Link;
import ru.erius.sharedhealth.commands.Unlink;

import java.util.Set;

public final class SharedHealth extends JavaPlugin {

    private static SharedHealth instance;

    private final static Set<Class<? extends MyCommand>> commands = Set.of(Link.class, Unlink.class);

    {
        instance = this;
    }

    @Override
    public void onEnable() {
        MyCommand.initCommands(this, commands);
        getLogger().info(getName() + " enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info(getName() + " disabled");
    }

    public static SharedHealth getInstance() {
        return instance;
    }
}
