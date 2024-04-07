package com.neptuneclient.voidui.utils

import com.neptuneclient.voidui.VoidUI
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.file.Path

/**
 * This class holds values which represent a font in the renderer.
 *
 * @param void The singleton of the library.
 * @param filePath The file path of the font file.
 * @param size The size of the text in pixels.
 * @param letterSpacing The amount of letter spacing used when rendering.
 */
class Font(void: VoidUI, val identifier: String, filePath: Path, val size: Int, val letterSpacing: Int) {

    val data: ByteBuffer = try {
        getBufferData(filePath)
    } catch (e: IOException) {
        VoidUI.LOGGER.error("Failed to load font data, this may cause a crash later!", e)
        ByteBuffer.allocateDirect(0)
    }

    init {
        void.renderer.registerFont(this)
    }

}