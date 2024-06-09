package ch.skyfy.customnetherratio.config

import ch.skyfy.customnetherratio.CustomNetherRatioMod
import ch.skyfy.tomlconfiglib.ConfigData

object Configs {
    @JvmField
    val MOD_CONFIG = ConfigData.invoke<ModConfig, DefaultModConfig>(CustomNetherRatioMod.CONFIG_DIRECTORY.resolve("config.toml"))
}