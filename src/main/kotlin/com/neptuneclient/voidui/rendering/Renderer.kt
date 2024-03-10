package com.neptuneclient.voidui.rendering

import java.awt.Color
import java.awt.Rectangle
import java.awt.geom.Dimension2D

/**
 * This interface is the structure of the renderer in VoidUI.
 *
 * Every method which takes coordinates (x, y) and sizes (width, height) has to be implemented with both a [Float] version
 * and an [Int] version.
 */
interface Renderer {
    /**
     * Creates the renderer and initializes all necessary resources.
     * This method should be called BEFORE any rendering operations.
     */
    fun create()

    /**
     * Destroys the renderer and frees all resources.
     * This method should be called when the renderer is no longer needed.
     */
    fun destroy()
    /**
     * Begins a new frame. This method should be called before any rendering operations.
     * @param width width of the frame
     * @param height height of the frame
     */
    fun beginFrame(width: Float, height: Float)

    /**
     * Ends the current frame.
     * This method should be called after all rendering operations, but before freeing resources.
     */
    fun endFrame()

    /**
     * Frees all resources used by the renderer.
     * This method should be called when the renderer is no longer needed.
     */
    fun freeResources()

    /**
     * Renders a frame with the given width and height and executes the given [frame] lambda.
     * @param width width of the frame
     * @param height height of the frame
     * @param frame lambda to execute
     */
    fun frame(width: Float, height: Float, frame: Runnable) {
        beginFrame(width, height)
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