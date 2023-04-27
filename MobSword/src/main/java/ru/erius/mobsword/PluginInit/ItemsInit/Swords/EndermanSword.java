package ru.erius.mobsword.PluginInit.ItemsInit.Swords;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EndermanSword extends Sword {

    private final static int ID = 16;
    private final static String NAME = "enderman_sword";
    private final static ItemStack COMPONENT = new EndermanSword();
    private final static int DAMAGE = 10;

    public EndermanSword() {
        super(ID, NAME, COMPONENT, DAMAGE);
    }

    @Override
    public void onAirSwing(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ENDERMAN_AMBIENT, 1F, pitch);
    }

    @Override
    public void onBlockSwing(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ENDERMAN_AMBIENT, 1F, pitch);
    }

    @Override
    public void onAirUse(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, pitch);
        Location loc = player.getEyeLocation();
        Entity entity = player.getWorld().spawnEntity(loc, EntityType.ENDER_PEARL);
        EnderPearl pearl = (EnderPearl) entity;
        pearl.setShooter(player);
        entity.setVelocity(loc.getDirection().multiply(2));
    }

    @Override
    public void onBlockUse(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, pitch);
        Location loc = player.getEyeLocation();
        Entity entity = player.getWorld().spawnEntity(loc, EntityType.ENDER_PEARL);
        EnderPearl pearl = (EnderPearl) entity;
        pearl.setShooter(player);
        entity.setVelocity(loc.getDirection().multiply(2));
    }

    @Override
    public void onEntityUse(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1F, pitch);
    }

    @Override
    public void onDamageEntity(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ENDERMAN_AMBIENT, 1F, pitch);
        Location loc = entity.getLocation();
        player.spawnParticle(Particle.DRAGON_BREATH, entity.getLocation(), 100, 1, 1, 1);
        double x = Math.random() * 20 + loc.getX() - 10, y = Math.random() * 5 + loc.getY(), z = Math.random() * 20 + loc.getZ() - 10;
        entity.teleport(new Location(entity.getWorld(), x, y, z));
    }
}
