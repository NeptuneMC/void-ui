package com.neptuneclient.voidui.testmod.impl;

import com.neptuneclient.voidui.rendering.Renderer;
import com.neptuneclient.voidui.utils.Font;
import com.neptuneclient.voidui.widgets.objects.EdgeInsets;
import com.neptuneclient.voidui.widgets.objects.Size;
import kotlin.Pair;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.BufferUtils;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVGGL3;

import java.awt.Color;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.nanovg.NanoVG.*;

public class RendererImpl implements Renderer {

    private long vg = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_ANTIALIAS | NanoVGGL3.NVG_STENCIL_STROKES);
    private GLState glState = new GLState();

    @Override
    public void beginFrame() {
        glState.backupGlState();
        nvgBeginFrame(vg, MinecraftClient.getInstance().getWindow().getWidth(), MinecraftClient.getInstance().getWindow().getHeight(), 1);
    }

    @Override
    public void endFrame() {
        nvgEndFrame(vg);
        glState.restoreGlState();
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
        NVGColor c = NVGColor.calloc();
        c.r(color.getRed());
        c.g(color.getGreen());
        c.b(color.getBlue());
        c.a(color.getAlpha());

        nvgFontFace(vg, font.getIdentifier());
        nvgFontSize(vg, font.getSize());
        nvgTextMetrics(vg, null, null, BufferUtils.createFloatBuffer(1));

        FloatBuffer bounds = BufferUtils.createFloatBuffer(4);
        nvgTextBounds(vg, x, y, text, bounds);

        nvgFillColor(vg, c);
        nvgText(vg, x, y + (y - bounds.get(1)), text);
        nvgClosePath(vg);

        c.free();
    }

    @NotNull
    @Override
    public Size getTextBounds(@NotNull String text, @NotNull Font font) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(4);

        nvgFontSize(vg, font.getSize());
        nvgFontFace(vg, font.getIdentifier());
        nvgTextBounds(vg, 0f, 0f, text, buffer);
        return new Size(buffer.get(2) - buffer.get(0), buffer.get(3) - buffer.get(1));
    }

    @Override
    public void rectangleFrame(float x, float y, float width, float height, float thickness, @NotNull Color color) {
        NVGColor c = NVGColor.calloc();
        c.r(color.getRed());
        c.g(color.getGreen());
        c.b(color.getBlue());
        c.a(color.getAlpha());

        nvgBeginPath(vg);
        nvgRect(vg, x, y, width, height);
        nvgStrokeColor(vg, c);
        nvgStrokeWidth(vg, thickness);
        nvgStroke(vg);
        nvgClosePath(vg);

        c.free();
    }

    @Override
    public void roundedRectangleFrame(float x, float y, float width, float height, @NotNull EdgeInsets thickness, float radius, @NotNull Color color) {
        NVGColor c = NVGColor.calloc();
        c.r(color.getRed());
        c.g(color.getGreen());
        c.b(color.getBlue());
        c.a(color.getAlpha());

        nvgBeginPath(vg);
        nvgRect(vg, x, y, width, height);
        nvgStrokeColor(vg, c);
        nvgStrokeWidth(vg, thickness.component1());
        nvgStroke(vg);
        nvgClosePath(vg);

        c.free();
    }
}
