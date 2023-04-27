package ru.erius.opmobs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import ru.erius.opmobs.OPHandler.MobEvents;

public class ServerEvents implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent evt) {
        MobEvents.dealZeroDamageToMob(evt);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent evt) {
        MobEvents.mobsOneShot(evt);
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent evt) {
        MobEvents.onCreeperExplosion(evt);
    }
}
