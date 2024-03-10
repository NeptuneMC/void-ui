package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.utils.Font
import java.awt.Color

class MockRenderer : Renderer {
    override fun beginFrame() {
        println("beginFrame")
    }

    override fun endFrame() {
        println("endFrame")
    }

    override fun registerFont(font: Font) {
        println("registerFont id=${font.identifier}")
    }

    override fun rectangle(x: Float, y: Float, width: Float, height: Float, color: Color) {
        println("rectangle: x=$x, y=$y, width=$width, height=$height, color=$color")
    }

    override fun roundedRectangle(x: Float, y: Float, width: Float, height: Float, radius: Float, color: Color) {
        println("roundedRectangle: x=$x, y=$y, width=$width, height=$height, radius=$radius, color=$color")
    }
}