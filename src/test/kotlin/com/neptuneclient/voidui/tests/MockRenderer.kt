package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.rendering.Renderer
import java.awt.Color

class MockRenderer : Renderer {
    override fun beginFrame() {
        println("beginFrame")
    }

    override fun endFrame() {
        println("endFrame")
    }

    override fun freeResources() {
        println("freeResources")
    }

    override fun rectangle(x: Float, y: Float, width: Float, height: Float, color: Color) {
        println("rectangle: x=$x, y=$y, width=$width, height=$height, color=$color")
    }

    override fun roundedRectangle(x: Float, y: Float, width: Float, height: Float, radius: Float, color: Color) {
        println("roundedRectangle: x=$x, y=$y, width=$width, height=$height, radius=$radius, color=$color")
    }
}