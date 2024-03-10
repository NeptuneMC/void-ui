package com.neptuneclient.voidui.testmod.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import com.neptuneclient.voidui.VoidUI;
import com.neptuneclient.voidui.testmod.impl.IRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.render.item.ItemRenderer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL33;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.Color;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Unique
    private final VoidUI v = new VoidUI(new IRenderer());

    @Inject(method = "<init>", at = @At("TAIL"))
    public void mixin$init(MinecraftClient client, ItemRenderer itemRenderer, CallbackInfo ci) {
        v.getRenderer().create();
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void mixin$render(DrawContext context, float tickDelta, CallbackInfo ci) {
        //context.getMatrices().push();
       // RenderSystem.enableBlend();
        v.getRenderer().frame(
                MinecraftClient.getInstance().getWindow().getWidth(),
                MinecraftClient.getInstance().getWindow().getHeight(),
                () -> {
                    v.getRenderer().roundedRectangle(
                            100,
                            100,
                            100,
                            50,
                            25,
                            new Color(255, 0, 0, 255)
                    );
                }
        );
        //RenderSystem.disableBlend();
        //context.getMatrices().pop();
    }

}
