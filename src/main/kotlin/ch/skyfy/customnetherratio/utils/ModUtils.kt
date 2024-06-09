package ch.skyfy.customnetherratio.utils

import ch.skyfy.customnetherratio.CustomNetherRatioMod
import kotlin.io.path.createDirectory
import kotlin.io.path.exists

fun setupConfigDirectory(){
    try {
        if(!CustomNetherRatioMod.CONFIG_DIRECTORY.exists()) CustomNetherRatioMod.CONFIG_DIRECTORY.createDirectory()
    } catch (e: java.lang.Exception) {
        CustomNetherRatioMod.LOGGER.fatal("An exception occurred. Could not create the root folder that should contain the configuration files")
        throw RuntimeException(e)
    }
}