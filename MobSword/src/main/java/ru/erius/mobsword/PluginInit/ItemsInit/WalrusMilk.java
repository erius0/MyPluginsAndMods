package ru.erius.mobsword.PluginInit.ItemsInit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WalrusMilk extends MyItem implements Consumable {

    private final static int ID = 23;
    private final static String NAME = "walrus_milk";
    private final static Material MATERIAL = Material.MILK_BUCKET;

    public WalrusMilk() {
        super(ID, NAME, MATERIAL);
    }

    public void onConsumed(Player player) {
        PotionEffectType[] effectTypes =  PotionEffectType.values();
        int duration = (int) (600 + Math.random() * 1801);
        int amplifier = (int) (Math.random() * 4);
        int effectId = (int) (1 + Math.random() * (effectTypes.length - 1));
        PotionEffectType effectType = effectTypes[effectId];
        player.addPotionEffect(new PotionEffect(effectType, duration, amplifier));
    }
}
