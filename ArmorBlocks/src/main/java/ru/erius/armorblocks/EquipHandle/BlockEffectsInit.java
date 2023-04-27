package ru.erius.armorblocks.EquipHandle;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import ru.erius.armorblocks.ArmorBlocks;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlockEffectsInit {

    private final static Plugin plugin = ArmorBlocks.getPlugin(ArmorBlocks.class);
    public static HashMap<Material, List<PotionEffectType>> blockEffects;

    public static void initConfig() {
        plugin.getLogger().info("Started config init");
        File config = new File("ArmorBlocks.cfg");
        boolean created = true;
        if (!config.exists())
            created = createConfig(config);
        if (!created)
            return;
        plugin.getLogger().info("Config init successful");
        plugin.getLogger().info("Reading the config");
        blockEffects = readConfig(config);
        plugin.getLogger().info("Config applied");
    }

    public static boolean resetConfig() {
        File config = new File("ArmorBlocks.cfg");
        config.delete();
        boolean result = createConfig(config);
        reloadConfig();
        return result;
    }

    public static boolean reloadConfig() {
        File config = new File("ArmorBlocks.cfg");
        blockEffects = readConfig(config);
        return true;
    }

    private static boolean createConfig(File file) {
        plugin.getLogger().info("Creating config");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));
             BufferedReader reader = new BufferedReader(new InputStreamReader(plugin.getResource("ru/erius/ArmorBlocks/ArmorBlocks.cfg")))) {
            while (reader.ready()) {
                String line = reader.readLine();
                writer.write(line);
                writer.newLine();
            }
            file.createNewFile();
        } catch (IOException e) {
            plugin.getLogger().severe("Something went wrong while setting up a new configuration file");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static HashMap<Material, List<PotionEffectType>> readConfig(File file) {
        HashMap<Material, List<PotionEffectType>> blockEffects = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                String[] materialPotions = reader.readLine().split("[=;]");
                if (materialPotions.length < 2)
                    continue;
                Material material = Material.getMaterial(materialPotions[0]);
                List<PotionEffectType> potions = Stream.of(materialPotions)
                        .skip(1)
                        .map(PotionEffectType::getByName)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                blockEffects.put(material, potions);
            }
        } catch (IOException e) {
            plugin.getLogger().severe("Something went wrong while reading a configuration file");
            e.printStackTrace();
        }
        return blockEffects;
    }
}
