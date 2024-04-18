package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.BoxConstraints
import com.neptuneclient.voidui.framework.LeafWidget
import com.neptuneclient.voidui.framework.Offset
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.rendering.Renderer
import java.awt.Color

/**
 * A widget which just acts as a placeholder. It covers the whole size of its parent.
 */
class Placeholder : LeafWidget() {

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        offset = parentOffset
        size = Size(constraints.maxWidth, constraints.maxHeight)
    }

    override fun render(renderer: Renderer) {
        renderer.rectangle(offset.x, offset.y, size.width, size.height, Color.WHITE)
        renderer.rectangleFrame(offset.x, offset.y, size.width, size.height, 10.0F, Color.RED)
    }

}