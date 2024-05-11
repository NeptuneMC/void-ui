package com.neptuneclient.voidui.utils

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.rendering.Renderer
import java.io.IOException
import java.nio.file.Path
import javax.imageio.ImageIO

/**
 * Holds values which define an image in the library.
 */
data class ImageBuffer(val path: Path) {

    /**
     * The identifier of the image, if this is null, the image has not been initialized yet.
     */
    var id: Int? = null

    /**
     * The size of the image in pixels.
     */
    lateinit var size: Size

    /**
     * Registers an image to the renderer.
     *
     * @param renderer The renderer to initialize the image.
     */
    fun register(renderer: Renderer) {
        try {
            val data = getBufferData(path)
            id = renderer.registerImage(path, data)

            val stream = VoidUI::class.java.classLoader.getResourceAsStream(path.toString()) ?:
                throw IllegalArgumentException("Image buffer could not be loaded: $path")

            val img = ImageIO.read(stream)
            size = Size(img.width.toFloat(), img.height.toFloat())
        } catch (e: IOException) {
            VoidUI.LOGGER.error("Failed to register image $path", e)
        }
    }

    /**
     * Deletes the image from the renderer.
     *
     * @param renderer The renderer to delete the image.
     */
    fun delete(renderer: Renderer) {
        renderer.unregisterImage(this)
    }

}