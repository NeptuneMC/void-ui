package com.neptuneclient.voidui.testmod.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import com.neptuneclient.voidui.VoidUI;
import com.neptuneclient.voidui.testmod.impl.RendererImpl;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.Color;

@Mixin(InGameHud.class)
public class MixinInGameHud {

    private VoidUI v = new VoidUI(new RendererImpl());

    @Inject(method = "render", at = @At("RETURN"))
    public void mixin$render(DrawContext context, float tickDelta, CallbackInfo ci) {
        // i have looked at oneconfig to see how they render with nanovg
        // but it didnt make me smarter

        v.getRenderer().beginFrame();
        v.getRenderer().rectangle(100, 100, 200, 120, new Color(255, 99, 99));
        v.getRenderer().endFrame();
    }

}
