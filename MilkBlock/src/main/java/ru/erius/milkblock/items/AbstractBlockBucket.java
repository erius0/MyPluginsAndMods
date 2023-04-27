package ru.erius.milkblock.items;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import ru.erius.eriuslib.Registry;
import ru.erius.eriuslib.items.CustomItem;
import ru.erius.milkblock.items.bucket.EmptyBlockBucket;

public abstract class AbstractBlockBucket extends CustomItem {

    private final static Material MATERIAL = Material.MILK_BUCKET;

    public AbstractBlockBucket(String name, String defaultTitle) {
        super(name, MATERIAL, defaultTitle);
    }

    public abstract void onConsume(PlayerItemConsumeEvent evt);

    protected void replaceBucket(PlayerItemConsumeEvent evt) {
        evt.setItem(new ItemStack(Material.AIR));
        evt.setReplacement(new ItemStack(Registry.ItemsRegistry.getCustomItem(EmptyBlockBucket.NAME)));
        EmptyBlockBucket.startBucketCountdown(evt.getPlayer());
    }
}
