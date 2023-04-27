package ru.erius.mobsword.PluginInit.ItemsInit.Swords;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.erius.mobsword.PluginInit.ItemsInit.Souls.CowSoul;
import ru.erius.mobsword.PluginInit.ItemsInit.WalrusMilk;

public class CowSword extends Sword {

    private final static int ID = 19;
    private final static String NAME = "cow_sword";
    private final static ItemStack COMPONENT = new CowSoul();
    private final static int DAMAGE = 4;

    public CowSword() {
        super(ID, NAME, COMPONENT, DAMAGE);
    }

    @Override
    public void onAirSwing(Player player) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_COW_AMBIENT, 1F, pitch);
    }

    @Override
    public void onBlockSwing(Player player, Block block) {
        float pitch = (float) (Math.random() * 2 + 0.1);
        player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_COW_AMBIENT, 1F, pitch);
    }

    @Override
    public void onAirUse(Player player) {

    }

    @Override
    public void onBlockUse(Player player, Block block) {

    }

    @Override
    public void onEntityUse(Player player, Entity entity) {

    }

    @Override
    public void onDamageEntity(Player player, Entity entity) {
        if ((int) (Math.random() * 4) == 0) {
            player.getInventory().addItem(new WalrusMilk());
            float pitch = (float) (Math.random() * 2 + 0.1);
            player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_COW_MILK, 1F, pitch);
            player.getWorld().spawnParticle(Particle.HEART, player.getEyeLocation(), 100, 1, 1, 1);
        } else {
            float pitch = (float) (Math.random() * 2 + 0.1);
            player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_COW_AMBIENT, 1F, pitch);
            player.getWorld().spawnParticle(Particle.SMOKE_LARGE, entity.getLocation(), 100, 1, 1, 1);
        }
    }
}
