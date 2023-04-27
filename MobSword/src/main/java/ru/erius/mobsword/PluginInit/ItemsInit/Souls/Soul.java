package ru.erius.mobsword.PluginInit.ItemsInit.Souls;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import ru.erius.mobsword.PluginInit.ItemsInit.Consumable;
import ru.erius.mobsword.PluginInit.ItemsInit.MyItem;

public abstract class Soul extends MyItem implements Consumable {

    private final EntityType entityType;
    private final static Material MATERIAL = Material.GOLDEN_APPLE;

    public Soul(int id, String name, EntityType entityType) {
        super(id, name, MATERIAL);
        this.entityType = entityType;
        this.setType(MATERIAL);
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public abstract void onConsumed(Player player);
}
