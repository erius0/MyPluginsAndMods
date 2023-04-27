package ru.erius.blockshear.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import ru.erius.eriuslib.items.CustomItem;

public class UltraDiamondHelm extends CustomItem {

    private final static String NAME = "ultra_diamond_helmet";
    private final static Material MATERIAL = Material.DIAMOND_HELMET;

    public UltraDiamondHelm() {
        super(NAME, MATERIAL);
        this.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        this.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
        this.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
        this.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
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
