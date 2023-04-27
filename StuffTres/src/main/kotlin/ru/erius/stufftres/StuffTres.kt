package ru.erius.stufftres

import org.bukkit.plugin.java.JavaPlugin

class StuffTres: JavaPlugin() {

    companion object {
        lateinit var plugin: StuffTres
        private set
    }

    override fun onEnable() {
        super.onEnable()
        logger.info("$name enabled")
    }

    override fun onDisable() {
        super.onDisable()
        logger.info("$name disabled")
    }
}
