// Credit: https://github.com/EngineHub/WorldEdit/blob/master/worldedit-bukkit/src/main/java/com/sk89q/worldedit/bukkit/WorldEditPlugin.java#L306
package dev.august.plugins.minecraft.announcer.util

import dev.august.plugins.minecraft.announcer.AutoAnnouncer
import java.io.*

class ConfigLoader(private val plugin: AutoAnnouncer) {
  fun createConfigFile(name: String) {
    val actual = File(plugin.dataFolder, name)
    if (!actual.exists()) {
      try {
        val stream = plugin.getResource("default/$name")
        if (stream == null) throw FileNotFoundException("File in resource 'default/$name' was not found.")

        copy(stream, actual, name)
      } catch(ex: IOException) {
        plugin.logger.severe("Unable to read file $name")
      }
    }
  }

  protected fun copy(stream: InputStream, actual: File, name: String) {
    try {
      val output = FileOutputStream(actual)
      val buffer = ByteArray(8192)

      while (true) {
        val len = stream.read(buffer)
        if (len <= 0) break

        output.write(buffer, 0, len)
      }

      plugin.logger.info("Copied default config file at $name")
    } catch(ex: IOException) {
      plugin.logger.severe("Unable to copy the default file, view from exception:")
      ex.printStackTrace()
    }
  }
}