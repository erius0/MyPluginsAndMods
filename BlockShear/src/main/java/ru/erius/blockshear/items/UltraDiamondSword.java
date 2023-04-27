package ru.erius.blockshear.items;

import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import ru.erius.eriuslib.items.CustomItem;

public class UltraDiamondSword extends CustomItem {

    private final static String NAME = "ultra_diamond_sword";
    private final static Material MATERIAL = Material.DIAMOND_SWORD;

    public UltraDiamondSword() {
        super(NAME, MATERIAL);

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
