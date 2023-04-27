package ru.erius.mobsword.PluginInit.ItemsInit.Swords;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import ru.erius.mobsword.MobSword;
import ru.erius.mobsword.PluginInit.ItemsInit.Souls.EnderDragonSoul;

public class EnderDragonSword extends Sword {

    private final static int ID = 22;
    private final static String NAME = "ender_dragon_sword";
    private final static ItemStack COMPONENT = new EnderDragonSoul();
    private final static int DAMAGE = 100;

    public EnderDragonSword() {
        super(ID, NAME, COMPONENT, DAMAGE);
        this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);
    }

    @Override
    public void onAirSwing(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1F, pitch);
    }

    @Override
    public void onBlockSwing(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1F, pitch);
    }

    @Override
    public void onAirUse(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, 1F, pitch);
        Location loc = player.getEyeLocation();
        Entity entity = player.getWorld().spawnEntity(loc, EntityType.DRAGON_FIREBALL);
        entity.setVelocity(loc.getDirection().multiply(2));
    }

    @Override
    public void onBlockUse(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, 1F, pitch);
        Location loc = player.getEyeLocation();
        Entity entity = player.getWorld().spawnEntity(loc, EntityType.DRAGON_FIREBALL);
        entity.setVelocity(loc.getDirection().multiply(2));
    }

    @Override
    public void onEntityUse(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, 1F, pitch);
    }

    @Override
    public void onDamageEntity(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1F, pitch);
        player.spawnParticle(Particle.GLOW, entity.getLocation(), 100, 1, 1, 1);
    }


    public static void onHolding(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
                if (meta != null && meta.hasCustomModelData() && meta.getCustomModelData() == ID) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 4));
                    player.setAllowFlight(true);
                } else {
                    player.removePotionEffect(PotionEffectType.SPEED);
                    if (player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR)
                        player.setAllowFlight(false);
                }
            }
        }.runTaskTimer(MobSword.plugin, 0, 1);
    }
}
