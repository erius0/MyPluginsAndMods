package ru.erius.armorblocks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import ru.erius.armorblocks.EquipHandle.InventoryClickHandle;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent evt) {
        InventoryClickHandle.clickHandle(evt);
    }
}
