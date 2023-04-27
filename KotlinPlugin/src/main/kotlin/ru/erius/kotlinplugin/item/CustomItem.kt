package ru.erius.kotlinplugin.item

import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.Listener
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import ru.erius.kotlinplugin.KotlinPlugin

private val idKey = NamespacedKey(KotlinPlugin.instance, "id")

var ItemStack.id: String?
    get() = itemMeta?.persistentDataContainer?.get(idKey, PersistentDataType.STRING)
    set(value) {
        val meta = itemMeta ?: return
        meta.persistentDataContainer.set(idKey, PersistentDataType.STRING, value ?: "")
        itemMeta = meta
    }

var ItemStack.customModelData: Int?
    get() = itemMeta?.customModelData
    internal set(value) {
        val meta = itemMeta
        meta?.setCustomModelData(value)
        itemMeta = meta
    }

open class CustomItem(
    val id: String,
    material: Material,
    displayName: String = id,
    amount: Int = 1,
    lore: String = "",
    isUnbreakable: Boolean = false
) :
    ItemStack(material, amount), Listener {

    init {
        val meta = itemMeta!!
        meta.setDisplayName(displayName)
        meta.lore = listOf(lore)
        meta.isUnbreakable = isUnbreakable
        itemMeta = meta
    }
}