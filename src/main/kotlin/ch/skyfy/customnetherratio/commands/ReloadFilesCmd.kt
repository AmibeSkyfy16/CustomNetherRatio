package ch.skyfy.customnetherratio.commands

import ch.skyfy.customnetherratio.CustomNetherRatioMod
import ch.skyfy.customnetherratio.config.Configs
import ch.skyfy.tomlconfiglib.ConfigManager
import com.mojang.brigadier.Command
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.StringArgumentType
import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import net.minecraft.command.CommandSource
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.Text
import java.util.concurrent.CompletableFuture

class ReloadFilesCmd : Command<ServerCommandSource> {

    companion object {
        fun register(dispatcher: CommandDispatcher<ServerCommandSource>) {
            val cmd = CommandManager.literal("parallels").requires { source -> source.hasPermissionLevel(4) }
                .then(
                    CommandManager.literal("reload").then(
                        CommandManager.argument("fileName", StringArgumentType.string()).suggests(::getConfigFiles).executes(ReloadFilesCmd())
                    )
                )
            dispatcher.register(cmd)
        }

        @Suppress("UNUSED_PARAMETER")
        private fun <S : ServerCommandSource> getConfigFiles(commandContext: CommandContext<S>, suggestionsBuilder: SuggestionsBuilder): CompletableFuture<Suggestions> {
            return CommandSource.suggestMatching(listOf(Configs.MOD_CONFIG.relativeFilePath.fileName.toString()), suggestionsBuilder)
        }
    }

    override fun run(context: CommandContext<ServerCommandSource>): Int {
        val fileName = StringArgumentType.getString(context, "fileName")
        val list = mutableListOf<Boolean>()

        when(fileName){
            "config.toml" -> list.add(ConfigManager.reloadConfig(Configs.MOD_CONFIG))
        }

        if (list.contains(false)) {
            context.source.sendFeedback({ Text.literal("Configuration could not be reloaded") }, false)
            CustomNetherRatioMod.LOGGER.warn("Configuration could not be reloaded")
        } else {
            context.source.sendFeedback({ Text.literal("The configuration was successfully reloaded") }, false)
            CustomNetherRatioMod.LOGGER.info("The configuration was successfully reloaded")
        }

        return Command.SINGLE_SUCCESS
    }

}