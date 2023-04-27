package ru.erius.mobsword.PluginInit.ItemsInit.Swords;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import ru.erius.mobsword.PluginInit.ItemsInit.Souls.SheepSoul;

public class SheepSword extends Sword {

    private final static int ID = 20;
    private final static String NAME = "sheep_sword";
    private final static ItemStack COMPONENT = new SheepSoul();
    private final static int DAMAGE = 4;

    public SheepSword() {
        super(ID, NAME, COMPONENT, DAMAGE);
    }

    @Override
    public void onAirSwing(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_SHEEP_AMBIENT, 1F, pitch);
    }

    @Override
    public void onBlockSwing(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_SHEEP_AMBIENT, 1F, pitch);
    }

    @Override
    public void onAirUse(Player player) {
        for (Entity e : player.getNearbyEntities(20, 20, 20)) {
            Vector entityVector = e.getLocation().getDirection(), playerVector = player.getLocation().getDirection();
            Vector push = playerVector.subtract(entityVector).normalize();
            push.multiply(3);
            push.setY(0.5);
            e.setVelocity(push);
        }
    }

    @Override
    public void onBlockUse(Player player, Block block) {
        for (Entity e : player.getNearbyEntities(20, 20, 20)) {
            Vector entityVector = e.getLocation().getDirection(), playerVector = player.getLocation().getDirection();
            Vector push = playerVector.subtract(entityVector).normalize();
            push.multiply(3);
            push.setY(0.5);
            e.setVelocity(push);
            float pitch = (float) (Math.random() * 2 + 0.1);
            player.getWorld().playSound(player.getEyeLocation(), Sound.AMBIENT_CAVE, 1F, pitch);
        }
    }

    @Override
    public void onEntityUse(Player player, Entity entity) {
        for (Entity e : player.getNearbyEntities(10, 10, 10)) {
            Vector entityVector = e.getLocation().getDirection(), playerVector = player.getLocation().getDirection();
            Vector push = playerVector.subtract(entityVector).normalize();
            push.multiply(3);
            push.setY(0.5);
            e.setVelocity(push);
        }
    }

    @Override
    public void onDamageEntity(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_SHEEP_AMBIENT, 1F, pitch);
        player.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, entity.getLocation(), 100, 1, 1, 1);
    }
}
