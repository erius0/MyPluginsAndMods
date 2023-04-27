package ru.erius.kotlinplugin.command

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.inventory.ItemStack
import ru.erius.kotlinplugin.KotlinPlugin
import ru.erius.kotlinplugin.registry.Registry
import ru.erius.kotlinplugin.util.intOrDefault

object GiveItem : CustomCommand("givecustom") {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return when (args.size) {
            0, 1 -> false
            2 -> giveItem(sender, args[0], args[1], 1)
            else -> giveItem(sender, args[0], args[1], args[2].intOrDefault(1))
        }
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String>? = when (args.size) {
        1 -> Bukkit.getOnlinePlayers().map { it.name }.toMutableList()
        2 -> Registry.ItemRegistry.items.keys.toMutableList()
        else -> null
    }

    private fun giveItem(sender: CommandSender, nickname: String, itemId: String, amount: Int): Boolean {
        val player = Bukkit.getPlayer(nickname) ?:
        sender.sendMessage("${ChatColor.RED}Player $nickname is offline or doesn't exist").let { return false }
        val customItem = Registry.ItemRegistry.items[itemId] ?:
        sender.sendMessage("${ChatColor.RED}Item $itemId is not registered").let { return false }
        val item = ItemStack(customItem)
        item.amount = amount.coerceIn(1..64)
        player.inventory.addItem(item)
        val msg = "${ChatColor.GREEN}Gave $amount [$itemId] to $nickname"
        sender.sendMessage(msg)
        KotlinPlugin.instance.logger.info(ChatColor.stripColor(msg))
        return true
    }
}