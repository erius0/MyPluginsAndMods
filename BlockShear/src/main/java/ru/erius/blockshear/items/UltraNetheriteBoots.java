package ru.erius.blockshear.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import ru.erius.eriuslib.items.CustomItem;

public class UltraNetheriteBoots extends CustomItem {

    private final static String NAME = "ultra_netherite_boots";
    private final static Material MATERIAL = Material.NETHERITE_BOOTS;

    public UltraNetheriteBoots() {
        super(NAME, MATERIAL);
        this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        this.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10);
        this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
        this.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10);
    }

    @Override
    public void addRecipe() {

    }

    @Override
    public void onConsume(PlayerItemConsumeEvent playerItemConsumeEvent) {

    }

    @Override
    public void onRightClickAir(PlayerInteractEvent playerInteractEvent) {

    }

    @Override
    public void onRightClickBlock(PlayerInteractEvent playerInteractEvent) {

    }

    @Override
    public void onRightClickEntity(PlayerInteractEntityEvent playerInteractEntityEvent) {

    }

    @Override
    public void onLeftClickAir(PlayerInteractEvent playerInteractEvent) {

    }

    @Override
    public void onLeftClickBlock(PlayerInteractEvent playerInteractEvent) {

    }

    @Override
    public void onLeftClickEntity(EntityDamageByEntityEvent entityDamageByEntityEvent) {

    }
}
