package ru.erius.mobsword.PluginInit.ItemsInit.Swords;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import ru.erius.mobsword.PluginInit.ItemsInit.Souls.AxolotlSoul;

public class AxolotlSword extends Sword {

    private final static int ID = 21;
    private final static String NAME = "axolotl_sword";
    private final static ItemStack COMPONENT = new AxolotlSoul();
    private final static int DAMAGE = 12;

    public AxolotlSword() {
        super(ID, NAME, COMPONENT, DAMAGE);
    }

    @Override
    public void onAirSwing(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_AXOLOTL_IDLE_AIR, 1F, pitch);
    }

    @Override
    public void onBlockSwing(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_AXOLOTL_IDLE_AIR, 1F, pitch);
    }

    @Override
    public void onAirUse(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_FISHING_BOBBER_SPLASH, 1F, pitch);
        for (int i = 0; i < 40; i++) {
            FallingBlock water = player.getWorld().spawnFallingBlock(player.getLocation(), Material.WATER.createBlockData());
            player.spawnParticle(Particle.WATER_DROP, player.getEyeLocation(), 10, 1, 1, 1);
            double x = Math.random() * 2 - 1, z = Math.random() * 2 - 1;
            water.setVelocity(new Vector(x, 0.5, z));
        }
    }

    @Override
    public void onBlockUse(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_FISHING_BOBBER_SPLASH, 1F, pitch);
        for (int i = 0; i < 40; i++) {
            FallingBlock water = player.getWorld().spawnFallingBlock(player.getLocation(), Material.WATER.createBlockData());
            player.spawnParticle(Particle.WATER_DROP, player.getEyeLocation(), 10, 1, 1, 1);
            double x = Math.random() * 2 - 1, z = Math.random() * 2 - 1;
            water.setVelocity(new Vector(x, 0.5, z));
        }
    }

    @Override
    public void onEntityUse(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_FISHING_BOBBER_SPLASH, 1F, pitch);
    }

    @Override
    public void onDamageEntity(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_AXOLOTL_IDLE_AIR, 1F, pitch);
        player.getWorld().spawnParticle(Particle.WATER_DROP, entity.getLocation(), 100, 1, 1, 1);
    }
}
