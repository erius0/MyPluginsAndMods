package ru.erius.mobsword.PluginInit.ItemsInit.Souls;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ZombieSoul extends Soul {

    private final static int ID = 3;
    private final static String NAME = "zombie_soul";
    private final static EntityType ENTITY_TYPE = EntityType.ZOMBIE;

    public ZombieSoul() {
        super(ID, NAME, ENTITY_TYPE);
    }

    @Override
    public void onConsumed(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 600, 0));
    }
}
