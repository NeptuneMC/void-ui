package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.utils.Font
import com.neptuneclient.voidui.utils.Image
import com.neptuneclient.voidui.widgets.objects.EdgeInsets
import com.neptuneclient.voidui.widgets.objects.Size
import java.awt.Color
import java.nio.ByteBuffer
import java.nio.file.Path

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

    override fun registerImage(path: Path, data: ByteBuffer): Int {
        println("registerImage")
        return 0
    }

    override fun unregisterImage(image: Image) {
        println("unregisterImage")
    }

    override fun rectangle(x: Float, y: Float, width: Float, height: Float, color: Color) {
        println("rectangle")
    }

    override fun rectangleFrame(x: Float, y: Float, width: Float, height: Float, thickness: Float, color: Color) {
        println("rectangleFrame")
    }

    override fun roundedRectangle(
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        r0: Float,
        r1: Float,
        r2: Float,
        r3: Float,
        color: Color
    ) {
        println("roundedRectangle")
    }

    override fun roundedRectangleFrame(
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        r0: Float,
        r1: Float,
        r2: Float,
        r3: Float,
        thickness: Float,
        color: Color
    ) {
        println("roundedRectangleFrame")
    }

    override fun image(x: Float, y: Float, width: Float, height: Float, image: Image) {
        println("image")
    }

    override fun roundedImage(x: Float, y: Float, width: Float, height: Float, radius: Float, image: Image) {
        println("roundedImage")
    }

    override fun text(x: Float, y: Float, text: String, font: Font, color: Color) {
        println("text")
    }

    override fun getTextBounds(text: String, font: Font): Size {
        println("getTextBounds")
        return Size(0f, 0f)
    }
}