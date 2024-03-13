package com.neptuneclient.voidui.testmod.impl;

import com.neptuneclient.voidui.rendering.Renderer;
import com.neptuneclient.voidui.utils.Font;
import kotlin.Pair;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVGGL3;

import java.awt.Color;

import static org.lwjgl.nanovg.NanoVG.*;

public class RendererImpl implements Renderer {

    private long vg = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_ANTIALIAS | NanoVGGL3.NVG_STENCIL_STROKES);

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

    @Override
    public void roundedRectangle(float x, float y, float width, float height, float radius, @NotNull Color color) {
        NVGColor c = NVGColor.calloc();
        c.r(color.getRed());
        c.g(color.getGreen());
        c.b(color.getBlue());
        c.a(color.getAlpha());

        nvgBeginPath(vg);
        nvgRoundedRect(vg, x, y, width, height, radius);
        nvgFillColor(vg, c);
        nvgFill(vg);
        nvgClosePath(vg);

        c.free();
    }

    @Override
    public void registerFont(@NotNull Font font) {
        nvgCreateFontMem(vg, font.getIdentifier(), font.getData(), 1);
    }

    @Override
    public int windowWidth() {
        return MinecraftClient.getInstance().getWindow().getWidth();
    }

    @Override
    public int windowHeight() {
        return MinecraftClient.getInstance().getWindow().getHeight();
    }

    @Override
    public void text(float x, float y, @NotNull String text, @NotNull Font font, @NotNull Color color) {

    }

    @NotNull
    @Override
    public Pair<Float, Float> getTextBounds(@NotNull String text, @NotNull Font font) {
        return null;
    }
}
