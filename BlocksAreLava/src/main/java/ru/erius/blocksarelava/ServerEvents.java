package ru.erius.blocksarelava;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class ServerEvents implements Listener {
    @EventHandler
    private void onPlayerMove(PlayerMoveEvent evt) {
        BlocksHandler.checkBlock(evt.getPlayer());
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent evt) {
        BlocksHandler.checkBlock((Player) evt.getWhoClicked());
    }
}
