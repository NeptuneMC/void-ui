package com.neptuneclient.voidui.rendering

import com.neptuneclient.voidui.framework.Offset
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.theme.TextStyle
import com.neptuneclient.voidui.utils.Font
import com.neptuneclient.voidui.utils.ImageBuffer
import java.awt.Color
import java.nio.ByteBuffer
import java.nio.file.Path

/**
 * This interface is the structure of the renderer in VoidUI.
 *
 * Every method which takes coordinates (x, y) and sizes (width, height) has to be implemented with both a [Float] version
 * and an [Int] version.
 */
interface Renderer {

    /**
     * The width of the application window.
     */
    fun windowWidth(): Int

    /**
     * The height of the application window.
     */
    fun windowHeight(): Int

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
     * Destroys the renderer and cleans up everything.
     */
    fun destroy()

    /**
     * Returns the current position of the mouse.
     */
    fun mousePosition(): Offset

    /**
     * Registers a font to the renderer.
     *
     * @param name The identifier of the font.
     * @param data The data of the font file in a byte buffer.
     */
    fun registerFont(name: String, data: ByteBuffer)

    /**
     * Registers an image to the renderer.
     *
     * @param path The path to the image, in case the render backend needs it.
     * @param data The image data in a byte buffer.
     *
     * @return An image id which the renderer uses to identify the image.
     */
    fun registerImage(path: Path, data: ByteBuffer): Int

    /**
     * Deletes an image from the render backend.
     *
     * @param image The image to be deleted.
     */
    fun unregisterImage(image: ImageBuffer)

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
     * Renders a hollow rectangle with the given dimensions, size, radius and color.
     *
     * @param x x coordinate of the rectangle
     * @param y y coordinate of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param thickness thickness of the frame
     * @param color color of the rectangle
     */
    fun rectangleFrame(x: Float, y: Float, width: Float, height: Float, thickness: Float, color: Color)

    fun rectangleFrame(x: Int, y: Int, width: Int, height: Int, thickness: Int, color: Color) {
        rectangleFrame(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat(), thickness.toFloat(), color)
    }

    /**
     * Renders a rounded rectangle with the given dimensions, size, radius and color.
     *
     * @param x x coordinate of the rectangle
     * @param y y coordinate of the rectangle
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param radius The radius of the rectangle.
     * @param color color of the rectangle
     */
    fun roundedRectangle(x: Float, y: Float, width: Float, height: Float, radius: CornerRadius, color: Color)

    fun roundedRectangle(x: Int, y: Int, width: Int, height: Int, radius: CornerRadius, color: Color) {
        roundedRectangle(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat(), radius, color)
    }

    fun roundedRectangle(x: Float, y: Float, width: Float, height: Float, radius: Float, color: Color) {
        roundedRectangle(x, y, width, height, CornerRadius.all(radius), color)
    }

    fun roundedRectangle(x: Int, y: Int, width: Int, height: Int, radius: Int, color: Color) {
        roundedRectangle(x, y, width, height, CornerRadius.all(radius.toFloat()), color)
    }

    /**
     * Renders a hollow rounded rectangle with the given dimensions, size, radius and color.
     *
     * @param x x coordinate of the rectangle.
     * @param y y coordinate of the rectangle.
     * @param width width of the rectangle.
     * @param height height of the rectangle.
     * @param thickness thickness of the frame.
     * @param radius The radius of the rectangle.
     * @param color color of the rectangle.
     */
    fun roundedRectangleFrame(x: Float, y: Float, width: Float, height: Float, radius: CornerRadius, thickness: Float, color: Color)

    fun roundedRectangleFrame(x: Int, y: Int, width: Int, height: Int, radius: CornerRadius, thickness: Int, color: Color) {
        roundedRectangleFrame(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat(), radius, thickness.toFloat(), color)
    }

    fun roundedRectangleFrame(x: Float, y: Float, width: Float, height: Float, radius: Float, thickness: Float, color: Color) {
        roundedRectangleFrame(x, y, width, height, CornerRadius.all(radius), thickness, color)
    }

    fun roundedRectangleFrame(x: Int, y: Int, width: Int, height: Int, radius: Int, thickness: Int, color: Color) {
        roundedRectangleFrame(x, y, width, height, CornerRadius.all(radius.toFloat()), thickness, color)
    }

    /**
     * Draws an image to the screen.
     *
     * @param x The x position of the image.
     * @param y The y position of the image.
     * @param width The width of the image.
     * @param height The height of the image.
     * @param image An image object which contains data about the image.
     */
    fun image(x: Float, y: Float, width: Float, height: Float, image: ImageBuffer)

    fun image(x: Int, y: Int, width: Int, height: Int, image: ImageBuffer) {
        image(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat(), image)
    }

    /**
     * Draws an image with rounded edges to the screen.
     *
     * @param x The x position of the image.
     * @param y The y position of the image.
     * @param width The width of the image.
     * @param height The height of the image.
     * @param radius The radius of the image's corners.
     * @param image An image object which contains data about the image.
     */
    fun roundedImage(x: Float, y: Float, width: Float, height: Float, radius: CornerRadius, image: ImageBuffer)

    fun roundedImage(x: Int, y: Int, width: Int, height: Int, radius: CornerRadius, image: ImageBuffer) {
        roundedImage(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat(), radius, image)
    }

    fun roundedImage(x: Float, y: Float, width: Float, height: Float, radius: Float, image: ImageBuffer) {
        roundedImage(x, y, width, height, CornerRadius.all(radius), image)
    }

    fun roundedImage(x: Int, y: Int, width: Int, height: Int, radius: Int, image: ImageBuffer) {
        roundedImage(x, y, width, height, CornerRadius.all(radius.toFloat()), image)
    }

    /**
     * Renders a text with the given dimensions, size and font.
     *
     * @param x x coordinate of the text.
     * @param y y coordinate of the text.
     * @param text text to render.
     * @param font font of the text.
     * @param style style values for the text.
     */
    fun text(x: Float, y: Float, text: String, font: Font, style: TextStyle)

    fun text(x: Int, y: Int, text: String, font: Font, style: TextStyle) {
        text(x.toFloat(), y.toFloat(), text, font, style)
    }

    /**
     * Returns the bounds of the given text with the given font.
     *
     * @param text text to measure
     * @param font font of the text
     * @param style style values for the text.
     *
     * @return the width and height of the text
     */
    fun getTextBounds(text: String, font: Font, style: TextStyle): Size

}