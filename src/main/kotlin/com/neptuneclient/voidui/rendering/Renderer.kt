package com.neptuneclient.voidui.rendering

import java.awt.Color

/**
 * This interface is the structure of the renderer in VoidUI.
 *
 * Every method which takes coordinates (x, y) and sizes (width, height) has to be implemented with both a [Float] version
 * and an [Int] version.
 */
interface Renderer {

    fun beginFrame()

    fun endFrame()

    fun rectangle(x: Float, y: Float, width: Float, height: Float, color: Color)

}