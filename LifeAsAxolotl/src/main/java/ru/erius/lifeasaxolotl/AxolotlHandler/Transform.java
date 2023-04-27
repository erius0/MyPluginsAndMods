package ru.erius.lifeasaxolotl.AxolotlHandler;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import ru.erius.lifeasaxolotl.ConfigHandler;
import ru.erius.lifeasaxolotl.LifeAsAxolotl;

public class Transform {

    private final static LifeAsAxolotl plugin = LifeAsAxolotl.plugin;
    private final static FileConfiguration config = ConfigHandler.getConfig();
    private static Axolotl axolotl;

    public static void onAxolotlCommand(CommandSender sender) {
        if (sender instanceof Player)
            transformPlayer((Player) sender);
        else
            plugin.getLogger().info("You are not a player!");
    }

    private static void transformPlayer(Player player) {
        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
        armorStand.setInvisible(true);
        Axolotl.Variant variant = Axolotl.Variant.valueOf(config.getConfigurationSection("axolotl").getString("variant"));
        boolean isBaby = config.getConfigurationSection("axolotl").getBoolean("is-baby");
        axolotl = (Axolotl) player.getWorld().spawnEntity(player.getLocation(), EntityType.AXOLOTL);
        axolotl.setVariant(variant);
        if (isBaby)
            axolotl.setBaby();
        axolotl.setAI(false);
        player.setInvisible(true);
    }
}
