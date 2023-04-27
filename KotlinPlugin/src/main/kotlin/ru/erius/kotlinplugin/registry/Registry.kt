package ru.erius.kotlinplugin.registry

import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import ru.erius.kotlinplugin.KotlinPlugin
import ru.erius.kotlinplugin.command.CustomCommand
import ru.erius.kotlinplugin.item.CustomItem
import ru.erius.kotlinplugin.item.customModelData
import ru.erius.kotlinplugin.item.id

object Registry {

    object ItemRegistry {

        val items = mutableMapOf<String, CustomItem>()

        fun registerItem(plugin: Plugin, item: CustomItem) {
            val id = item.id
            item.customModelData = items.size
            (item as ItemStack).id = id
            Bukkit.getServer().pluginManager.registerEvents(item, KotlinPlugin.instance)
            if (items.containsKey(id))
                KotlinPlugin.instance.logger.warning("Duplicate declaration of item $id from plugin $plugin, overriding the old item with the new one...")
            items[id] = item
            KotlinPlugin.instance.logger.info("Registered item $id from plugin $plugin")
        }

        fun registerItems(plugin: Plugin, vararg items: CustomItem) = items.forEach { registerItem(plugin, it) }

    }

    object CommandRegistry {

        fun registerCommand(plugin: Plugin, command: CustomCommand) {
            val pluginCommand = plugin.server.getPluginCommand(command.name) ?: KotlinPlugin.instance.logger.warning(
                "Failed to register command ${command.name} " +
                        "from plugin $plugin, it is not declared in plugin.yml"
            ).let { return }
            pluginCommand.setExecutor(command)
            pluginCommand.tabCompleter = command
            KotlinPlugin.instance.logger.info("Registered command ${command.name} from plugin $plugin")
        }

        fun registerCommands(plugin: Plugin, vararg commands: CustomCommand) =
            commands.forEach { registerCommand(plugin, it) }

    }

}