package ru.erius.kotlinplugin.item

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.bukkit.util.Vector
import ru.erius.kotlinplugin.KotlinPlugin
import ru.erius.kotlinplugin.util.checkOnGround

object SpringBoots : CustomItem("spring_boots", Material.LEATHER_BOOTS, "Spring Boots", isUnbreakable = true) {

    private const val boostMultiplier = 1

    private var sneakTaskId: Int = -1

    @EventHandler
    fun whileSneaking(evt: PlayerToggleSneakEvent) {
        val bootsId = evt.player.inventory.boots?.id ?: return
        if (bootsId != id) return
        val player = evt.player
        if (!player.checkOnGround()) {
            Bukkit.getScheduler().cancelTask(sneakTaskId)
            player.exp = 0F
            return
        }
        if (evt.isSneaking) {
            player.exp = 0F
            sneakTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(KotlinPlugin.instance, {
                player.exp += 0.02F
                player.playSound(player, Sound.ENTITY_CHICKEN_EGG, 1F, 2F)
                if (player.exp >= 0.98F) {
                    player.exp = 1F
                    Bukkit.getScheduler().cancelTask(sneakTaskId)
                }
            }, 0L, 1L)
        } else {
            Bukkit.getScheduler().cancelTask(sneakTaskId)
            val x = player.velocity.x + player.eyeLocation.direction.x * player.exp
            val y = player.velocity.y + player.eyeLocation.direction.y + player.exp * boostMultiplier
            val z = player.velocity.z + player.eyeLocation.direction.z * player.exp
            player.velocity = Vector(x, y, z)
            player.exp = 0F
        }
    }
}