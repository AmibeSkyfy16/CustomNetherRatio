package ch.skyfy.customnetherratio.config

import ch.skyfy.customnetherratio.CustomNetherRatioMod.Companion.MOD_ID
import ch.skyfy.tomlconfiglib.Defaultable
import ch.skyfy.tomlconfiglib.Validatable
import kotlinx.serialization.Serializable
import net.peanuuutz.tomlkt.TomlComment

@Serializable
data class ModConfig(
    @TomlComment("[Default -> 1500] The new \"travel scale ratio\". 8 by default in Minecraft vanilla. 16 by Default with this mod")
    @JvmField
    val netherRatio: Double,

) : Validatable {
    override fun validateImpl(errors: MutableList<String>) {
        if (netherRatio < 0)
            errors.add("[ERROR] $MOD_ID -> config.toml -> netherRatio can't be negative ! Current value is $netherRatio")
    }
}

class DefaultModConfig : Defaultable<ModConfig> {
    override fun getDefault() = ModConfig(128.0)
}