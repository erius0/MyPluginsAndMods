package ru.erius.mobsword.PluginInit.ItemsInit.Souls;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ChickenSoul extends Soul {

    private final static int ID = 7;
    private final static String NAME = "chicken_soul";
    private final static EntityType ENTITY_TYPE = EntityType.CHICKEN;

    public ChickenSoul() {
        super(ID, NAME, ENTITY_TYPE);
    }

    @Override
    public void onConsumed(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 600, 0));
    }
}
