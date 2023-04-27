package ru.erius.armorblocks.EquipHandle;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

import static ru.erius.armorblocks.EquipHandle.BlockEffectsInit.blockEffects;

public class InventoryClickHandle {

    private final static int HELMET_SLOT = 39;

    public static void clickHandle(InventoryClickEvent evt) {
        Player player = (Player) evt.getWhoClicked();
        ItemStack newHelmet = evt.getCursor();
        newHelmet = newHelmet == null ? new ItemStack(Material.AIR) : new ItemStack(newHelmet);
        ItemStack oldHelmet = player.getInventory().getHelmet();
        oldHelmet = oldHelmet == null ? new ItemStack(Material.AIR) : new ItemStack(oldHelmet);
        if (evt.getSlot() == HELMET_SLOT) {
            switch (evt.getClick()) {
                case LEFT:
                    clearEffects(blockEffects.get(oldHelmet.getType()), player);
                    int count;
                    if (newHelmet.getType() == oldHelmet.getType()) {
                        count = oldHelmet.getAmount() + newHelmet.getAmount();
                        if (count <= 64) {
                            oldHelmet.setAmount(0);
                            newHelmet.setAmount(count);
                        }
                    } else
                        count = newHelmet.getAmount();
                    int amplifier = Math.min(count, 4) - 1;
                    applyEffects(blockEffects.get(newHelmet.getType()), player, amplifier);
                    replaceHelmet(player, oldHelmet, newHelmet);
                    break;
                case RIGHT:
                    clearEffects(blockEffects.get(oldHelmet.getType()), player);
                    if (oldHelmet.getType() == Material.AIR) {
                        oldHelmet = new ItemStack(newHelmet);
                        oldHelmet.setAmount(newHelmet.getAmount() - 1);
                        newHelmet.setAmount(1);
                    } else if (oldHelmet.getType() == newHelmet.getType()) {
                        if (oldHelmet.getAmount() >= 64)
                            break;
                        int newHelmetAmount = newHelmet.getAmount(), oldHelmetAmount = oldHelmet.getAmount();
                        newHelmet.setAmount(oldHelmetAmount + 1);
                        oldHelmet.setAmount(newHelmetAmount - 1);
                    }
                    amplifier = Math.min(newHelmet.getAmount(), 4) - 1;
                    applyEffects(blockEffects.get(newHelmet.getType()), player, amplifier);
                    if (newHelmet.getType() == Material.AIR) {
                        amplifier = Math.min(oldHelmet.getAmount() / 2, 4) - 1;
                        if (oldHelmet.getAmount() > 1)
                            applyEffects(blockEffects.get(oldHelmet.getType()), player, amplifier);
                        break;
                    }
                    replaceHelmet(player, oldHelmet, newHelmet);
                    break;
                case DROP:
                    if (newHelmet.getType() == Material.AIR && blockEffects.containsKey(oldHelmet.getType())) {
                        clearEffects(blockEffects.get(oldHelmet.getType()), player);
                        if (oldHelmet.getAmount() > 1)
                            applyEffects(blockEffects.get(oldHelmet.getType()), player, Math.min(oldHelmet.getAmount() - 1, 4) - 1);
                    }
                    break;
                case CONTROL_DROP:
                case SHIFT_LEFT:
                    if (newHelmet.getType() == Material.AIR && blockEffects.containsKey(oldHelmet.getType()))
                        clearEffects(blockEffects.get(oldHelmet.getType()), player);
                default:
                    break;
            }
        }

    }

    private static void clearEffects(List<PotionEffectType> potionEffectTypes, Player player) {
        if (potionEffectTypes == null)
            return;
        for (PotionEffectType potionEffectType : potionEffectTypes)
            player.removePotionEffect(potionEffectType);
    }

    private static void applyEffects(List<PotionEffectType> potionEffectTypes, Player player, int amplifier) {
        if (potionEffectTypes == null)
            return;
        for (PotionEffectType potionEffectType : potionEffectTypes)
            player.addPotionEffect(new PotionEffect(potionEffectType, Integer.MAX_VALUE, amplifier));
    }

    private static void replaceHelmet(Player player, ItemStack oldHelmet, ItemStack newHelmet) {
        new Thread(() -> {
            player.setItemOnCursor(oldHelmet);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.getInventory().setHelmet(newHelmet);
        }).start();
    }
}
