package com.neptuneclient.voidui.testmod.impl;

import com.neptuneclient.voidui.rendering.Renderer;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVGGL3;

import java.awt.Color;

import static org.lwjgl.nanovg.NanoVG.*;

public class RendererImpl implements Renderer {

    private long vg;

    public RendererImpl() {
        this.vg = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_ANTIALIAS | NanoVGGL3.NVG_STENCIL_STROKES);
    }

    @Override
    public void beginFrame() {
        nvgBeginFrame(vg, MinecraftClient.getInstance().getWindow().getWidth(), MinecraftClient.getInstance().getWindow().getHeight(), 1);
    }

    @Override
    public void endFrame() {
        nvgEndFrame(vg);
    }

    @Override
    public void rectangle(float x, float y, float width, float height, @NotNull Color color) {
        NVGColor c = NVGColor.calloc();
        c.r(color.getRed());
        c.g(color.getGreen());
        c.b(color.getBlue());
        c.a(color.getAlpha());

        nvgBeginPath(vg);
        nvgRect(vg, x, y, width, height);
        nvgFillColor(vg, c);
        nvgFill(vg);
        nvgClosePath(vg);

        c.free();
    }

}
