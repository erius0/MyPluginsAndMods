package ru.erius.orechest.guis;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import ru.erius.orechest.OreChest;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class LootTables {

    private final static List<Material> ORES = List.of(
            Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE, Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE,
            Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE, Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE,
            Material.GOLD_ORE, Material.DEEPSLATE_GOLD_ORE, Material.NETHER_GOLD_ORE, Material.IRON_ORE,
            Material.DEEPSLATE_IRON_ORE, Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE, Material.ANCIENT_DEBRIS,
            Material.NETHER_QUARTZ_ORE, Material.REDSTONE_ORE, Material.DEEPSLATE_REDSTONE_ORE
    );

    private final static String FILE_NAME = "ore_contents.dat";
    public static HashMap<Location, ItemStack[]> ORE_CONTENTS;

    public final static ItemProbability[] COAL_ORE_ITEMS = {
            new ItemProbability(new ItemStack(Material.AIR), 500, 1),
            new ItemProbability(new ItemStack(Material.COAL), 80, 8),
            new ItemProbability(new ItemStack(Material.OAK_PLANKS), 80, 16),
            new ItemProbability(new ItemStack(Material.TORCH), 60, 12),
            new ItemProbability(new ItemStack(Material.COOKED_CHICKEN), 40, 8),
            new ItemProbability(new ItemStack(Material.COOKED_PORKCHOP), 40, 8),
            new ItemProbability(new ItemStack(Material.COOKED_BEEF), 40, 8),
            new ItemProbability(new ItemStack(Material.OAK_LOG), 40, 8),
            new ItemProbability(new ItemStack(Material.COAL_BLOCK), 20, 2)
    };
    public final static ItemProbability[] COPPER_ORE_ITEMS = {
            new ItemProbability(new ItemStack(Material.AIR), 500, 1),
            new ItemProbability(new ItemStack(Material.RAW_COPPER), 100, 8),
            new ItemProbability(new ItemStack(Material.COPPER_INGOT), 80, 8),
            new ItemProbability(new ItemStack(Material.CHAINMAIL_HELMET), 40, 8),
            new ItemProbability(new ItemStack(Material.CHAINMAIL_CHESTPLATE), 40, 8),
            new ItemProbability(new ItemStack(Material.CHAINMAIL_LEGGINGS), 40, 8),
            new ItemProbability(new ItemStack(Material.CHAINMAIL_BOOTS), 40, 8),
            new ItemProbability(new ItemStack(Material.COPPER_BLOCK), 20, 2)
    };
    public final static ItemProbability[] DIAMOND_ORE_ITEMS = {
            new ItemProbability(new ItemStack(Material.AIR), 500, 1),
            new ItemProbability(new ItemStack(Material.DIAMOND), 80, 4),
            new ItemProbability(new ItemStack(Material.DIAMOND_PICKAXE), 20, 1),
            new ItemProbability(new ItemStack(Material.DIAMOND_SWORD), 20, 1),
            new ItemProbability(new ItemStack(Material.DIAMOND_AXE), 20, 1),
            new ItemProbability(new ItemStack(Material.DIAMOND_HOE), 40, 1),
            new ItemProbability(new ItemStack(Material.DIAMOND_SHOVEL), 40, 1),
            new ItemProbability(new ItemStack(Material.DIAMOND_HELMET), 20, 1),
            new ItemProbability(new ItemStack(Material.DIAMOND_CHESTPLATE), 20, 1),
            new ItemProbability(new ItemStack(Material.DIAMOND_LEGGINGS), 20, 1),
            new ItemProbability(new ItemStack(Material.DIAMOND_BOOTS), 20, 1),
            new ItemProbability(new ItemStack(Material.DIAMOND_HORSE_ARMOR), 40, 1),
            new ItemProbability(new ItemStack(Material.DIAMOND_BLOCK), 5, 1),
    };
    public final static ItemProbability[] EMERALD_ORE_ITEMS = {
            new ItemProbability(new ItemStack(Material.AIR), 500, 1),
            new ItemProbability(new ItemStack(Material.POPPY), 100, 8),
            new ItemProbability(new ItemStack(Material.EMERALD), 80, 4),
            new ItemProbability(new ItemStack(Material.ENDER_PEARL), 60, 4),
            new ItemProbability(new ItemStack(Material.ENDER_EYE), 50, 3),
            new ItemProbability(new ItemStack(Material.BELL), 40, 1),
            new ItemProbability(new ItemStack(Material.ZOMBIE_VILLAGER_SPAWN_EGG), 40, 3),
            new ItemProbability(new ItemStack(Material.VILLAGER_SPAWN_EGG), 20, 2),
            new ItemProbability(new ItemStack(Material.EMERALD_BLOCK), 10, 1),
            new ItemProbability(new ItemStack(Material.TRIDENT), 5, 1),
            new ItemProbability(new ItemStack(Material.TOTEM_OF_UNDYING), 5, 1)
    };
    public final static ItemProbability[] GOLD_ORE_ITEMS = {
            new ItemProbability(new ItemStack(Material.AIR), 500, 1),
            new ItemProbability(new ItemStack(Material.GOLD_NUGGET), 150, 16),
            new ItemProbability(new ItemStack(Material.RAW_GOLD), 100, 8),
            new ItemProbability(new ItemStack(Material.GOLD_INGOT), 80, 8),
            new ItemProbability(new ItemStack(Material.GOLDEN_PICKAXE), 40, 1),
            new ItemProbability(new ItemStack(Material.GOLDEN_SWORD), 40, 1),
            new ItemProbability(new ItemStack(Material.GOLDEN_AXE), 40, 1),
            new ItemProbability(new ItemStack(Material.GOLDEN_HOE), 40, 1),
            new ItemProbability(new ItemStack(Material.GOLDEN_SHOVEL), 40, 1),
            new ItemProbability(new ItemStack(Material.GOLDEN_HELMET), 30, 1),
            new ItemProbability(new ItemStack(Material.GOLDEN_CHESTPLATE), 30, 1),
            new ItemProbability(new ItemStack(Material.GOLDEN_LEGGINGS), 30, 1),
            new ItemProbability(new ItemStack(Material.GOLDEN_BOOTS), 30, 1),
            new ItemProbability(new ItemStack(Material.GOLDEN_HORSE_ARMOR), 40, 1),
            new ItemProbability(new ItemStack(Material.GOLDEN_APPLE), 20, 2),
            new ItemProbability(new ItemStack(Material.GOLD_BLOCK), 10, 2),
            new ItemProbability(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE), 5, 1)
    };
    public final static ItemProbability[] IRON_ORE_ITEMS = {
            new ItemProbability(new ItemStack(Material.AIR), 500, 1),
            new ItemProbability(new ItemStack(Material.IRON_NUGGET), 150, 16),
            new ItemProbability(new ItemStack(Material.RAW_IRON), 100, 8),
            new ItemProbability(new ItemStack(Material.IRON_INGOT), 80, 8),
            new ItemProbability(new ItemStack(Material.IRON_PICKAXE), 30, 1),
            new ItemProbability(new ItemStack(Material.IRON_SWORD), 30, 1),
            new ItemProbability(new ItemStack(Material.IRON_AXE), 30, 1),
            new ItemProbability(new ItemStack(Material.IRON_HOE), 40, 1),
            new ItemProbability(new ItemStack(Material.IRON_SHOVEL), 40, 1),
            new ItemProbability(new ItemStack(Material.IRON_HELMET), 30, 1),
            new ItemProbability(new ItemStack(Material.IRON_CHESTPLATE), 30, 1),
            new ItemProbability(new ItemStack(Material.IRON_LEGGINGS), 30, 1),
            new ItemProbability(new ItemStack(Material.IRON_BOOTS), 30, 1),
            new ItemProbability(new ItemStack(Material.IRON_HORSE_ARMOR), 40, 1),
            new ItemProbability(new ItemStack(Material.IRON_BLOCK), 10, 2),
            new ItemProbability(new ItemStack(Material.ANVIL), 5, 1)
    };
    public final static ItemProbability[] LAPIS_ORE_ITEMS = {
            new ItemProbability(new ItemStack(Material.AIR), 500, 1),
            new ItemProbability(new ItemStack(Material.LAPIS_LAZULI), 80, 16),
            new ItemProbability(new ItemStack(Material.BOOK), 80, 4),
            new ItemProbability(new ItemStack(Material.EXPERIENCE_BOTTLE), 50, 8),
            new ItemProbability(new ItemStack(Material.BOOKSHELF), 20, 2),
            new ItemProbability(new ItemStack(Material.LAPIS_BLOCK), 20, 4),
            new ItemProbability(new ItemStack(Material.ENCHANTING_TABLE), 10, 1)
    };
    public final static ItemProbability[] NETHERITE_ORE_ITEMS = {
            new ItemProbability(new ItemStack(Material.AIR), 500, 1),
            new ItemProbability(new ItemStack(Material.NETHERITE_INGOT), 80, 4),
            new ItemProbability(new ItemStack(Material.NETHERITE_PICKAXE), 10, 1),
            new ItemProbability(new ItemStack(Material.NETHERITE_SWORD), 10, 1),
            new ItemProbability(new ItemStack(Material.NETHERITE_AXE), 10, 1),
            new ItemProbability(new ItemStack(Material.NETHERITE_HOE), 30, 1),
            new ItemProbability(new ItemStack(Material.NETHERITE_SHOVEL), 30, 1),
            new ItemProbability(new ItemStack(Material.NETHERITE_HELMET), 20, 1),
            new ItemProbability(new ItemStack(Material.NETHERITE_CHESTPLATE), 20, 1),
            new ItemProbability(new ItemStack(Material.NETHERITE_LEGGINGS), 20, 1),
            new ItemProbability(new ItemStack(Material.NETHERITE_BOOTS), 20, 1),
            new ItemProbability(new ItemStack(Material.NETHERITE_BLOCK), 10, 1),
            new ItemProbability(new ItemStack(Material.SHULKER_BOX), 5, 1),
            new ItemProbability(new ItemStack(Material.ELYTRA), 5, 1),
            new ItemProbability(new ItemStack(Material.NETHER_STAR), 5, 1)
    };
    public final static ItemProbability[] QUARTZ_ORE_ITEMS = {
            new ItemProbability(new ItemStack(Material.AIR), 500, 1),
            new ItemProbability(new ItemStack(Material.QUARTZ), 100, 16),
            new ItemProbability(new ItemStack(Material.NETHER_WART), 80, 8),
            new ItemProbability(new ItemStack(Material.BLAZE_POWDER), 60, 6),
            new ItemProbability(new ItemStack(Material.BLAZE_ROD), 20, 2),
            new ItemProbability(new ItemStack(Material.CAKE), 20, 1),
            new ItemProbability(new ItemStack(Material.QUARTZ_BLOCK), 10, 4)
    };
    public final static ItemProbability[] REDSTONE_ORE_ITEMS = {
            new ItemProbability(new ItemStack(Material.AIR), 500, 1),
            new ItemProbability(new ItemStack(Material.REDSTONE), 80, 16),
            new ItemProbability(new ItemStack(Material.TNT), 60, 8),
            new ItemProbability(new ItemStack(Material.REDSTONE_TORCH), 60, 4),
            new ItemProbability(new ItemStack(Material.PISTON), 40, 4),
            new ItemProbability(new ItemStack(Material.STICKY_PISTON), 40, 4),
            new ItemProbability(new ItemStack(Material.REPEATER), 40, 2),
            new ItemProbability(new ItemStack(Material.COMPARATOR), 40, 2),
            new ItemProbability(new ItemStack(Material.SLIME_BLOCK), 20, 2),
            new ItemProbability(new ItemStack(Material.HONEY_BLOCK), 20, 2),
            new ItemProbability(new ItemStack(Material.OBSERVER), 20, 2),
            new ItemProbability(new ItemStack(Material.REDSTONE_BLOCK), 10, 4)
    };


    public record ItemProbability(ItemStack item, int weight, int maxAmount) {
    }

    public record OreInventory(Block block) implements InventoryHolder {

        @Override
        public Inventory getInventory() {
            return null;
        }

        public Block getBlock() {
            return block;
        }
    }

    public static ItemStack[] generateLoot(Block block, int slots) {
        if (ORE_CONTENTS.containsKey(block.getLocation()))
            return ORE_CONTENTS.get(block.getLocation());
        else {
            ItemProbability[] array = getArrayForBlock(block);
            ItemStack[] loot = new ItemStack[slots];
            boolean isLapis = block.getType() == Material.LAPIS_ORE || block.getType() == Material.DEEPSLATE_LAPIS_ORE;
            for (int i = 0; i < loot.length; i++)
                loot[i] = getRandomItem(array, isLapis);
            ORE_CONTENTS.put(block.getLocation(), loot);
            return loot;
        }
    }

    private static ItemStack getRandomItem(ItemProbability[] array, boolean isLapis) {
        if (isLapis)
            array = Stream.of(array,
                    new ItemProbability[]{new ItemProbability(randomEnchantedBook(), 50, 1)})
                    .flatMap(Stream::of)
                    .toArray(ItemProbability[]::new);
        int totalWeight = 0;
        for (ItemProbability element : array)
            totalWeight += element.weight;
        int position = (int) (Math.random() * totalWeight);
        ItemStack item;
        for (ItemProbability element : array) {
            if (position < element.weight) {
                item = new ItemStack(element.item);
                item.setAmount((int) (Math.random() * element.maxAmount + 1));
                return item;
            }
            position -= element.weight;
        }
        throw new IllegalStateException("Couldn't choose a random item from array, the elements weight might be broken?");
    }

    private static LootTables.ItemProbability[] getArrayForBlock(Block block) {
        return switch (block.getType()) {
            case COAL_ORE, DEEPSLATE_COAL_ORE -> COAL_ORE_ITEMS;
            case COPPER_ORE, DEEPSLATE_COPPER_ORE -> COPPER_ORE_ITEMS;
            case DIAMOND_ORE, DEEPSLATE_DIAMOND_ORE -> DIAMOND_ORE_ITEMS;
            case EMERALD_ORE, DEEPSLATE_EMERALD_ORE -> EMERALD_ORE_ITEMS;
            case GOLD_ORE, DEEPSLATE_GOLD_ORE, NETHER_GOLD_ORE -> GOLD_ORE_ITEMS;
            case IRON_ORE, DEEPSLATE_IRON_ORE -> IRON_ORE_ITEMS;
            case LAPIS_ORE, DEEPSLATE_LAPIS_ORE -> LAPIS_ORE_ITEMS;
            case ANCIENT_DEBRIS -> NETHERITE_ORE_ITEMS;
            case NETHER_QUARTZ_ORE -> QUARTZ_ORE_ITEMS;
            case REDSTONE_ORE, DEEPSLATE_REDSTONE_ORE -> REDSTONE_ORE_ITEMS;
            default -> throw new IllegalStateException("No corresponding item probability array for block type " + block.getType().name());
        };
    }

    public static void addToMap(Block block, int slots) {
        if (!ORES.contains(block.getType())) return;
        ItemStack[] items = new ItemStack[slots];
        Arrays.fill(items, new ItemStack(Material.AIR));
        ORE_CONTENTS.put(block.getLocation(), items);
    }

    @SuppressWarnings("unchecked")
    public static void readOreContents() {
        File file = new File(OreChest.getInstance().getDataFolder(), FILE_NAME);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ORE_CONTENTS = new HashMap<>();
        } else {
            try (BukkitObjectInputStream objectInputStream = new BukkitObjectInputStream(new FileInputStream(file))) {
                ORE_CONTENTS = (HashMap<Location, ItemStack[]>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                ORE_CONTENTS = new HashMap<>();
                OreChest.getInstance().getLogger().severe("Couldn't read ore contents from " + FILE_NAME);
                e.printStackTrace();
            }
            if (ORE_CONTENTS == null)
                ORE_CONTENTS = new HashMap<>();
        }
    }

    public static void writeOreContents() {
        File file = new File(OreChest.getInstance().getDataFolder(), FILE_NAME);
        if (file.exists()) {
            try (BukkitObjectOutputStream objectOutputStream = new BukkitObjectOutputStream(new FileOutputStream(file))) {
                objectOutputStream.writeObject(ORE_CONTENTS);
            } catch (IOException e) {
                OreChest.getInstance().getLogger().severe("Couldn't write ore contents to " + FILE_NAME);
                e.printStackTrace();
            }
        }
    }

    private static ItemStack randomEnchantedBook() {
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        Enchantment[] enchantments = Enchantment.values();
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) book.getItemMeta();
        Enchantment enchantment = enchantments[(int) (Math.random() * enchantments.length)];
        int level = (int) (Math.random() * enchantment.getMaxLevel() + 1);
        meta.addStoredEnchant(enchantment, level, false);
        book.setItemMeta(meta);
        return book;
    }
}
