package com.neptuneclient.voidui.testmod.mixins;

import com.neptuneclient.voidui.VoidUI;
import com.neptuneclient.voidui.ui.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    VoidUI voidUI = new VoidUI();
    /**
     * @author
     * @reason
     */
    @Overwrite
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        voidUI.setCurrentScreen((Screen) (Object) this);

    }
}
