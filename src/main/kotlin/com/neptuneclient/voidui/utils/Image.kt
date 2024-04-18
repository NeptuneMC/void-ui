package com.neptuneclient.voidui.utils

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.rendering.Renderer
import java.io.IOException
import java.nio.file.Path

data class Image(val path: Path) {

    var id: Int? = null

    fun register(renderer: Renderer) {
        try {
            val data = getBufferData(path)
            id = renderer.registerImage(path, data)
        } catch (e: IOException) {
            VoidUI.LOGGER.error("Failed to register image $path", e)
        }
    }

    fun delete(renderer: Renderer) {
        renderer.unregisterImage(this)
    }

}