package ru.erius.mobsword.PluginInit.ItemsInit.Souls;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SheepSoul extends Soul {

    private final static int ID = 9;
    private final static String NAME = "sheep_soul";
    private final static EntityType ENTITY_TYPE = EntityType.SHEEP;

    public SheepSoul() {
        super(ID, NAME, ENTITY_TYPE);
    }

    @Override
    public void onConsumed(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 0));
    }
}
