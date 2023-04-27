package ru.erius.blockshear.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import ru.erius.eriuslib.items.CustomItem;

public class KnockbackSlime extends CustomItem {

    private final static String NAME = "knockback_slime";
    private final static Material MATERIAL = Material.SLIME_BALL;

    public KnockbackSlime() {
        super(NAME, MATERIAL);
        this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);
    }

    @Override
    public void addRecipe() {

    }

    @Override
    public void onConsume(PlayerItemConsumeEvent evt) {

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
