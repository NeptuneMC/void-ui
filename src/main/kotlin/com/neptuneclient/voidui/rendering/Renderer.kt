package com.neptuneclient.voidui.rendering

import com.neptuneclient.voidui.utils.Font
import org.jetbrains.annotations.NotNull
import java.awt.Color
import java.awt.Rectangle

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
     * Clears all resources used by the renderer.
     */
    fun clearResources() {
        // TODO: remove everything from an arraylist of resources
    }

    /**
     * Helper method to render a frame.
     * @param l The lambda to execute in the frame.
     */
    fun frame(l : () -> Unit) {
        beginFrame()
        l.invoke()
        clearResources()
        endFrame()
    }

    /**
     * Registers a font/image/etc. to the render backend.
     * The [Font] class provides the render backend with all necessary resources
     * to create a font.
     *
     * @param o The object to register.
     */
    fun register(o: Any): Renderer

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

    fun rectangle(rect: Rectangle, color: Color) {
        rectangle(rect.x, rect.y, rect.width, rect.height, color)
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

}