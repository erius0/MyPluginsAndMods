package ru.erius.milkblock;

import org.bukkit.Keyed;
import org.bukkit.plugin.java.JavaPlugin;
import ru.erius.eriuslib.Registry;
import ru.erius.eriuslib.items.CustomItem;
import ru.erius.milkblock.items.bucket.*;
import ru.erius.milkblock.recipes.EmptyBlockBucketRecipe;

import java.util.List;

public final class MilkBlock extends JavaPlugin {

    private static MilkBlock INSTANCE;

    {
        INSTANCE = this;
    }

    private final static List<Class<? extends CustomItem>> ITEMS_INIT = List.of(
            EmptyBlockBucket.class,
            AirBlockBucket.class,
            BedrockBlockBucket.class,
            ChestBlockBucket.class,
            CoalOreBlockBucket.class,
            CraftingTableBlockBucket.class,
            DiamondOreBlockBucket.class,
            DirtBlockBucket.class,
            EndStoneBlockBucket.class,
            FurnaceBlockBucket.class,
            GrassBlockBucket.class,
            GravelBlockBucket.class,
            IronOreBlockBucket.class,
            NetherBricksBlockBucket.class,
            NetherrackBlockBucket.class,
            ObsidianBlockBucket.class,
            SandBlockBucket.class,
            StoneBlockBucket.class,
            WoodBlockBucket.class
    );
    private final static List<Class<? extends Keyed>> RECIPES_INIT = List.of(
            EmptyBlockBucketRecipe.class
    );
    private final static String RESOURCE_PACK = "https://drive.google.com/u/0/uc?id=1TE0lfOOXsVykar2atZdil5UiBYiKcgsV&export=download";

    @Override
    public void onEnable() {
        Registry.registerAll(this, ITEMS_INIT, null, null, null, RECIPES_INIT, RESOURCE_PACK);
        this.getLogger().info(this.getName() + " enabled");
    }

    @Override
    public void onDisable() {
        this.getLogger().info(this.getName() + " disabled");
    }

    public static MilkBlock getInstance() {
        return INSTANCE;
    }
}
