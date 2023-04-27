package ru.erius.stufftres.items

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object ChargeHoe: ItemStack(Material.WOODEN_HOE, 1) {
    init {
        this.itemMeta = ItemFabric.createMeta(this.type, "")
    }
}