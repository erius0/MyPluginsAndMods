package ru.erius.sharedhealth;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class PlayersHealthPool implements Listener {

    private final static List<Player> ALL_LINKED_PLAYERS = new ArrayList<>();
    private final static List<PlayersHealthPool> ALL_INSTANCES = new ArrayList<>();

    private final List<Player> players = new ArrayList<>();
    private double health = 20D;

    public PlayersHealthPool(List<Player> players) {
        for (Player player : players)
            if (ALL_LINKED_PLAYERS.contains(player))
                throw new IllegalArgumentException("Player " + player.getName() + " is already linked");
        ALL_LINKED_PLAYERS.addAll(players);
        this.players.addAll(players);
        this.players.forEach(p -> {
            p.setHealth(health);
            p.setFoodLevel(20);
        });
    }

    @EventHandler
    private void onPlayerHurt(EntityDamageEvent evt) {
        if (!(evt.getEntity() instanceof Player))
            return;
        Player player = (Player) evt.getEntity();
        if (!players.contains(player))
            return;
        health -= evt.getDamage();
        if (health <= 0) {
            players.forEach(p -> {
                if (p != player)
                    p.setHealth(0);
                Bukkit.getScheduler().runTaskLater(SharedHealth.getInstance(), () ->
                        p.spigot().respawn(), 1);
            });
            health = 20;
        } else {
            players.forEach(p -> {
                if (p != player) {
                    p.playSound(p.getEyeLocation(), Sound.ENTITY_PLAYER_HURT, 1, 1);
                    p.setHealth(health);
                }
            });
        }
    }

    @EventHandler
    private void onPlayerHeal(EntityRegainHealthEvent evt) {
        if (!(evt.getEntity() instanceof Player))
            return;
        Player player = (Player) evt.getEntity();
        if (!players.contains(player))
            return;
        health = Math.min(health + evt.getAmount(), 20);
        players.forEach(p -> {
            if (p != player)
                p.setHealth(health);
        });
    }

    @EventHandler
    private void onPlayerAbsorption(EntityPotionEffectEvent evt) {
        if (!(evt.getNewEffect() == null || evt.getCause() == EntityPotionEffectEvent.Cause.PLUGIN)) {
            PotionEffect effect = evt.getNewEffect();
            if (evt.getEntity() instanceof Player && effect.getType().equals(PotionEffectType.ABSORPTION)) {
                Player player = (Player) evt.getEntity();
                players.forEach(p -> {
                    if (p != player && !player.hasPotionEffect(PotionEffectType.ABSORPTION))
                        p.addPotionEffect(effect);
                });
            }
        }
    }

    public static List<PlayersHealthPool> getAllInstances() {
        return ALL_INSTANCES;
    }

    public static List<Player> getAllLinkedPlayers() {
        return ALL_LINKED_PLAYERS;
    }
}
