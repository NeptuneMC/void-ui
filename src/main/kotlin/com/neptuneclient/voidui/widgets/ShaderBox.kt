package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.*
import com.neptuneclient.voidui.rendering.RenderObject
import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.shaders.ShaderProgram
import com.neptuneclient.voidui.shaders.Uniform

class ShaderBox(
    private val src: ShaderProgram,
    private val uniforms: Map<String, Uniform>,
    private val boxSize: Size? = null
) : LeafWidget() {

    override fun init(screen: Screen, parent: Widget) {
        super.init(screen, parent)
        if (!voidUI.settings.useShaders)
            throw IllegalStateException("Shaders are disabled in the settings!")

        src.setup(voidUI.renderer)
        src.setUniforms(voidUI.renderer, uniforms)
    }

    override fun layout(constraints: BoxConstraints) {
        val s = boxSize ?: Size(constraints.maxWidth, constraints.maxHeight)
        size = constraints.constrain(s)
    }

    override fun createRenderObject(): RenderObject? {
        return ShaderBoxRenderObject(offset, size)
    }

    override fun remove() {
        super.remove()
        src.delete(voidUI.renderer)
    }

}

class ShaderBoxRenderObject(offset: Offset, size: Size) : RenderObject(offset, size) {
    override fun render(renderer: Renderer) {
        // render shader
    }

}