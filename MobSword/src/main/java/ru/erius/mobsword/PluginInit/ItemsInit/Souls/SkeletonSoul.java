package ru.erius.mobsword.PluginInit.ItemsInit.Souls;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SkeletonSoul extends Soul {

    private final static int ID = 2;
    private final static String NAME = "skeleton_soul";
    private final static EntityType ENTITY_TYPE = EntityType.SKELETON;

    public SkeletonSoul() {
        super(ID, NAME, ENTITY_TYPE);
    }

    @Override
    public void onConsumed(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 0));
    }
}
