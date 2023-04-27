package ru.erius.cobblediamonds;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class SpawnWalrusListener implements Listener {

    public static boolean isAlive = false;

    @EventHandler
    private void test(PlayerItemConsumeEvent evt) {
        if (evt.getItem().getType() == Material.ROTTEN_FLESH && !isAlive) {
            EvilWalrus evilWalrus = new EvilWalrus();
            Bukkit.getPluginManager().registerEvents(evilWalrus, CobbleDiamonds.getInstance());
            evilWalrus.spawn(evt.getPlayer().getLocation());
            evt.getPlayer().getWorld().playSound(evt.getPlayer().getEyeLocation(), Sound.ENTITY_WITHER_SPAWN, 1F, 0.5F);
            isAlive = true;
        }
    }
}
