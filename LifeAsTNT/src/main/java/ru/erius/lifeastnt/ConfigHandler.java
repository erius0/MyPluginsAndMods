package ru.erius.lifeastnt;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigHandler {

    private final static LifeAsTNT plugin = LifeAsTNT.plugin;

    private final static String fileName = "tnt.yml";
    private final static File file = new File(plugin.getDataFolder(), fileName);
    private static FileConfiguration config = null;

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void reloadConfig(boolean replace) {
        plugin.saveResource(fileName, replace);
        config = YamlConfiguration.loadConfiguration(file);
        plugin.getLogger().info("loaded the config");
    }
}
