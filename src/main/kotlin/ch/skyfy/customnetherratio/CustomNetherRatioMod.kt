package ch.skyfy.customnetherratio

import ch.skyfy.customnetherratio.commands.ReloadFilesCmd
import ch.skyfy.customnetherratio.config.Configs
import ch.skyfy.customnetherratio.utils.setupConfigDirectory
import ch.skyfy.tomlconfiglib.ConfigManager
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.loader.api.FabricLoader
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.nio.file.Path

@Suppress("MemberVisibilityCanBePrivate")
class CustomNetherRatioMod : ModInitializer {

    companion object {
        const val MOD_ID: String = "customnetherratio"
        val CONFIG_DIRECTORY: Path = FabricLoader.getInstance().configDir.resolve(MOD_ID)
        val LOGGER: Logger = LogManager.getLogger(CustomNetherRatioMod::class.java)
    }

    init {
        setupConfigDirectory()
        ConfigManager.loadConfigs(arrayOf(Configs.javaClass))
    }

    override fun onInitialize() {
        registerCommands()



        ServerLifecycleEvents.SERVER_STARTED.register{

        }
    }

    private fun registerCommands() = CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
        ReloadFilesCmd.register(dispatcher)
    }

}