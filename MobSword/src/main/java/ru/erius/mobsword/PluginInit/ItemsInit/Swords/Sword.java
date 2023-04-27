package ru.erius.mobsword.PluginInit.ItemsInit.Swords;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import ru.erius.mobsword.MobSword;
import ru.erius.mobsword.PluginInit.ItemsInit.MyItem;

import java.util.UUID;

public abstract class Sword extends MyItem {

    private final static Material MATERIAL = Material.IRON_SWORD;
    private final static String[][] recipeShape = {
            {"H..", "H..", "S.."},
            {".H.", ".H.", ".S."},
            {"..H", "..H", "..S"}
    };
    private final static Material STICK = Material.STICK;
    private final int DAMAGE;
    private final ItemStack COMPONENT;

    public Sword(int id, String name, ItemStack component, int damage) {
        super(id, name, MATERIAL);
        this.COMPONENT = component;
        this.DAMAGE = damage;
        ItemMeta meta = this.getItemMeta();
        Attribute damageAttribute = Attribute.GENERIC_ATTACK_DAMAGE;
        AttributeModifier damageModifier = new AttributeModifier(UUID.randomUUID(), damageAttribute.getKey().getKey(),
            damage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(damageAttribute, damageModifier);
        this.setItemMeta(meta);
    }

    abstract public void onAirSwing(Player player);

    abstract public void onBlockSwing(Player player, Block block);

    abstract public void onAirUse(Player player);

    abstract public void onBlockUse(Player player, Block block);

    abstract public void onEntityUse(Player player, Entity entity);

    abstract public void onDamageEntity(Player player, Entity entity);

    public void addRecipe() {
        for (int i = 0; i < recipeShape.length; i++) {
            NamespacedKey key = new NamespacedKey(MobSword.plugin, getName() + i);
            ShapedRecipe recipe = new ShapedRecipe(key, this);
            recipe.shape(recipeShape[i]);
            recipe.setIngredient('H', new RecipeChoice.ExactChoice(COMPONENT));
            recipe.setIngredient('S', STICK);
            Bukkit.addRecipe(recipe);
        }
    }

    public ItemStack getComponent() {
        return COMPONENT;
    }
    
    public int getDamage() {
        return DAMAGE;
    }
}
