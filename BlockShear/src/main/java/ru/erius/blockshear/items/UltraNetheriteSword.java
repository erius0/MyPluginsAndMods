package ru.erius.blockshear.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import ru.erius.eriuslib.items.CustomItem;

public class UltraNetheriteSword extends CustomItem {

    private final static String NAME = "ultra_netherite_sword";
    private final static Material MATERIAL = Material.NETHERITE_SWORD;

    public UltraNetheriteSword() {
        super(NAME, MATERIAL);
        this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
        this.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 10);
        this.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 10);
        this.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 10);
        this.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 10);
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
