package ru.erius.mobsword.PluginInit.ItemsInit.Swords;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.erius.mobsword.PluginInit.ItemsInit.Souls.CreeperSoul;

public class CreeperSword extends Sword {

    private final static int ID = 12;
    private final static String NAME = "creeper_sword";
    private final static ItemStack COMPONENT = new CreeperSoul();
    private final static int DAMAGE = 6;


    public CreeperSword() {
        super(ID, NAME, COMPONENT, DAMAGE);
    }

    @Override
    public void onAirSwing(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_CREEPER_PRIMED, 1F, pitch);
    }

    @Override
    public void onBlockSwing(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_CREEPER_PRIMED, 1F, pitch);
    }

    @Override
    public void onAirUse(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_TNT_PRIMED, 1F, pitch);
        Location loc = player.getEyeLocation();
        Entity entity = player.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
        entity.setVelocity(loc.getDirection().multiply(2));
    }

    @Override
    public void onBlockUse(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_TNT_PRIMED, 1F, pitch);
        Location loc = player.getEyeLocation();
        Entity entity = player.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
        entity.setVelocity(loc.getDirection().multiply(2));
    }

    @Override
    public void onEntityUse(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_TNT_PRIMED, 1F, pitch);
    }

    @Override
    public void onDamageEntity(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_CREEPER_PRIMED, 1F, pitch);
        player.setInvulnerable(true);
        player.getWorld().createExplosion(entity.getLocation(), 3);
        player.setInvulnerable(false);
    }
}
