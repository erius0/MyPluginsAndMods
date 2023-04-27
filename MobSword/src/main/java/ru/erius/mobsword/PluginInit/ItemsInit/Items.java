package ru.erius.mobsword.PluginInit.ItemsInit;

import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import ru.erius.mobsword.MobSword;
import ru.erius.mobsword.PluginInit.ItemsInit.Souls.*;
import ru.erius.mobsword.PluginInit.ItemsInit.Swords.*;

import java.util.HashMap;

public class Items {

    private final static HashMap<Integer, MyItem> items = new HashMap<>();
    private final static HashMap<Integer, Consumable> consumableItems = new HashMap<>();
    private final static HashMap<EntityType, Soul> mobDrop = new HashMap<>();
    private final static HashMap<Integer, Sword> swordType = new HashMap<>();

    public static void initItems() {
        registerDrops();
        registerRecipes();
    }

    public static ItemStack getItem(int id, int amount) {
        if (items.containsKey(id)) {
            ItemStack item = new ItemStack(items.get(id));
            item.setAmount(amount);
            return item;
        } else
            return null;
    }

    private static void registerDrops() {
        //creeper_soul
        Soul soul = new CreeperSoul();
        consumableItems.put(soul.getId(), soul);
        //skeleton_soul
        soul = new SkeletonSoul();
        consumableItems.put(soul.getId(), soul);
        //zombie_soul
        soul = new ZombieSoul();
        consumableItems.put(soul.getId(), soul);
        //enderman_soul
        soul = new EndermanSoul();
        consumableItems.put(soul.getId(), soul);
        //pig_soul
        soul = new PigSoul();
        consumableItems.put(soul.getId(), soul);
        //blaze_soul
        soul = new BlazeSoul();
        consumableItems.put(soul.getId(), soul);
        //chicken_soul
        soul = new ChickenSoul();
        consumableItems.put(soul.getId(), soul);
        //cow_soul
        soul = new CowSoul();
        consumableItems.put(soul.getId(), soul);
        //sheep_soul
        soul = new SheepSoul();
        consumableItems.put(soul.getId(), soul);
        //axolotl_soul
        soul = new AxolotlSoul();
        consumableItems.put(soul.getId(), soul);
        //ender_dragon_soul
        soul = new EnderDragonSoul();
        consumableItems.put(soul.getId(), soul);
        //walrus_milk
        WalrusMilk milk = new WalrusMilk();
        consumableItems.put(milk.getId(), milk);
        consumableItems.values().forEach(s -> {
            if (s instanceof MyItem) {
                MyItem item = (MyItem) s;
                items.put(item.getId(), item);
                if (item instanceof Soul) {
                    Soul mySoul = (Soul) item;
                    mobDrop.put(mySoul.getEntityType(), mySoul);
                }
            }
        });
        //add ender_dragon_soul recipe
        new EnderDragonSoul().addRecipe();
        MobSword.plugin.getLogger().info("Registered new drops");
    }

    private static void registerRecipes() {
        //creeper_sword
        Sword sword = new CreeperSword();
        swordType.put(sword.getId(), sword);
        //pig_sword
        sword = new PigSword();
        swordType.put(sword.getId(), sword);
        //skeleton_sword
        sword = new SkeletonSword();
        swordType.put(sword.getId(), sword);
        //enderman_sword
        sword = new EndermanSword();
        swordType.put(sword.getId(), sword);
        //zombie_sword
        sword = new ZombieSword();
        swordType.put(sword.getId(), sword);
        //blaze_sword
        sword = new BlazeSword();
        swordType.put(sword.getId(), sword);
        //chicken_sword
        sword = new ChickenSword();
        swordType.put(sword.getId(), sword);
        //cow_sword
        sword = new CowSword();
        swordType.put(sword.getId(), sword);
        //sheep_sword
        sword = new SheepSword();
        swordType.put(sword.getId(), sword);
        //axolotl_sword
        sword = new AxolotlSword();
        swordType.put(sword.getId(), sword);
        //ender_dragon_sword
        sword = new EnderDragonSword();
        swordType.put(sword.getId(), sword);
        //init recipes
        swordType.values().forEach(s -> {
            items.put(s.getId(), s);
            s.addRecipe();
        });
        MobSword.plugin.getLogger().info("Registered new recipes");
    }

    public static HashMap<Integer, MyItem> getItems() {
        return items;
    }

    public static HashMap<EntityType, Soul> getMobDrop() {
        return mobDrop;
    }

    public static HashMap<Integer, Sword> getSwordType() {
        return swordType;
    }

    public static HashMap<Integer, Consumable> getConsumableItems() {
        return consumableItems;
    }
}
