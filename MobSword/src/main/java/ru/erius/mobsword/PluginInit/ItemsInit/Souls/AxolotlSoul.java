package ru.erius.mobsword.PluginInit.ItemsInit.Souls;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AxolotlSoul extends Soul {

    private final static int ID = 10;
    private final static String NAME = "axolotl_soul";
    private final static EntityType ENTITY_TYPE = EntityType.AXOLOTL;

    public AxolotlSoul() {
        super(ID, NAME, ENTITY_TYPE);
    }

    @Override
    public void onConsumed(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 600, 0));
    }
}
