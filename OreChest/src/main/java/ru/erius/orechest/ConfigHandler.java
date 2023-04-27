package ru.erius.orechest;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.erius.eriuslib.EriusLib;

import java.io.File;
import java.io.IOException;

public class ConfigHandler {

    private static FileConfiguration config;
    private static File configFile;
    private final static String fileName = "config.yml";

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void saveConfigs() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            OreChest.getInstance().getLogger().severe("Failed to save to config!");
            e.printStackTrace();
        }
        createConfig();
    }

    public static void createConfig() {
        configFile = new File(OreChest.getInstance().getDataFolder(), fileName);
        if (!configFile.exists()) {
            OreChest.getInstance().getLogger().info("Configuration file was not found. Creating a new one...");
            configFile.getParentFile().mkdirs();
            OreChest.getInstance().saveResource(fileName, false);
        }
        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            OreChest.getInstance().getLogger().severe("Something went wrong while loading the config");
            e.printStackTrace();
            OreChest.getInstance().getPluginLoader().disablePlugin(OreChest.getInstance());
        }
    }
}
