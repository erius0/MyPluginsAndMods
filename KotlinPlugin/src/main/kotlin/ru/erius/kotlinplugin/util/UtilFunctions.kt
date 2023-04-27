package ru.erius.kotlinplugin.util

import org.bukkit.entity.Player

fun String.intOrDefault(default: Int) = this.toIntOrNull() ?: default

fun String.longOrDefault(default: Long) = this.toLongOrNull() ?: default

fun String.doubleOrDefault(default: Double) = this.toDoubleOrNull() ?: default

fun String.floatOrDefault(default: Float) = this.toFloatOrNull() ?: default

fun String.byteOrDefault(default: Byte) = this.toByteOrNull() ?: default

fun String.shortOrDefault(default: Short) = this.toShortOrNull() ?: default

fun Player.checkOnGround(): Boolean = !this.isFlying && this.velocity.y != 0.0
