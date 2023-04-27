package ru.erius.blockshear.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import ru.erius.blockshear.BlockShear;
import ru.erius.eriuslib.items.CustomItem;

public class InfiniteChicken extends CustomItem {

    private final static String NAME = "infinite_chicken";
    private final static Material MATERIAL = Material.COOKED_CHICKEN;

    public InfiniteChicken() {
        super(NAME, MATERIAL);
        this.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
    }

    @Override
    public void addRecipe() {

    }

    @Override
    public void onConsume(PlayerItemConsumeEvent evt) {
        Bukkit.getScheduler().runTaskLater(BlockShear.plugin, () ->
                evt.getPlayer().getInventory().addItem(new InfiniteChicken()), 1);
    }

    @Override
    public void onRightClickAir(PlayerInteractEvent evt) {

    }

    @Override
    public void onRightClickBlock(PlayerInteractEvent evt) {

    }

    @Override
    public void onRightClickEntity(PlayerInteractEntityEvent evt) {

    }

    @Override
    public void onLeftClickAir(PlayerInteractEvent evt) {

    }

    @Override
    public void onLeftClickBlock(PlayerInteractEvent evt) {

    }

    @Override
    public void onLeftClickEntity(EntityDamageByEntityEvent evt) {

    }
}
