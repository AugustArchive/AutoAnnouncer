package dev.august.plugins.minecraft.announcer

import dev.august.plugins.minecraft.announcer.util.ConfigLoader
import dev.august.plugins.minecraft.announcer.util.Timer
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import kotlin.system.exitProcess
import org.bukkit.ChatColor

import java.io.File

class AutoAnnouncer: JavaPlugin() {
  private val configFile: File = File(dataFolder, "config.yml")
  val timer: Timer = Timer()

  override fun onEnable() {
    val loader = ConfigLoader(this) // TODO: Fix output stream file being null
    loader.createConfigFile("config.yml")

    val config = YamlConfiguration()
    try {
      config.load(configFile)
    } catch(ex: Exception) {
      ex.printStackTrace()
    }

    logger.info("AutoAnnouncer is been initialized...")
    val announcements = config.getList("announcements") as List<String>?
    val interval = config.getInt("interval", 1000)

    if (announcements == null) {
      logger.severe("Announcements is null, please add some announcements!")
      exitProcess(1)
    }

    if (announcements.isEmpty()) {
      logger.severe("Announcements is empty")
      exitProcess(1)
    }

    if (interval < 1000) {
      logger.severe("Interval cannot be lower or equal to 1 second (1000ms)")
      exitProcess(1)
    }

    timer.createInterval(interval) {
      val announcement = announcements.random()
      val prefix = config.getString("prefix", "&8[&d&lAnnouncement&8]")

      server.onlinePlayers.forEach {
        it.sendMessage(message("$prefix &7$announcement"))
      }
    }
  }

  override fun onDisable() {
    logger.severe("Plugin has been disabled.")
    timer.cancel()
  }

  fun message(m: String) = ChatColor.translateAlternateColorCodes('&', m)
}