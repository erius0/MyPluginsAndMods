package ru.erius.milkblock.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import ru.erius.eriuslib.Registry;
import ru.erius.eriuslib.items.CustomItem;
import ru.erius.milkblock.items.bucket.*;

import java.util.Map;

public class Buckets {

    private final static Map<Material, CustomItem> BLOCK_BUCKET = Map.ofEntries(
            Map.entry(Material.BEDROCK, Registry.ItemsRegistry.getCustomItem(BedrockBlockBucket.NAME)),
            Map.entry(Material.CHEST, Registry.ItemsRegistry.getCustomItem(ChestBlockBucket.NAME)),
            Map.entry(Material.COAL_ORE, Registry.ItemsRegistry.getCustomItem(CoalOreBlockBucket.NAME)),
            Map.entry(Material.DEEPSLATE_COAL_ORE, Registry.ItemsRegistry.getCustomItem(CoalOreBlockBucket.NAME)),
            Map.entry(Material.CRAFTING_TABLE, Registry.ItemsRegistry.getCustomItem(CraftingTableBlockBucket.NAME)),
            Map.entry(Material.DIAMOND_ORE, Registry.ItemsRegistry.getCustomItem(DiamondOreBlockBucket.NAME)),
            Map.entry(Material.DEEPSLATE_DIAMOND_ORE, Registry.ItemsRegistry.getCustomItem(DiamondOreBlockBucket.NAME)),
            Map.entry(Material.DIRT, Registry.ItemsRegistry.getCustomItem(DirtBlockBucket.NAME)),
            Map.entry(Material.END_STONE, Registry.ItemsRegistry.getCustomItem(EndStoneBlockBucket.NAME)),
            Map.entry(Material.FURNACE, Registry.ItemsRegistry.getCustomItem(FurnaceBlockBucket.NAME)),
            Map.entry(Material.GRASS_BLOCK, Registry.ItemsRegistry.getCustomItem(GrassBlockBucket.NAME)),
            Map.entry(Material.GRAVEL, Registry.ItemsRegistry.getCustomItem(GravelBlockBucket.NAME)),
            Map.entry(Material.IRON_ORE, Registry.ItemsRegistry.getCustomItem(IronOreBlockBucket.NAME)),
            Map.entry(Material.DEEPSLATE_IRON_ORE, Registry.ItemsRegistry.getCustomItem(IronOreBlockBucket.NAME)),
            Map.entry(Material.NETHER_BRICKS, Registry.ItemsRegistry.getCustomItem(NetherBricksBlockBucket.NAME)),
            Map.entry(Material.NETHERRACK, Registry.ItemsRegistry.getCustomItem(NetherrackBlockBucket.NAME)),
            Map.entry(Material.OBSIDIAN, Registry.ItemsRegistry.getCustomItem(ObsidianBlockBucket.NAME)),
            Map.entry(Material.SAND, Registry.ItemsRegistry.getCustomItem(SandBlockBucket.NAME)),
            Map.entry(Material.STONE, Registry.ItemsRegistry.getCustomItem(StoneBlockBucket.NAME)),
            Map.entry(Material.OAK_LOG, Registry.ItemsRegistry.getCustomItem(WoodBlockBucket.NAME)),
            Map.entry(Material.SPRUCE_LOG, Registry.ItemsRegistry.getCustomItem(WoodBlockBucket.NAME)),
            Map.entry(Material.BIRCH_LOG, Registry.ItemsRegistry.getCustomItem(WoodBlockBucket.NAME)),
            Map.entry(Material.JUNGLE_LOG, Registry.ItemsRegistry.getCustomItem(WoodBlockBucket.NAME)),
            Map.entry(Material.ACACIA_LOG, Registry.ItemsRegistry.getCustomItem(WoodBlockBucket.NAME)),
            Map.entry(Material.DARK_OAK_LOG, Registry.ItemsRegistry.getCustomItem(WoodBlockBucket.NAME))
    );

    public static ItemStack giveBucket(Material material) {
        return BLOCK_BUCKET.containsKey(material) ? new ItemStack(BLOCK_BUCKET.get(material)) : null;
    }
}
