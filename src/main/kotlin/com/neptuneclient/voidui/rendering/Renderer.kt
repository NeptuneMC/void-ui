package com.neptuneclient.voidui.rendering

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
     */
    fun beginFrame()

    /**
     * Ends the current frame. This method should be called after all rendering operations.
     */
    fun endFrame()

    /**
     * Frees all resources used by the renderer.
     * This method should be called when the renderer is no longer needed.
     * (Called before endFrame())
     */
    fun freeResources()

    /**
     * Renders a frame with the given width and height and executes the given [frame] lambda.
     * @param width width of the frame
     * @param height height of the frame
     * @param frame lambda to execute
     */
    fun frame(width: Int, height: Int, frame: Runnable) {
        beginFrame()
        frame.run()
        freeResources()
        endFrame()
    }

    /**
     * Renders a rectangle with the given dimensions, size and color.
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

    fun rectangle(x: Float, y: Float, width: Float, height: Float, color: Int) {
        rectangle(x, y, width, height, Color(color))
    }

    /**
     * Renders a rounded rectangle with the given dimensions, size, radius and color.
     * @param x x coordinate of the rectangle
     * @param y y coordinate of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param radius radius of the rectangle
     * @param color color of the rectangle
     */
    fun roundedRectangle(x: Float, y: Float, width: Float, height: Float, radius: Float, color: Color)

}