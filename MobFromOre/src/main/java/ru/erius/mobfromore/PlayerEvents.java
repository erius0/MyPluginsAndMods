package ru.erius.mobfromore;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerEvents implements Listener {
    final static List<Material> ores = Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE,
            Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.DIAMOND_ORE, Material.EMERALD_ORE,
            Material.NETHER_GOLD_ORE, Material.NETHER_QUARTZ_ORE);

    final static List<EntityType> mobs = Stream.concat(Stream.of(EntityType.values())
            .filter(v -> v.getEntityClass() != null && Mob.class.isAssignableFrom(v.getEntityClass())), Stream.of(EntityType.ENDER_DRAGON))
            .collect(Collectors.toList());

    static float spawnRate = 0.25F;

    @EventHandler
    public void onMining(BlockBreakEvent evt) {
        Block block = evt.getBlock();
        if (ores.contains(block.getType())) {
            if (Math.random() < spawnRate) {
                int index = (int) (Math.random() * mobs.size());
                Location spawnLocation = block.getLocation();
                EntityType mob = mobs.get(index);
                Entity entity = spawnLocation.getWorld().spawnEntity(spawnLocation, mob);
                if (entity instanceof EnderDragon)
                    ((EnderDragon) entity).setPhase(EnderDragon.Phase.CIRCLING);
            }
        }
    }
}
