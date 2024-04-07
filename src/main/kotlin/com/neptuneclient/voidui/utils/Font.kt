package com.neptuneclient.voidui.utils

import com.neptuneclient.voidui.VoidUI
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.file.Path

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