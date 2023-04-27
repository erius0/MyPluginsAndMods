package ru.erius.orechest.items.pickaxes;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import ru.erius.eriuslib.items.CustomItem;
import ru.erius.orechest.guis.OreChestGUI;

import java.util.List;

public class ChestedNetheritePick extends CustomItem {

    private final static String NAME = "chested_netherite_pickaxe";
    private final static Material MATERIAL = Material.NETHERITE_PICKAXE;
    private final static String DEFAULT_TITLE = "Chested netherite pickaxe";
    private final static List<Material> BLOCK_TYPES = List.of(Material.ANCIENT_DEBRIS);

    public ChestedNetheritePick() {
        super(NAME, MATERIAL, DEFAULT_TITLE);
    }

    public static String getItemName() {
        return NAME;
    }

    @EventHandler
    private void onBlockRightClick(PlayerInteractEvent evt) {
        if (evt.getAction() != Action.RIGHT_CLICK_BLOCK || !this.isThisItem(evt.getItem())) return;
        Block block = evt.getClickedBlock();
        if (block != null && BLOCK_TYPES.contains(block.getType()))
            new OreChestGUI(block, evt.getPlayer());
        else
            evt.getPlayer().getWorld().playSound(evt.getClickedBlock().getLocation(), Sound.BLOCK_CHEST_LOCKED, 1F, 1.2F);
    }
}