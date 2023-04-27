package ru.erius.kotlinplugin

import org.bukkit.plugin.java.JavaPlugin
import ru.erius.kotlinplugin.command.GiveItem
import ru.erius.kotlinplugin.item.SpringBoots
import ru.erius.kotlinplugin.registry.Registry

class KotlinPlugin : JavaPlugin() {

    companion object {
        lateinit var instance: JavaPlugin
        private set
    }

    override fun onEnable() {
        super.onEnable()
        instance = this
        registerEverything()
        logger.info("$name enabled")
    }

    override fun onDisable() {
        super.onDisable()
        logger.info("$name disabled")
    }

    private fun registerEverything() {
        Registry.ItemRegistry.registerItems(instance, SpringBoots)
        Registry.CommandRegistry.registerCommands(instance, GiveItem)
    }
}