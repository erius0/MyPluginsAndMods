package ru.erius.mobsword.PluginInit.ItemsInit.Swords;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.erius.mobsword.PluginInit.ItemsInit.Souls.SkeletonSoul;

public class SkeletonSword extends Sword {

    private final static int ID = 13;
    private final static String NAME = "skeleton_sword";
    private final static ItemStack COMPONENT = new SkeletonSoul();
    private final static int DAMAGE = 6;

    public SkeletonSword() {
        super(ID, NAME, COMPONENT, DAMAGE);
    }

    @Override
    public void onAirSwing(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_SKELETON_AMBIENT, 1F, pitch);
    }

    @Override
    public void onBlockSwing(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_SKELETON_AMBIENT, 1F, pitch);
    }

    @Override
    public void onAirUse(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ARROW_SHOOT, 1F, pitch);
        Location loc = player.getEyeLocation();
        Entity arrow = player.getWorld().spawnEntity(loc, EntityType.ARROW);
        arrow.setVelocity(loc.getDirection().multiply(4));
    }

    @Override
    public void onBlockUse(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ARROW_SHOOT, 1F, pitch);
        Location loc = player.getEyeLocation();
        Entity arrow = player.getWorld().spawnEntity(loc, EntityType.ARROW);
        arrow.setVelocity(loc.getDirection().multiply(4));
    }

    @Override
    public void onEntityUse(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ARROW_SHOOT, 1F, pitch);
    }

    @Override
    public void onDamageEntity(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_SKELETON_AMBIENT, 1F, pitch);
        player.spawnParticle(Particle.END_ROD, entity.getLocation(), 100, 1, 1, 1);
        if (entity instanceof Mob)
            ((Mob) entity).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 1200, 0));
    }
}
