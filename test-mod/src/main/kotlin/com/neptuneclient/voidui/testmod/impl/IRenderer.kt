package com.neptuneclient.voidui.testmod.impl

import com.neptuneclient.voidui.rendering.Renderer
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL2.*
import java.awt.Color


class IRenderer: Renderer {
    private var vg: Long = -1

    override fun create() {
        if (vg == -1L) {
            vg = nvgCreate(NVG_ANTIALIAS or NVG_STENCIL_STROKES)
        }
    }

    override fun destroy() {
        nvgDelete(vg)
        vg = -1
    }

    override fun beginFrame(width: Float, height: Float) {
        nvgBeginFrame(vg, width.toFloat(), height.toFloat(), 1.0f)
    }

    override fun endFrame() {
        nvgEndFrame(vg)
    }

    override fun freeResources() {
        // TODO: manage resources such as fonts and paints
    }

    override fun rectangle(x: Float, y: Float, width: Float, height: Float, color: Color) {
        nvgBeginPath(vg)
        nvgRect(vg, x, y, width, height)
        val clr = NVGColor.create()
        clr.r(color.red / 255f)
        clr.g(color.green / 255f)
        clr.b(color.blue / 255f)
        clr.a(color.alpha / 255f)
        nvgFillColor(vg, clr)
        nvgFill(vg)
    }

    override fun roundedRectangle(x: Float, y: Float, width: Float, height: Float, radius: Float, color: Color) {
        nvgBeginPath(vg)
        nvgRoundedRect(vg, x, y, width, height, radius)
        val clr = NVGColor.create()
        clr.r(color.red / 255f)
        clr.g(color.green / 255f)
        clr.b(color.blue / 255f)
        clr.a(color.alpha / 255f)
        nvgFillColor(vg, clr)
        nvgFill(vg)
    }
}