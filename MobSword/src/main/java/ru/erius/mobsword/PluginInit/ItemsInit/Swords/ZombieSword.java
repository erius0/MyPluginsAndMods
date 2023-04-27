package ru.erius.mobsword.PluginInit.ItemsInit.Swords;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.erius.mobsword.PluginInit.ItemsInit.Souls.ZombieSoul;

public class ZombieSword extends Sword {

    private final static int ID = 14;
    private final static String NAME = "zombie_sword";
    private final static ItemStack COMPONENT = new ZombieSoul();
    private final static int DAMAGE = 5;

    public ZombieSword() {
        super(ID, NAME, COMPONENT, DAMAGE);
    }

    @Override
    public void onAirSwing(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ZOMBIE_AMBIENT, 1F, pitch);
    }

    @Override
    public void onBlockSwing(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ZOMBIE_AMBIENT, 1F, pitch);
    }

    @Override
    public void onAirUse(Player player) {

    }

    @Override
    public void onBlockUse(Player player, Block block) {

    }

    @Override
    public void onEntityUse(Player player, Entity entity) {
        if (entity instanceof Mob)
            ((Mob) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 9));
        player.playSound(player.getEyeLocation(), Sound.BLOCK_AMETHYST_BLOCK_STEP, 1, 1);
    }

    @Override
    public void onDamageEntity(Player player, Entity entity) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.playSound(player.getEyeLocation(), Sound.ENTITY_ZOMBIE_AMBIENT, 1F, pitch);
        PotionEffect potion = player.getPotionEffect(PotionEffectType.ABSORPTION);
        int amplifier = potion == null ? 0 : potion.getAmplifier() + 1;
        player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, amplifier));
    }
}
