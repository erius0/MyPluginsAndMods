package ru.erius.mobsword.PluginInit.ItemsInit.Swords;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.erius.mobsword.PluginInit.ItemsInit.Souls.BlazeSoul;

public class BlazeSword extends Sword {

    private final static int ID = 17;
    private final static String NAME = "blaze_sword";
    private final static ItemStack COMPONENT = new BlazeSoul();
    private final static int DAMAGE = 8;

    public BlazeSword() {
        super(ID, NAME, COMPONENT, DAMAGE);
        this.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 10);
    }

    @Override
    public void onAirSwing(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_BLAZE_AMBIENT, 1F, pitch);
    }

    @Override
    public void onBlockSwing(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_BLAZE_AMBIENT, 1F, pitch);
    }

    @Override
    public void onAirUse(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_BLAZE_SHOOT, 1F, pitch);
        Location loc = player.getEyeLocation();
        Entity entity = player.getWorld().spawnEntity(loc, EntityType.FIREBALL);
        entity.setVelocity(loc.getDirection().multiply(2));
    }

    @Override
    public void onBlockUse(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_BLAZE_SHOOT, 1F, pitch);
        Location loc = player.getEyeLocation();
        Entity entity = player.getWorld().spawnEntity(loc, EntityType.FIREBALL);
        entity.setVelocity(loc.getDirection().multiply(2));
    }

    @Override
    public void onEntityUse(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_BLAZE_SHOOT, 1F, pitch);
    }

    @Override
    public void onDamageEntity(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_BLAZE_AMBIENT, 1F, pitch);
        player.getWorld().spawnParticle(Particle.FLAME, entity.getLocation(), 100, 1, 1, 1);
    }
}
