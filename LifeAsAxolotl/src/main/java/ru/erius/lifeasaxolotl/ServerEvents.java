package ru.erius.lifeasaxolotl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ServerEvents implements Listener {
    @EventHandler
    private void test(PlayerMoveEvent evt) {
        System.out.println("a");
    }
}
