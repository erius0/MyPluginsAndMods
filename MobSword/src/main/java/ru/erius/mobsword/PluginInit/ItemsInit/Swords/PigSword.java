package ru.erius.mobsword.PluginInit.ItemsInit.Swords;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import ru.erius.mobsword.MobSword;
import ru.erius.mobsword.PluginInit.ItemsInit.Souls.PigSoul;

public class PigSword extends Sword {

    private final static int ID = 15;
    private final static String NAME = "pig_sword";
    private final static ItemStack COMPONENT = new PigSoul();
    private final static int DAMAGE = 4;

    public PigSword() {
        super(ID, NAME, COMPONENT, DAMAGE);
    }

    @Override
    public void onAirSwing(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_PIG_AMBIENT, 1F, pitch);
    }

    @Override
    public void onBlockSwing(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_PIG_AMBIENT, 1F, pitch);
    }

    @Override
    public void onAirUse(Player player) {

    }

    @Override
    public void onBlockUse(Player player, Block block) {

    }

    @Override
    public void onEntityUse(Player player, Entity entity) {
        if (player.getPassengers().size() == 0) {
            float pitch = (float) (Math.random() * 2 + 0.1);
            player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_CHICKEN_EGG, 1F, pitch);
            player.addPassenger(entity);
        } else {
            float pitch = (float) (Math.random() * 2 + 0.1);
            player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_EGG_THROW, 1F, pitch);
            Entity passenger = player.getPassengers().get(0);
            player.eject();
            Location loc = player.getEyeLocation();
            passenger.setVelocity(loc.getDirection().multiply(3));
            new BukkitRunnable() {
                @Override
                public void run() {
                    Location loc = entity.getLocation();
                    loc.setY(loc.getY() + 1);
                    entity.getWorld().spawnParticle(Particle.HEART, loc, 1);
                    if (entity.isOnGround())
                        cancel();
                }
            }.runTaskTimer(MobSword.plugin, 0, 1);
        }
    }

    @Override
    public void onDamageEntity(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_PIG_AMBIENT, 1F, pitch);
        player.getWorld().spawnParticle(Particle.CLOUD, entity.getLocation(), 100, 1, 1, 1);
    }
}
