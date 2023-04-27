package ru.erius.blockshear.items;

import ru.erius.blockshear.BlockShear;
import ru.erius.eriuslib.items.CustomItem;

import java.util.Set;

public class InitItems {

    private static final Set<Class<? extends CustomItem>> ITEMS_INIT = Set.of(BlockShears.class, InfiniteChicken.class, KnockbackSlime.class,
    DiamondApple.class, EmeraldApple.class, UltraDiamondBoots.class, UltraDiamondLegs.class, UltraDiamondChest.class,
    UltraDiamondHelm.class, UltraDiamondSword.class, UltraDiamondPickaxe.class, UltraNetheriteSword.class,
    UltraNetheritePickaxe.class, UltraNetheriteBoots.class, UltraNetheriteLegs.class, UltraNetheriteChest.class,
    UltraNetheriteHelm.class);

    private final static String packURL = "https://drive.google.com/u/0/uc?id=1sAe93zBaI0hzynvcNUtJwT7oncIG4xoM&export=download";

    public static void initItems() {
        CustomItem.initItems(BlockShear.plugin, ITEMS_INIT);
        CustomItem.setPackURL(packURL);
    }
}
