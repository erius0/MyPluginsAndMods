package ru.erius.mobsword.PluginInit.ItemsInit.Swords;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import ru.erius.mobsword.PluginInit.ItemsInit.Souls.ChickenSoul;

public class ChickenSword extends Sword {
    private final static int ID = 18;
    private final static String NAME = "chicken_sword";
    private final static ItemStack COMPONENT = new ChickenSoul();
    private final static int DAMAGE = 3;

    public ChickenSword() {
        super(ID, NAME, COMPONENT, DAMAGE);
    }

    @Override
    public void onAirSwing(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_CHICKEN_AMBIENT, 1F, pitch);
    }

    @Override
    public void onBlockSwing(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_CHICKEN_AMBIENT, 1F, pitch);
    }

    @Override
    public void onAirUse(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_PHANTOM_FLAP, 1F, pitch);
        Vector dir = player.getEyeLocation().getDirection().normalize().multiply(0.5);
        dir.setY(0.5);
        player.setVelocity(dir);
    }

    @Override
    public void onBlockUse(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_PHANTOM_FLAP, 1F, pitch);
        Vector dir = player.getEyeLocation().getDirection().normalize().multiply(0.5);
        dir.setY(0.5);
        player.setVelocity(dir);
    }

    @Override
    public void onEntityUse(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_PHANTOM_FLAP, 1F, pitch);
    }

    @Override
    public void onDamageEntity(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_CHICKEN_AMBIENT, 1F, pitch);
        if (entity instanceof Mob)
            ((Mob) entity).addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100, 0));
        player.getWorld().spawnParticle(Particle.CLOUD, entity.getLocation(), 100, 1, 1, 1);
    }
}
