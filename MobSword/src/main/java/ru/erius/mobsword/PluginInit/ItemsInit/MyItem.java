package ru.erius.mobsword.PluginInit.ItemsInit;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.erius.mobsword.ConfigHandler;

public abstract class MyItem extends ItemStack {

    private final Material MATERIAL;
    private final String NAME;
    private final int ID;

    public MyItem(int id, String name, Material material) {
        this.ID = id;
        this.NAME = name;
        this.MATERIAL = material;
        this.setAmount(1);
        this.setType(MATERIAL);
        ItemMeta meta = this.getItemMeta();
        String displayName = ConfigHandler.getConfig().getString(NAME);
        displayName = displayName == null ? "" : ChatColor.translateAlternateColorCodes('&', displayName);
        meta.setDisplayName(displayName);
        meta.setCustomModelData(ID);
        this.setItemMeta(meta);
    }

    public Material getMaterial() {
        return MATERIAL;
    }

    public String getName() {
        return NAME;
    }

    public int getId() {
        return ID;
    }
}
