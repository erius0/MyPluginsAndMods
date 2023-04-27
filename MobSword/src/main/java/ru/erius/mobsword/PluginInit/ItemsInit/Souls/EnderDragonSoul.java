package ru.erius.mobsword.PluginInit.ItemsInit.Souls;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import ru.erius.mobsword.MobSword;

public class EnderDragonSoul extends Soul {

    private final static int ID = 11;
    private final static String NAME = "ender_dragon_soul";
    private final static EntityType ENTITY_TYPE = EntityType.ENDER_DRAGON;

    public EnderDragonSoul() {
        super(ID, NAME, ENTITY_TYPE);
    }

    @Override
    public void onConsumed(Player player) {
        double health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health + 4);
    }

    public void addRecipe() {
        NamespacedKey key = new NamespacedKey(MobSword.plugin, getName().toLowerCase().replace(" ", "_"));
        ItemStack result = new ItemStack(this);
        result.setAmount(9);
        ShapelessRecipe recipe = new ShapelessRecipe(key, result);
        recipe.addIngredient(1, Material.DRAGON_EGG);
        Bukkit.addRecipe(recipe);
    }
}
