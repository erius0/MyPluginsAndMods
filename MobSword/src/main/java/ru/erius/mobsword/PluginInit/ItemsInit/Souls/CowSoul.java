package ru.erius.mobsword.PluginInit.ItemsInit.Souls;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CowSoul extends Soul {

    private final static int ID = 8;
    private final static String NAME = "cow_soul";
    private final static EntityType ENTITY_TYPE = EntityType.COW;

    public CowSoul() {
        super(ID, NAME, ENTITY_TYPE);
    }

    @Override
    public void onConsumed(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 0));
    }
}
