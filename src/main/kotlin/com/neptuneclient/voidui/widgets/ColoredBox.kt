package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.Offset
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.framework.Widget
import com.neptuneclient.voidui.objects.Border
import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.rendering.RenderObject
import com.neptuneclient.voidui.rendering.Renderer
import java.awt.Color

/**
 * Wraps a child in a box with a background color.
 *
 * @param child The child widget, which is inside the box.
 * @param color The background color of the box.
 * @param cornerRadius The cornerRadius of the colored box.
 * @param border A border for the box.
 */
class ColoredBox(
    private val child: Widget,
    private val color: Color,
    private val cornerRadius: CornerRadius = CornerRadius.zero,
    private val border: Border = Border(0f, Color(0))
) : Widget() {

    override fun build() = child

    override fun createRenderObject(): RenderObject? {
        return ColoredBoxRenderObject(offset, size, color, cornerRadius, border)
    }

}

private class ColoredBoxRenderObject(offset: Offset, size: Size, val color: Color, val cornerRadius: CornerRadius, val border: Border) : RenderObject(offset, size) {
    override fun render(renderer: Renderer) {
        if (cornerRadius.isEmpty())
            renderer.rectangle(offset, size, color)
        else
            renderer.roundedRectangle(offset, size, cornerRadius, color)

        if (border.width > 0f) {
            if (cornerRadius.isEmpty())
                renderer.rectangleFrame(offset, size, border.width, border.color)
            else
                renderer.roundedRectangleFrame(offset, size, cornerRadius, border.width, border.color)
        }
    }
}
