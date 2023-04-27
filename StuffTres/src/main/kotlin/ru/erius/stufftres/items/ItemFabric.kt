package ru.erius.stufftres.items

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

object ItemFabric {

    private val items = hashMapOf<String, ItemStack>()

    fun createMeta( material: Material = Material.BARRIER,
                    displayName: String = "none",
                    lore: String = "",
                    isUnbreakable: Boolean = false
                    ): ItemMeta {
        val meta = ItemStack(material).itemMeta!!
        meta.setDisplayName(displayName)
        meta.lore = listOf(lore)
        meta.isUnbreakable = isUnbreakable
        return meta
    }

    fun createAndAddItem( id: String,
                    material: Material = Material.BARRIER,
                    displayName: String = "none",
                    lore: String = "",
                    isUnbreakable: Boolean = false
                    ) {
        val item = ItemStack(material)
        item.itemMeta = createMeta(material, displayName, lore, isUnbreakable)
        registerItem(id, item)
    }

    private fun registerItem(id: String, item: ItemStack) { items[id] = item }
}