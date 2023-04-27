package ru.erius.kotlinlib

import org.bukkit.plugin.java.JavaPlugin

class KotlinLib : JavaPlugin() {
    override fun onEnable() {
        super.onEnable()
        logger.info("$name enabled")
    }

    override fun onDisable() {
        super.onDisable()
        logger.info("$name disabled")
    }
}