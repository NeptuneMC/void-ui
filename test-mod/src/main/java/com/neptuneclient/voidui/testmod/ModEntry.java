package com.neptuneclient.voidui.testmod;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModEntry implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("TestMod");

    @Override
    public void onInitializeClient() {
        LOGGER.info("VoidUI is best!");
    }

}
