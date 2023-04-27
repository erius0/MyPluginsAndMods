package ru.erius.mobsword.PluginInit;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import ru.erius.mobsword.PluginInit.ItemsInit.Consumable;
import ru.erius.mobsword.PluginInit.ItemsInit.Items;
import ru.erius.mobsword.PluginInit.ItemsInit.MyItem;
import ru.erius.mobsword.PluginInit.ItemsInit.Swords.Sword;

public class ItemsEvents {

    public static void addDrops(EntityDeathEvent evt) {
        if (Items.getMobDrop().containsKey(evt.getEntityType())) {
            ItemStack drop = new ItemStack(Items.getMobDrop().get(evt.getEntityType()));
            evt.getDrops().add(drop);
        }
    }

    public static void handleAirSwing(PlayerInteractEvent evt) {
        ItemStack item = evt.getItem();
        Sword sword = getSword(item);
        if (sword != null)
            sword.onAirSwing(evt.getPlayer());
    }

    public static void handleBlockSwing(PlayerInteractEvent evt) {
        ItemStack item = evt.getItem();
        Sword sword = getSword(item);
        if (sword != null)
            sword.onBlockSwing(evt.getPlayer(), evt.getClickedBlock());
    }

    public static void handleAirUse(PlayerInteractEvent evt) {
        ItemStack item = evt.getItem();
        Sword sword = getSword(item);
        if (sword != null)
            sword.onAirUse(evt.getPlayer());
    }

    public static void handleBlockUse(PlayerInteractEvent evt) {
        ItemStack item = evt.getItem();
        Sword sword = getSword(item);
        if (sword != null)
            sword.onBlockUse(evt.getPlayer(), evt.getClickedBlock());
    }

    public static void handleEntityUse(PlayerInteractAtEntityEvent evt) {
        if (evt.getHand() != EquipmentSlot.HAND)
            return;
        ItemStack item = evt.getPlayer().getInventory().getItemInMainHand();
        Sword sword = getSword(item);
        if (sword != null)
            sword.onEntityUse(evt.getPlayer(), evt.getRightClicked());
    }

    public static void handleDamage(EntityDamageByEntityEvent evt) {
        if (evt.getDamager() instanceof Player) {
            Player player = (Player) evt.getDamager();
            ItemStack item = player.getInventory().getItemInMainHand();
            Sword sword = getSword(item);
            if (sword != null)
                sword.onDamageEntity(player, evt.getEntity());
        }
    }

    private static Sword getSword(ItemStack item) {
        if (item == null)
            return null;
        ItemMeta meta = item.getItemMeta();
        return meta != null && meta.hasCustomModelData() ? Items.getSwordType().get(meta.getCustomModelData()) : null;
    }

    public static boolean getLookingAt(Player player, Entity entity) {
        Location eye = player.getEyeLocation();
        Vector toEntity = entity.getLocation().toVector().subtract(eye.toVector());
        double dot = toEntity.normalize().dot(eye.getDirection());
        return dot > 0.9;
    }

    public static void handleConsume(PlayerItemConsumeEvent evt) {
        ItemStack item = evt.getItem();
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasCustomModelData() && Items.getItems().containsKey(meta.getCustomModelData())) {
            evt.setCancelled(true);
            MyItem myItem = Items.getItems().get(meta.getCustomModelData());
            if (myItem instanceof Consumable)
                ((Consumable) myItem).onConsumed(evt.getPlayer());
            evt.getPlayer().getInventory().getItemInMainHand().setAmount(item.getAmount() - 1);
        }
    }
}
