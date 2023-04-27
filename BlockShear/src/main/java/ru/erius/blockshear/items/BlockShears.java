package ru.erius.blockshear.items;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import ru.erius.blockshear.BlockShear;
import ru.erius.eriuslib.items.CustomItem;

public class BlockShears extends CustomItem {

    private final static String NAME = "block_shears";
    private final static Material MATERIAL = Material.SHEARS;

    public BlockShears() {
        super(NAME, MATERIAL);
    }

    @Override
    public void addRecipe() {
        String[][] recipeShapes = {{"_A", "A_"}, {"A_", "_A"}};
        for (int i = 0; i < recipeShapes.length; i++) {
            NamespacedKey key = new NamespacedKey(BlockShear.plugin, NAME + i);
            ShapedRecipe newRecipe = new ShapedRecipe(key, new BlockShears());
            newRecipe.shape(recipeShapes[i]);
            newRecipe.setIngredient('A', Material.DIAMOND);
            Bukkit.addRecipe(newRecipe);
        }
    }

    @Override
    public void onConsume(PlayerItemConsumeEvent evt) {

    }

    @Override
    public void onRightClickAir(PlayerInteractEvent evt) {

    }

    @Override
    public void onRightClickBlock(PlayerInteractEvent evt) {
        Block block = evt.getClickedBlock();
        Player player = evt.getPlayer();
        if (block == null)
            return;
        switch (block.getType()) {
            case DEEPSLATE_COAL_ORE:
                ItemStack[] possibleDrops1 = {new ItemStack(Material.COAL), new InfiniteChicken(), new KnockbackSlime(),
                        new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.TORCH), new ItemStack(Material.FURNACE),
                new ItemStack(Material.COOKED_CHICKEN), new ItemStack(Material.COOKED_BEEF), new ItemStack(Material.COOKED_PORKCHOP)};
                handleShear(possibleDrops1, player, block, Color.BLACK, Material.DEEPSLATE);
                break;
            case COAL_ORE:
                ItemStack[] possibleDrops2 = {new ItemStack(Material.COAL), new InfiniteChicken(), new KnockbackSlime(),
                        new ItemStack(Material.COAL_BLOCK), new ItemStack(Material.TORCH), new ItemStack(Material.FURNACE),
                        new ItemStack(Material.COOKED_CHICKEN), new ItemStack(Material.COOKED_BEEF), new ItemStack(Material.COOKED_PORKCHOP)};
                handleShear(possibleDrops2, player, block, Color.BLACK, Material.STONE);
                break;
            case DEEPSLATE_IRON_ORE:
                ItemStack[] possibleDrops3 = {new ItemStack(Material.RAW_IRON), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_PICKAXE),
                        new ItemStack(Material.IRON_SWORD), new ItemStack(Material.IRON_BOOTS), new ItemStack(Material.IRON_LEGGINGS),
                        new ItemStack(Material.IRON_CHESTPLATE), new ItemStack(Material.IRON_HELMET)};
                handleShear(possibleDrops3, player, block, Color.fromRGB(255, 174, 69), Material.DEEPSLATE);
                break;
            case IRON_ORE:
                ItemStack[] possibleDrops4 = {new ItemStack(Material.RAW_IRON), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_PICKAXE),
                        new ItemStack(Material.IRON_SWORD), new ItemStack(Material.IRON_BOOTS), new ItemStack(Material.IRON_LEGGINGS),
                        new ItemStack(Material.IRON_CHESTPLATE), new ItemStack(Material.IRON_HELMET), new ItemStack(Material.IRON_BLOCK)};
                handleShear(possibleDrops4, player, block, Color.fromRGB(255, 174, 69), Material.STONE);
                break;
            case DEEPSLATE_COPPER_ORE:
                ItemStack[] possibleDrops5 = {new ItemStack(Material.COPPER_ORE), new ItemStack(Material.RAW_COPPER),
                        new ItemStack(Material.COPPER_BLOCK), new InfiniteChicken(), new KnockbackSlime()};
                handleShear(possibleDrops5, player, block, Color.fromRGB(255, 124, 48), Material.DEEPSLATE);
                break;
            case COPPER_ORE:
                ItemStack[] possibleDrops6 = {new ItemStack(Material.COPPER_ORE), new ItemStack(Material.RAW_COPPER),
                        new ItemStack(Material.COPPER_BLOCK), new InfiniteChicken(), new KnockbackSlime()};
                handleShear(possibleDrops6, player, block, Color.fromRGB(255, 124, 48), Material.STONE);
                break;
            case DEEPSLATE_GOLD_ORE:
                ItemStack[] possibleDrops7 = {new ItemStack(Material.GOLDEN_APPLE), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE),
                        new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.GOLD_BLOCK),
                        new ItemStack(Material.GOLDEN_CARROT), new ItemStack(Material.GOLDEN_PICKAXE), new ItemStack(Material.GOLDEN_SWORD)};
                handleShear(possibleDrops7, player, block, Color.YELLOW, Material.DEEPSLATE);
                break;
            case GOLD_ORE:
                ItemStack[] possibleDrops8 = {new ItemStack(Material.GOLDEN_APPLE), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE),
                        new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.GOLD_BLOCK),
                        new ItemStack(Material.GOLDEN_CARROT), new ItemStack(Material.GOLDEN_PICKAXE), new ItemStack(Material.GOLDEN_SWORD)};
                handleShear(possibleDrops8, player, block, Color.YELLOW, Material.STONE);
                break;
            case DEEPSLATE_REDSTONE_ORE:
                ItemStack[] possibleDrops9 = {new ItemStack(Material.TNT), new ItemStack(Material.REDSTONE),
                        new ItemStack(Material.DROPPER), new ItemStack(Material.REDSTONE_TORCH), new ItemStack(Material.REDSTONE_BLOCK),
                };
                handleShear(possibleDrops9, player, block, Color.RED, Material.DEEPSLATE);
                break;
            case REDSTONE_ORE:
                ItemStack[] possibleDrops10 = {new ItemStack(Material.TNT), new ItemStack(Material.REDSTONE),
                        new ItemStack(Material.DROPPER), new ItemStack(Material.REDSTONE_TORCH), new ItemStack(Material.REDSTONE_BLOCK),
                };
                handleShear(possibleDrops10, player, block, Color.RED, Material.STONE);
                break;
            case DEEPSLATE_LAPIS_ORE:
                ItemStack[] possibleDrops11 = {new ItemStack(Material.LAPIS_LAZULI), new ItemStack(Material.LAPIS_BLOCK),
                        new ItemStack(Material.ENCHANTING_TABLE)};
                handleShear(possibleDrops11, player, block, Color.BLUE, Material.DEEPSLATE);
                break;
            case LAPIS_ORE:
                ItemStack[] possibleDrops12 = {new ItemStack(Material.LAPIS_LAZULI), new ItemStack(Material.LAPIS_BLOCK),
                        new ItemStack(Material.ENCHANTING_TABLE)};
                handleShear(possibleDrops12, player, block, Color.BLUE, Material.STONE);
                break;
            case DEEPSLATE_DIAMOND_ORE:
                ItemStack[] possibleDrops13 = {new ItemStack(Material.DIAMOND), new ItemStack(Material.DIAMOND_BLOCK),
                        new ItemStack(Material.DIAMOND_BOOTS), new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.DIAMOND_CHESTPLATE),
                        new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.DIAMOND_SWORD), new ItemStack(Material.DIAMOND_PICKAXE),
                        new UltraDiamondBoots(), new UltraDiamondLegs(), new UltraDiamondChest(), new UltraDiamondHelm(), new UltraDiamondSword(),
                        new DiamondApple()};
                handleShear(possibleDrops13, player, block, Color.TEAL, Material.DEEPSLATE);
                break;
            case DIAMOND_ORE:
                ItemStack[] possibleDrops14 = {new ItemStack(Material.DIAMOND), new ItemStack(Material.DIAMOND_BLOCK),
                        new ItemStack(Material.DIAMOND_BOOTS), new ItemStack(Material.DIAMOND_LEGGINGS), new ItemStack(Material.DIAMOND_CHESTPLATE),
                        new ItemStack(Material.DIAMOND_HELMET), new ItemStack(Material.DIAMOND_SWORD), new ItemStack(Material.DIAMOND_PICKAXE),
                        new UltraDiamondBoots(), new UltraDiamondLegs(), new UltraDiamondChest(), new UltraDiamondHelm(), new UltraDiamondSword(),
                        new DiamondApple()};
                handleShear(possibleDrops14, player, block, Color.TEAL, Material.STONE);
                break;
            case DEEPSLATE_EMERALD_ORE:
                ItemStack[] possibleDrops15 = {new ItemStack(Material.EMERALD), new ItemStack(Material.EMERALD_BLOCK),
                        new EmeraldApple(), new ItemStack(Material.TOTEM_OF_UNDYING)};
                handleShear(possibleDrops15, player, block, Color.GREEN, Material.DEEPSLATE);
                break;
            case EMERALD_ORE:
                ItemStack[] possibleDrops16 = {new ItemStack(Material.EMERALD), new ItemStack(Material.EMERALD_BLOCK),
                        new EmeraldApple(), new ItemStack(Material.TOTEM_OF_UNDYING)};
                handleShear(possibleDrops16, player, block, Color.GREEN, Material.STONE);
                break;
            case ANCIENT_DEBRIS:
                ItemStack[] possibleDrops17 = {new UltraNetheriteBoots(), new UltraNetheriteLegs(), new UltraNetheriteChest(),
                new UltraNetheriteHelm(), new UltraNetheriteSword(), new UltraNetheritePickaxe()};
                handleShear(possibleDrops17, player, block, Color.FUCHSIA, Material.OBSIDIAN);
                break;
            case NETHER_QUARTZ_ORE:
                ItemStack[] possibleDrops18 = {new ItemStack(Material.QUARTZ), new ItemStack(Material.QUARTZ_BLOCK)};
                handleShear(possibleDrops18, player, block, Color.WHITE, Material.NETHERRACK);
                break;
            case NETHER_GOLD_ORE:
                ItemStack[] possibleDrops19 = {new ItemStack(Material.GOLDEN_APPLE), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE),
                        new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.GOLD_NUGGET), new ItemStack(Material.GOLD_BLOCK),
                        new ItemStack(Material.GOLDEN_CARROT), new ItemStack(Material.GOLDEN_PICKAXE), new ItemStack(Material.GOLDEN_SWORD)};
                handleShear(possibleDrops19, player, block, Color.YELLOW, Material.NETHERRACK);
                break;
        }
    }

    @Override
    public void onRightClickEntity(PlayerInteractEntityEvent evt) {

    }

    @Override
    public void onLeftClickAir(PlayerInteractEvent evt) {

    }

    @Override
    public void onLeftClickBlock(PlayerInteractEvent evt) {

    }

    @Override
    public void onLeftClickEntity(EntityDamageByEntityEvent evt) {

    }

    private static void coloredParticles(Block block, Color color) {
        Location particleLoc = block.getLocation();
        particleLoc.setY(particleLoc.getY() + 0.5);
        particleLoc.setX(particleLoc.getX() + 0.5);
        particleLoc.setZ(particleLoc.getZ() + 0.5);
        block.getWorld().spawnParticle(Particle.REDSTONE, particleLoc,
                75, 0.4F, 0.4F, 0.4F, 1F, new Particle.DustOptions(color, 1F));
    }

    private static void handleShear(ItemStack[] items, Player player, Block block, Color color, Material material) {
        block.setType(material);
        coloredParticles(block, color);
        int rnd = (int) (Math.random() * items.length);
        player.getInventory().addItem(items[rnd]);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_SHEEP_SHEAR, 1F, 1F);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_ITEM_PICKUP, 1F, 1F);
    }
}
