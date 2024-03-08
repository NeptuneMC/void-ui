package com.neptuneclient.voidui.testmod.mixins;

import com.neptuneclient.voidui.VoidUI;
import com.neptuneclient.voidui.testmod.impl.RendererImpl;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.Color;

@Mixin(InGameHud.class)
public class MixinIngameHud {

    private VoidUI voidUI = new VoidUI(new RendererImpl());

    @Inject(method = "render", at = @At("RETURN"))
    public void renderIngameHud(DrawContext context, float tickDelta, CallbackInfo ci) {
        // i have looked at oneconfig to see how they render with nanovg
        // but it didnt make me smarter

        // enable something with stencil
        GL11.glEnable(GL11.GL_STENCIL);
        //GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glDisable(GL11.GL_ALPHA);

        voidUI.getRenderer().beginFrame();
        voidUI.getRenderer().rectangle(100, 100, 200, 120, new Color(255, 99, 99));
        voidUI.getRenderer().endFrame();

        GL11.glEnable(GL11.GL_ALPHA);
        //GL11.glPopAttrib();
    }

}
