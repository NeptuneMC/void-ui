package com.neptuneclient.voidui.testmod;

import com.neptuneclient.voidui.VoidUI;
import com.neptuneclient.voidui.testmod.example.TestScreen;
import com.neptuneclient.voidui.testmod.example.TestTheme;
import com.neptuneclient.voidui.testmod.impl.RendererImpl;
import com.neptuneclient.voidui.testmod.impl.ScreenBridge;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.server.command.CommandManager.literal;

public class ModEntry implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("TestMod");

    @Override
    public void onInitializeClient() {
        LOGGER.info("VoidUI is best!");
    }

}
