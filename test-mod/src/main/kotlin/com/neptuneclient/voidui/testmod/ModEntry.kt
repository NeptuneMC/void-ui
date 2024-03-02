package com.neptuneclient.voidui.testmod

import com.neptuneclient.voidui.VoidUI
import net.fabricmc.api.ClientModInitializer
import org.slf4j.LoggerFactory

class ModEntry : ClientModInitializer {

    private val logger = LoggerFactory.getLogger("Void-Test")

    override fun onInitializeClient() {
        val void = VoidUI()

        logger.info("VoidUI is best!")
        logger.info(void.complexFunction(12, 5).toString())
    }

}