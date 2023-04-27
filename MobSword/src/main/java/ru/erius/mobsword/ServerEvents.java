package ru.erius.mobsword;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.erius.mobsword.PluginInit.ItemsEvents;
import ru.erius.mobsword.PluginInit.ItemsInit.Swords.EnderDragonSword;

public class ServerEvents implements Listener {
    @EventHandler
    private void onEntityDeath(EntityDeathEvent evt) {
        ItemsEvents.addDrops(evt);
    }

    @EventHandler
    private void onPlayerInteract(PlayerInteractEvent evt) {
        switch (evt.getAction()) {
            case LEFT_CLICK_AIR:
                ItemsEvents.handleAirSwing(evt);
                break;
            case LEFT_CLICK_BLOCK:
                ItemsEvents.handleBlockSwing(evt);
                break;
            case RIGHT_CLICK_AIR:
                ItemsEvents.handleAirUse(evt);
                break;
            case RIGHT_CLICK_BLOCK:
                ItemsEvents.handleBlockUse(evt);
                break;
        }
    }

    @EventHandler
    private void onEntityDamageByEntity(EntityDamageByEntityEvent evt) {
        ItemsEvents.handleDamage(evt);
    }

    @EventHandler
    private void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent evt) {
        ItemsEvents.handleEntityUse(evt);
    }

    @EventHandler
    private void onPlayerItemConsume(PlayerItemConsumeEvent evt) {
        ItemsEvents.handleConsume(evt);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent evt) {
        CommandHandler.offerPack(evt.getPlayer());
        EnderDragonSword.onHolding(evt.getPlayer());
    }
}
