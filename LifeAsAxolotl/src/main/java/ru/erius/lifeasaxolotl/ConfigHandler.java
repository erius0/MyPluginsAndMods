package ru.erius.lifeasaxolotl;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigHandler {

    private final static LifeAsAxolotl plugin = LifeAsAxolotl.plugin;

    private final static String fileName = "life-as-axolotl.yml";
    private final static File file = new File(plugin.getDataFolder(), fileName);
    private static FileConfiguration config = null;

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static void defaultConfig(boolean replace) {
        plugin.saveResource(fileName, replace);
        reloadConfig();
    }
}
