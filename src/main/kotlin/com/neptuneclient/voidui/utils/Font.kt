package com.neptuneclient.voidui.utils

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.rendering.Renderer
import java.io.IOException
import java.nio.file.Path

/**
 * Holds values for a font object for the library.
 */
data class Font(val name: String, val path: Path) {

    /**
     * The font data in a byte buffer.
     */
    private val data = getBufferData(path)

    /**
     * Registers the font to the renderer.
     *
     * @param renderer The renderer to register the font to.
     */
    fun register(renderer: Renderer) {
        try {
            renderer.registerFont(name, data)
        } catch (e: IOException) {
            VoidUI.LOGGER.error("Failed to load font data, this may cause a crash later!", e)
        }
    }

}