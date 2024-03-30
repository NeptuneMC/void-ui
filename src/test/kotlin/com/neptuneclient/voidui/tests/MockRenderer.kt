package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.utils.Font
import com.neptuneclient.voidui.widgets.objects.EdgeInsets
import com.neptuneclient.voidui.widgets.objects.Size
import java.awt.Color

class MockRenderer: Renderer {
    override fun windowWidth() = 0
    override fun windowHeight() = 0

    override fun beginFrame() {
        println("beginFrame")
    }

    override fun endFrame() {
        println("endFrame")
    }

    override fun registerFont(font: Font) {
        println("registerFont")
    }

    override fun rectangle(x: Float, y: Float, width: Float, height: Float, color: Color) {
        println("rectangle")
    }

    override fun rectangleFrame(x: Float, y: Float, width: Float, height: Float, thickness: Float, color: Color) {
        println("rectangleFrame")
    }

    override fun roundedRectangle(x: Float, y: Float, width: Float, height: Float, radius: Float, color: Color) {
        println("roundedRectangle")
    }

    override fun roundedRectangleFrame(
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        thickness: EdgeInsets,
        radius: Float,
        color: Color
    ) {
        println("roundedRectangleFrame")
    }

    override fun text(x: Float, y: Float, text: String, font: Font, color: Color) {
        println("text")
    }

    override fun getTextBounds(text: String, font: Font): Size {
        println("getTextBounds")
        return Size(0f, 0f)
    }
}