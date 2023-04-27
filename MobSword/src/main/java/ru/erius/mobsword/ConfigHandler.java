package ru.erius.mobsword;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigHandler {

    private final static MobSword plugin = MobSword.plugin;

    private final static String fileName = "mob_sword.yml";
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
