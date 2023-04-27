package ru.erius.mobsword.PluginInit.ItemsInit.Souls;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PigSoul extends Soul {

    private final static int ID = 5;
    private final static String NAME = "pig_soul";
    private final static EntityType ENTITY_TYPE = EntityType.PIG;

    public PigSoul() {
        super(ID, NAME, ENTITY_TYPE);
    }

    @Override
    public void onConsumed(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 600, 0));
    }
}
