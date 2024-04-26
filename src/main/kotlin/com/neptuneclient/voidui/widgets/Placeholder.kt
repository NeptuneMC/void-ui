package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.BoxConstraints
import com.neptuneclient.voidui.framework.LeafWidget
import com.neptuneclient.voidui.framework.Offset
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.rendering.RenderObject
import com.neptuneclient.voidui.rendering.Renderer
import java.awt.Color

/**
 * A widget which just acts as a placeholder. It covers the whole size of its parent.
 */
class Placeholder : LeafWidget() {

    override fun layout(constraints: BoxConstraints) {
        size = Size(constraints.maxWidth, constraints.maxHeight)
    }

    override fun createRenderObject(): RenderObject? {
        return PlaceholderRenderObject(offset, size)
    }

}

private class PlaceholderRenderObject(offset: Offset, size: Size) : RenderObject(offset, size) {
    override fun render(renderer: Renderer) {
        renderer.rectangle(offset, size, Color.WHITE)
        renderer.rectangleFrame(offset, size, 10.0f, Color.RED)
    }
}