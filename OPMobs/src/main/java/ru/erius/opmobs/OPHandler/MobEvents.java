package ru.erius.opmobs.OPHandler;

import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class MobEvents {

    public static boolean doMobsOneShot = false;
    public static boolean areMobsImmortal = false;

    public static void mobsOneShot(EntityDamageByEntityEvent evt) {
        Entity damager = evt.getDamager();
        if (doMobsOneShot &&
                evt.getEntity() instanceof Player && !(damager instanceof Player) &&
                !(damager instanceof Projectile && ((Projectile) damager).getShooter() instanceof Player ||
                        damager instanceof FallingBlock || damager instanceof TNTPrimed || damager instanceof EnderCrystal)) {
            evt.setDamage(1000F);
        }
    }


    public static void dealZeroDamageToMob(EntityDamageEvent evt) {
        if (areMobsImmortal && evt.getEntity() instanceof Mob)
            evt.setDamage(0);
    }


    public static void onCreeperExplosion(EntityExplodeEvent evt) {
        if (areMobsImmortal)
            evt.getEntity().getWorld().spawnEntity(evt.getLocation(), evt.getEntityType());
    }
}
