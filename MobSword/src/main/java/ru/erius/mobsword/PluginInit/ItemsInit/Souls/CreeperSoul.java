package ru.erius.mobsword.PluginInit.ItemsInit.Souls;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CreeperSoul extends Soul {

    private final static int ID = 1;
    private final static String NAME = "creeper_soul";
    private final static EntityType ENTITY_TYPE = EntityType.CREEPER;

    public CreeperSoul() {
        super(ID, NAME, ENTITY_TYPE);
    }

    @Override
    public void onConsumed(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 600, 0));
    }
}
