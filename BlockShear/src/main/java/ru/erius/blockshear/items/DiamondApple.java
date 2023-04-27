package ru.erius.blockshear.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.erius.eriuslib.items.CustomItem;

public class DiamondApple extends CustomItem {

    private final static String NAME = "diamond_apple";
    private final static Material MATERIAL = Material.GOLDEN_APPLE;

    public DiamondApple() {
        super(NAME, MATERIAL);
    }

    @Override
    public void addRecipe() {

    }

    @Override
    public void onConsume(PlayerItemConsumeEvent playerItemConsumeEvent) {
        Player player = playerItemConsumeEvent.getPlayer();
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 800, 2));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 12000, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 12000, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 12000, 1));
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
