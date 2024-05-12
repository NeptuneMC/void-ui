package com.neptuneclient.voidui.shaders

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.rendering.Renderer
import java.nio.file.Path

class ShaderProgram(vertexShader: Path, fragmentShader: Path) {

    val vertexSource: String = readShaderSource(vertexShader)
    val fragmentSource: String = readShaderSource(fragmentShader)

    var id: Int? = null

    fun setup(renderer: Renderer) {
        id = renderer.compileShaderProgram(vertexSource, fragmentSource)
    }

    fun delete(renderer: Renderer) {
        if (id != null)
            renderer.deleteShaderProgram(id!!)
    }

    fun setUniforms(renderer: Renderer, uniforms: Map<String, Uniform>) {
        if (id == null) {
            VoidUI.LOGGER.error("Tried applying uniforms to unregistered shader program!")
            return
        }
        for ((name, uniform) in uniforms) {
            if (uniform.values.size > 1) {
                renderer.setUniform(id!!, name, uniform.values)
            } else {
                renderer.setUniform(id!!, name, uniform.values[0])
            }
        }
    }

    private fun readShaderSource(path: Path): String {
        val stream = VoidUI::class.java.classLoader.getResourceAsStream(path.toString()) ?:
            throw IllegalArgumentException("Could not find the resource at the provided path!")

        return String(stream.readBytes(), Charsets.UTF_8)
    }

}