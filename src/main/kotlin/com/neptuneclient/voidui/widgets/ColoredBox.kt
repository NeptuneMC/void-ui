package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.Offset
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.framework.Widget
import com.neptuneclient.voidui.rendering.RenderObject
import com.neptuneclient.voidui.rendering.Renderer
import java.awt.Color

/**
 * Wraps a child in a box with a background color.
 *
 * @param child The child widget, which is inside the box.
 * @param color The background color of the box.
 */
class ColoredBox(
    private val child: Widget,
    private val color: Color
) : Widget() {

    override fun build() = child

    override fun createRenderObject(): RenderObject? {
        return ColoredBoxRenderObject(offset, size, color)
    }

}

private class ColoredBoxRenderObject(offset: Offset, size: Size, val color: Color) : RenderObject(offset, size) {
    override fun render(renderer: Renderer) {
        renderer.rectangle(offset.x, offset.y, size.width, size.height, color)
    }
}