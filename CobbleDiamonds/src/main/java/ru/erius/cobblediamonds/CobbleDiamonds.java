package ru.erius.cobblediamonds;

import org.bukkit.plugin.java.JavaPlugin;
import ru.erius.eriuslib.Registry;

import java.util.List;

public final class CobbleDiamonds extends JavaPlugin {

    private static CobbleDiamonds instance;

    {
        instance = this;
    }

    @Override
    public void onEnable() {
        Registry.registerAll(this, List.of(), List.of(SpawnNPC.class, DestroyNPCs.class),
                List.of(), List.of(KillEvilWalrus.class), List.of(DiamondFromCobble.class));
        getServer().getPluginManager().registerEvents(new SpawnWalrusListener(), this);
        getLogger().info(getName() + " enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info(getName() + " disabled");
    }

    public static CobbleDiamonds getInstance() {
        return instance;
    }
}
