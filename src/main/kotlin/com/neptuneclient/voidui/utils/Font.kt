package com.neptuneclient.voidui.utils

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.rendering.Renderer
import java.io.IOException
import java.nio.file.Path

data class Font(val name: String, val path: Path) {

    fun register(renderer: Renderer) {
        try {
            val data = getBufferData(path)
            renderer.registerFont(name, data)
        } catch (e: IOException) {
            VoidUI.LOGGER.error("Failed to load font data, this may cause a crash later!", e)
        }
    }

}