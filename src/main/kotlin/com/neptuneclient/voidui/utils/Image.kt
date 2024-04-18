package com.neptuneclient.voidui.utils

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.rendering.Renderer
import java.io.IOException
import java.nio.file.Path

/**
 * Holds values which define an image in the library.
 */
data class Image(val path: Path) {

    /**
     * The identifier of the image, if this is null, the image has not been initialized yet.
     */
    var id: Int? = null

    /**
     * Registers an image to the renderer.
     *
     * @param renderer The renderer to initialize the image.
     */
    fun register(renderer: Renderer) {
        try {
            val data = getBufferData(path)
            id = renderer.registerImage(path, data)
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