package ru.erius.kotlinplugin.command

import org.bukkit.command.CommandExecutor
import org.bukkit.command.TabCompleter

abstract class CustomCommand(val name: String) : CommandExecutor, TabCompleter
