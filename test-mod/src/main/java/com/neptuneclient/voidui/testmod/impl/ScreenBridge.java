package com.neptuneclient.voidui.testmod.impl;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ScreenBridge extends Screen {

    private com.neptuneclient.voidui.widgets.Screen voidScreen;

    public ScreenBridge(com.neptuneclient.voidui.widgets.Screen voidScreen) {
        super(Text.of(voidScreen.getClass().getSimpleName()));
        this.voidScreen = voidScreen;
    }

    @Override
    protected void init() {
        voidScreen.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        voidScreen.render();
    }

}
