package com.neptuneclient.voidui.testmod;

import com.neptuneclient.voidui.VoidUI;
import com.neptuneclient.voidui.testmod.impl.IRenderer;
import com.neptuneclient.voidui.utils.Font;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class ModEntry implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("TestMod");

    @Override
    public void onInitializeClient() {
        LOGGER.info("VoidUI is best!");

        ClientLifecycleEvents.CLIENT_STARTED.register((client) -> {
            VoidUI vui = new VoidUI(new IRenderer());

            // testing if nanovg will handle the font buffer
            new Font(vui, "testFont", Path.of("fonts/WorkSans-Regular.ttf"), 28);
        });
    }

}
