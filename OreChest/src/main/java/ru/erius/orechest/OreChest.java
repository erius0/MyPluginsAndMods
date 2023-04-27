package ru.erius.orechest;

import org.bukkit.Keyed;
import org.bukkit.plugin.java.JavaPlugin;
import ru.erius.eriuslib.Registry;
import ru.erius.eriuslib.gui.CustomGUI;
import ru.erius.eriuslib.items.CustomItem;
import ru.erius.eriuslib.quests.CustomQuest;
import ru.erius.orechest.guis.LootTables;
import ru.erius.orechest.guis.OreChestGUI;
import ru.erius.orechest.items.pickaxes.*;
import ru.erius.orechest.quests.CraftChestedCoalPickQuest;
import ru.erius.orechest.quests.CraftChestedDiamondPickQuest;
import ru.erius.orechest.quests.CraftChestedIronPickQuest;
import ru.erius.orechest.recipes.*;

import java.util.List;

public final class OreChest extends JavaPlugin {

    private static OreChest instance;

    private final static List<Class<? extends CustomItem>> ITEMS = List.of(ChestedCoalPick.class, ChestedCopperPick.class,
            ChestedDiamondPick.class, ChestedEmeraldPick.class, ChestedGoldenPick.class, ChestedIronPick.class,
            ChestedLapisPick.class, ChestedNetheritePick.class, ChestedQuartzPick.class, ChestedRedstonePick.class);
    private final static List<Class<? extends Keyed>> RECIPES = List.of(ChestedCoalPickRecipe.class, ChestedCopperPickRecipe.class,
            ChestedDiamondPickRecipe.class, ChestedEmeraldPickRecipe.class, ChestedGoldenPickRecipe.class, ChestedIronPickRecipe.class,
            ChestedLapisPickRecipe.class, ChestedNetheritePickRecipe.class, ChestedQuartzPickRecipe.class, ChestedRedstonePickRecipe.class);
    private final static List<Class<? extends CustomQuest>> QUESTS = List.of(CraftChestedCoalPickQuest.class,
            CraftChestedIronPickQuest.class, CraftChestedDiamondPickQuest.class);
    private final static List<Class<? extends CustomGUI>> GUIS = List.of(OreChestGUI.class);
    private final static String PACK = "https://drive.google.com/u/0/uc?id=1MMF-0HSbj03r4UEGwGLo3dVspA8YVwyS&export=download";

    {
        instance = this;
        ConfigHandler.createConfig();
    }

    @Override
    public void onEnable() {
        Registry.registerAll(this, ITEMS, null, GUIS, QUESTS, RECIPES, PACK);
        LootTables.readOreContents();
        getLogger().info(getName() + " enabled");
    }

    @Override
    public void onDisable() {
        LootTables.writeOreContents();
        getLogger().info(getName() + " disabled");
    }

    public static OreChest getInstance() {
        return instance;
    }
}
