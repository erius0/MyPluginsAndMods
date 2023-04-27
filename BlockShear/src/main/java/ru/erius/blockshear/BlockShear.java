package ru.erius.blockshear;

import org.bukkit.plugin.java.JavaPlugin;
import ru.erius.blockshear.items.InitItems;

public final class BlockShear extends JavaPlugin {

    public static BlockShear plugin;

    public BlockShear() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        InitItems.initItems();
        getLogger().info(getName() + " enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info(getName() + " disabled");
    }
}
