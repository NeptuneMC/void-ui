package com.neptuneclient.voidui.rendering

import com.neptuneclient.voidui.utils.Font
import java.awt.Color

/**
 * This interface is the structure of the renderer in VoidUI.
 *
 * Every method which takes coordinates (x, y) and sizes (width, height) has to be implemented with both a [Float] version
 * and an [Int] version.
 */
interface Renderer {

    /**
     * Begins a new frame. This method should be called before any rendering operations.
     *
     * The width and height of the frame should be the same as the window's width and height.
     */
    fun beginFrame()

    /**
     * Ends the current frame.
     * This method should be called after all rendering operations, but before freeing resources.
     */
    fun endFrame()

    /**
     * Registers a font to the renderer. The [Font] class provides the render backend with all necessary resources
     * to create the font.
     *
     * @param font The font object to register.
     */
    fun registerFont(font: Font)

    /**
     * Renders a rectangle with the given dimensions, size, radius and color.
     *
     * @param x x coordinate of the rectangle
     * @param y y coordinate of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param color color of the rectangle
     */
    fun rectangle(x: Float, y: Float, width: Float, height: Float, color: Color)

    fun rectangle(x: Int, y: Int, width: Int, height: Int, color: Color) {
        rectangle(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat(), color)
    }

    /**
     * Renders a rounded rectangle with the given dimensions, size, radius and color.
     *
     * @param x x coordinate of the rectangle
     * @param y y coordinate of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param radius radius of the rectangle
     * @param color color of the rectangle
     */
    fun roundedRectangle(x: Float, y: Float, width: Float, height: Float, radius: Float, color: Color)

    fun roundRectangle(x: Int, y: Int, width: Int, height: Int, radius: Int, color: Color) {
        roundedRectangle(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat(), radius.toFloat(), color)
    }

}