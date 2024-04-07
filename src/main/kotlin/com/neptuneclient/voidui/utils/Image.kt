package com.neptuneclient.voidui.utils

import com.neptuneclient.voidui.VoidUI
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.file.Path
import com.neptuneclient.voidui.rendering.Renderer

/**
 * A class which holds values about an image memory in the renderer.
 *
 * @param void The singleton to the library.
 * @param path The file path to the image.
 */
class Image(private val void: VoidUI, path: Path) {

    /**
     * The id for the image, this is assigned by [Renderer.unregisterImage].
     */
    val identifier: Int

    init {
        val data: ByteBuffer = try {
            getBufferData(path)
        } catch (e: IOException) {
            VoidUI.LOGGER.error("Failed to load font data, this may cause a crash later!", e)
            ByteBuffer.allocateDirect(0)
        }

        identifier = void.renderer.registerImage(path, data)
    }

    /**
     * Used to unregister the image from the renderer.
     */
    fun delete() {
        void.renderer.unregisterImage(this)
    }

}